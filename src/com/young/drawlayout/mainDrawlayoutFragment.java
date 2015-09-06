package com.young.drawlayout;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.young.R;
import com.young.activity.AboutUsActivity;
import com.young.activity.ChangePwdActivity;
import com.young.activity.ChoseScheduleTypeActitity;
import com.young.activity.InfoActivity;
import com.young.activity.LibraryActivity;
import com.young.activity.LoginActivity;
import com.young.activity.MainActivity;
import com.young.entry.Student;
import com.young.sqlite.DatabaseHelper;
import com.young.util.AppUtil;
import com.young.util.UpdateManager;

public class mainDrawlayoutFragment extends Fragment {
	private View view;
	public ImageView menuBtn;
	private ImageView setting;
	private PopupWindow popupwindow;
	private ImageButton chosescheduletype;
	private ImageButton info;
	private ImageButton library;
	private ImageButton changepwd;
	private ImageView mainHead;
	private TextView mainName;
	private DatabaseHelper helper;
	private Student student;
	private String stuId;
	private String myStuId; // 我的学号，也就是登陆的学号
	private String password;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_main, null);
		initView();
		return view;
	}

	public void initView() {
		menuBtn = (ImageView) view.findViewById(R.id.main_sidebar_ib);
		setting = (ImageView) view.findViewById(R.id.setting);
		mainName = (TextView) view.findViewById(R.id.main_name);
		mainHead = (ImageView) view.findViewById(R.id.main_head);
		chosescheduletype = (ImageButton) view
				.findViewById(R.id.chosescheduletype);
		info = (ImageButton) view.findViewById(R.id.info);
		library = (ImageButton) view.findViewById(R.id.library);
		changepwd = (ImageButton) view.findViewById(R.id.changepwd);

		getUserIdAndPassWord();
		helper = new DatabaseHelper(getActivity());
		student = helper.getStudent(stuId);
		if (student == null) {
			new GetStudentFromNetWork().execute();
		} else {
			setUI();
		}
		// 点击打开左边菜单
		menuBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).openLeftLayout();
			}
		});
		setting.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (popupwindow != null && popupwindow.isShowing()) {
					popupwindow.dismiss();
					return;
				} else {
					initmPopupWindowView();
					popupwindow.showAsDropDown(v, 0, 0);
				}
			}
		});
		chosescheduletype.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it1 = new Intent(getActivity(),
						ChoseScheduleTypeActitity.class);
				getActivity().startActivity(it1);
			}
		});
		info.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(getActivity(), InfoActivity.class);
				getActivity().startActivity(it);
			}
		});
		library.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it2 = new Intent(getActivity(), LibraryActivity.class);
				getActivity().startActivity(it2);
			}
		});
		changepwd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it3 = new Intent(getActivity(), ChangePwdActivity.class);
				getActivity().startActivity(it3);
			}
		});
	}

	private void setUI() {
		Resources resources = getResources();
		if (student == null) {
			mainName.setText("请登录");
			Drawable btnDrawable = resources
					.getDrawable(R.drawable.not_logged_in);
			mainHead.setBackgroundDrawable(btnDrawable);
		} else {
			mainName.setText(student.getStuName());

			if (student.getSex().equals("男")) {
				Drawable btnDrawable = resources
						.getDrawable(R.drawable.userhead_man);
				mainHead.setBackgroundDrawable(btnDrawable);
			} else {
				Drawable btnDrawable = resources
						.getDrawable(R.drawable.userhead_woman);
				mainHead.setBackgroundDrawable(btnDrawable);
			}
		}
	}

	private class GetStudentFromNetWork extends
			AsyncTask<String, Integer, String> {

		// @Override
		protected String doInBackground(String... arg0) {

			return "更新完毕";
		}

		// 这个是在后台执行完毕之后执行
		@Override
		protected void onPostExecute(String result) {
			if (("更新完毕").equals(result)) {
				// 需要在这里在查一遍，看看有没有数据
				student = helper.getStudent(stuId);
				if (student != null) {
					setUI();
				}
			}
		}
	}

	private void getUserIdAndPassWord() {
		@SuppressWarnings("deprecation")
		SharedPreferences sp = getActivity().getSharedPreferences("userInfo",
				Context.MODE_WORLD_READABLE);
		stuId = sp.getString("USER_NAME", "");
		password = sp.getString("PASSWORD", "");
	}

	public void initmPopupWindowView() {

		// // 获取自定义布局文件pop.xml的视图
		View customView = getActivity().getLayoutInflater().inflate(
				R.layout.main_pop, null, false);
		popupwindow = new PopupWindow(customView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, true);
		popupwindow.showAtLocation(customView, Gravity.RIGHT | Gravity.TOP, 25,
				145);
		// 自定义view添加触摸事件
		customView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (popupwindow != null && popupwindow.isShowing()) {
					popupwindow.dismiss();
					popupwindow = null;
				}

				return false;
			}
		});

		/** 在这里可以实现自定义视图的功能 */
		Button aboutUsIb = (Button) customView.findViewById(R.id.about_us_ib);
		Button checkUpdateIb = (Button) customView
				.findViewById(R.id.check_update_ib);
		Button logoutIb = (Button) customView.findViewById(R.id.logout_ib);
		aboutUsIb.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(getActivity(),
						AboutUsActivity.class);
				getActivity().startActivity(intent1);
				popupwindow.dismiss();
			}
		});
		checkUpdateIb.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread() {
					@Override
					public void run() {
						Looper.prepare();
						UpdateManager manager = new UpdateManager(getActivity());
						manager.checkUpdate();
						Looper.loop();
					}
				}.start();
			}
		});
		logoutIb.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setTitle("确定注销吗？");
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						})
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										@SuppressWarnings("deprecation")
										SharedPreferences sp = getActivity()
												.getSharedPreferences(
														"userInfo",
														Context.MODE_WORLD_READABLE);
										SharedPreferences.Editor editor = sp
												.edit();
										editor.remove("USER_NAME");
										editor.remove("PASSWORD");
										editor.commit();
										Intent intent = new Intent(
												getActivity(),
												LoginActivity.class);
										intent.putExtra("Message", "注销");
										getActivity().startActivity(intent);
										AppUtil.getInstance().exit();

										Toast.makeText(getActivity(), "注销成功",
												Toast.LENGTH_LONG).show();
										getActivity().finish();
									}
								}).show();
			}
		});
	}
}
