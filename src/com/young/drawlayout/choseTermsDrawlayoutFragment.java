package com.young.drawlayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.young.R;
import com.young.activity.ChoseTermsActivity;
import com.young.activity.ScoreManagementActivity;
import com.young.business.HBUT;
import com.young.entry.Score;
import com.young.entry.ScoreInfo;
import com.young.entry.Student;
import com.young.sqlite.DatabaseHelper;
import com.young.sqlite.SQLiteHelper;
import com.young.util.DropDownListView;
import com.young.util.NetworkUtil;

public class choseTermsDrawlayoutFragment extends Fragment {
	private Student student;
	private View view;
	public ImageView menuBtn;
	private DropDownListView listView;
	private ArrayList<String> data;
	private String stuId;
	private String myStuId; // 我的学号，也就是登陆的学号
	private String password;
	private ScoreInfo info = null;
	private TextView scoreInfoView;
	private TextView titleName;
	private ProgressDialog proDialog;
	private DatabaseHelper helper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_chose_term, null);
		initView();
		return view;
	}

	public void initView() {
		menuBtn = (ImageView) view.findViewById(R.id.chose_term_sidebar_ib);
		listView = (DropDownListView) view.findViewById(R.id.list_terms);
		scoreInfoView = (TextView) view.findViewById(R.id.text_score_info);
		titleName = (TextView) view.findViewById(R.id.title_name);

		getUserIdAndPassWord();
		helper = new DatabaseHelper(getActivity());
		student = helper.getStudent(stuId);
		// 下拉刷新
		listView.setonRefreshListener(new DropDownListView.OnRefreshListener() {

			@Override
			public void onRefresh() {
				if (helper != null) {
					if (!NetworkUtil.isOpenNetwork()) {
						Toast.makeText(getActivity(), "无网络连接！",
								Toast.LENGTH_LONG).show();
					} else {
						// 先删除表中的所有数据
						helper.clearTableScore(stuId);
						helper.clearTableScoreInfo(stuId);
						new GetScoreFromNetWork().execute();
					}
				}
			}
		});

		// 得到数据的方法要放到这里，然后给listView设置Adapter可以放到onStart里面
		if (stuId != "") {
			// 判断表中是否有数据，如果有就直接获取，如果没有就重新去网络拿数据
			if (helper.isEmpty(SQLiteHelper.TABLE_SCORE, stuId)) {
				new GetScoreFromNetWork().execute();
			} else {
				data = helper.getTerms(stuId);
				info = helper.getScoreInfo(stuId);
			}
		}
		// 给ListView添加单击响应
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(),
						ScoreManagementActivity.class);
				intent.putExtra("term", data.get(position - 1));
				intent.putExtra("id", stuId);
				startActivity(intent);
			}
		});
		// 点击打开左边菜单
		menuBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((ChoseTermsActivity) getActivity()).openLeftLayout();
			}
		});
	}

	private void getUserIdAndPassWord() {
		@SuppressWarnings("deprecation")
		SharedPreferences sp = getActivity().getSharedPreferences("userInfo",
				Context.MODE_WORLD_READABLE);
		stuId = sp.getString("USER_NAME", "");
		myStuId = stuId;
		password = sp.getString("PASSWORD", "");
	}

	public class GetScoreFromNetWork extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {
			proDialog = ProgressDialog.show(getActivity(), "加载中",
					"玩命加载中,请稍等...");
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... arg0) {
			HBUT hbut = HBUT.getInstance();
			try {
				if (!NetworkUtil.isOpenNetwork()) {
					Intent intent = new Intent(getActivity(),
							ChoseTermsActivity.class);
					getActivity().startActivity(intent);
					return "无网络连接";
				} else {
					hbut.login(myStuId, password);
					HashMap<String, Object> map = hbut.getScore(stuId);
					@SuppressWarnings("unchecked")
					ArrayList<Score> scores = (ArrayList<Score>) map
							.get("scores");
					info = (ScoreInfo) map.get("score_info");
					for (Score score : scores) {
						helper.addScore(score);
					}
					helper.insertScoreInfo(info);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return "更新完毕";
		}

		// 这个是在后台执行完毕之后执行
		@Override
		protected void onPostExecute(String result) {
			if (("更新完毕").equals(result)) {
				listView.onRefreshComplete();
				// 需要在这里在查一遍，看看有没有数据
				data = helper.getTerms(stuId);
				if (data != null) {
					ArrayList<String> myData = new ArrayList<String>();
					for (int i = 0; i < data.size(); i++) {
						String str = data.get(i);
						myData.add(str.substring(0, 4) + "学年第"
								+ str.substring(4) + "学期");
					}
					listView.setAdapter(new ArrayAdapter<String>(getActivity(),
							android.R.layout.simple_list_item_1, myData));
					if (info != null) {
						scoreInfoView.setText("平均绩点："
								+ info.getAverageGradePoint() + " 总绩点："
								+ info.getTotalGradePoint());
						titleName.setText(info.getName());
					}
				} else {
					Toast.makeText(getActivity(), "你还没有数据", Toast.LENGTH_LONG)
							.show();
				}
			} else {
				Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
			}
			proDialog.dismiss();
		}
	}

	@Override
	public void onStart() {
		if (data != null) {
			ArrayList<String> myData = new ArrayList<String>();
			for (int i = 0; i < data.size(); i++) {
				String str = data.get(i);
				myData.add(str.substring(0, 4) + "学年第" + str.substring(4)
						+ "学期");
			}
			listView.setAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, myData));
			if (info != null) {
				scoreInfoView.setText("平均绩点：" + info.getAverageGradePoint()
						+ " 总绩点：" + info.getTotalGradePoint());
				titleName.setText(info.getName());
			}
		}
		super.onStart();
	}
}
