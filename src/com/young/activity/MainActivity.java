package com.young.activity;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.young.R;
import com.young.business.HBUT;
import com.young.drawlayout.mainDrawlayoutFragment;
import com.young.entry.Student;
import com.young.sqlite.DatabaseHelper;
import com.young.util.NetworkUtil;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private ImageButton loginIb;
	private ImageButton roomIb;
	private ImageButton scoreIb;
	private ImageButton mapIb;
	private ImageButton newsIb;
	private ImageButton academyIb;
	private Student student;
	private TextView userName;
	private ImageView userHead;
	private String myStuId;
	private String stuId;
	private String password;
	private DatabaseHelper helper;
//	private long exitTime;// 两次返回键退出之间的间隔
	// 抽屉菜单对象
	private ActionBarDrawerToggle drawerbar;
	public DrawerLayout drawerLayout;
	private mainDrawlayoutFragment testFragment;
	private RelativeLayout left_menu_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_activity);
		initView();
		initEvent();

		getUserIdAndPassWord();
		helper = new DatabaseHelper(this);
		student = helper.getStudent(stuId);
		if (student == null) {
			new GetStudentFromNetWork().execute();
		} else {
			setUI();
		}
	}

	private void setUI() {
		Resources resources = getResources();
		if (student == null) {
			userName.setText("请登录");
			Drawable btnDrawable = resources
					.getDrawable(R.drawable.not_logged_in);
			userHead.setBackgroundDrawable(btnDrawable);
		} else {
			userName.setText(student.getStuName());

			if (student.getSex().equals("男")) {
				Drawable btnDrawable = resources
						.getDrawable(R.drawable.userhead_man);
				userHead.setBackgroundDrawable(btnDrawable);
			} else {
				Drawable btnDrawable = resources
						.getDrawable(R.drawable.userhead_woman);
				userHead.setBackgroundDrawable(btnDrawable);
			}
		}
	}

	private class GetStudentFromNetWork extends
			AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... arg0) {
			HBUT hbut = HBUT.getInstance();
			try {
				if (!NetworkUtil.isOpenNetwork()) {
					finish();
					return "无网络连接";
				} else {
					hbut.login(stuId, password);
					student = hbut.getInfo();
					helper.insertStudent(student);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
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
				} else {
					Toast.makeText(MainActivity.this, "没有数据", Toast.LENGTH_LONG)
							.show();
				}
			} else {
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG)
						.show();
			}

		}
	}

	private void getUserIdAndPassWord() {
		@SuppressWarnings("deprecation")
		SharedPreferences sp = MainActivity.this.getSharedPreferences(
				"userInfo", Context.MODE_WORLD_READABLE);
		myStuId = stuId;
		stuId = sp.getString("USER_NAME", "");
		password = sp.getString("PASSWORD", "");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_ib:
			drawerLayout.closeDrawer(left_menu_layout);
			break;
		case R.id.room_ib:
			Intent it1 = new Intent(MainActivity.this, SearchRoomActivity.class);
			startActivity(it1);
			drawerLayout.closeDrawer(left_menu_layout);
			finish();
			break;
		case R.id.score_ib:
			Intent it2 = new Intent(MainActivity.this, ChoseTermsActivity.class);
			startActivity(it2);
			drawerLayout.closeDrawer(left_menu_layout);
			finish();
			break;
		case R.id.news_ib:
			Intent it3 = new Intent(MainActivity.this, NewsActivity.class);
			startActivity(it3);
			drawerLayout.closeDrawer(left_menu_layout);
			finish();
			break;
		case R.id.academy_ib:
			Intent it = new Intent(MainActivity.this, AcademyNewsActivity.class);
			startActivity(it);
			drawerLayout.closeDrawer(left_menu_layout);
			finish();
			break;
		case R.id.map_ib:
			drawerLayout.closeDrawer(left_menu_layout);
			finish();
			break;
		}

	}

	public void initView() {
		testFragment = new mainDrawlayoutFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction f_transaction = fragmentManager.beginTransaction();
		f_transaction.replace(R.id.main_content_frame_parent, testFragment);
		f_transaction.commitAllowingStateLoss();
		initLeftLayout();
	}

	public void initLeftLayout() {
		drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
		// 左边菜单
		left_menu_layout = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
		View view2 = getLayoutInflater().inflate(R.layout.layout_menu, null);
		loginIb = (ImageButton) view2.findViewById(R.id.login_ib);
		roomIb = (ImageButton) view2.findViewById(R.id.room_ib);
		scoreIb = (ImageButton) view2.findViewById(R.id.score_ib);
		mapIb = (ImageButton) view2.findViewById(R.id.map_ib);
		newsIb = (ImageButton) view2.findViewById(R.id.news_ib);
		academyIb = (ImageButton) view2.findViewById(R.id.academy_ib);
		userHead = (ImageView) view2.findViewById(R.id.user_head);
		userName = (TextView) view2.findViewById(R.id.user_name);
		loginIb.setOnClickListener(this);
		roomIb.setOnClickListener(this);
		scoreIb.setOnClickListener(this);
		mapIb.setOnClickListener(this);
		newsIb.setOnClickListener(this);
		academyIb.setOnClickListener(this);
		left_menu_layout.addView(view2);
	}

	private void initEvent() {
		drawerbar = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.icon, 0, 0) {
			@Override
			public void onDrawerOpened(View drawerView) {

				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {

				super.onDrawerClosed(drawerView);
			}
		};
		drawerLayout.setDrawerListener(drawerbar);
	}

	public void openLeftLayout() {
		if (drawerLayout.isDrawerOpen(left_menu_layout)) {
			drawerLayout.closeDrawer(left_menu_layout);
		} else {
			drawerLayout.openDrawer(left_menu_layout);

		}
	}

	// 按两次退出程序
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK
//				&& event.getAction() == KeyEvent.ACTION_DOWN) {
//
//			if ((System.currentTimeMillis() - exitTime) > 2000) // System.currentTimeMillis()无论何时调用，肯定大于2000
//			{
//				Toast.makeText(getApplicationContext(), "再按一次退出程序",
//						Toast.LENGTH_SHORT).show();
//				exitTime = System.currentTimeMillis();
//			} else {
//				AppUtil.getInstance().exit();
//			}
//
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
}
