package com.young.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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

import com.young.R;
import com.young.drawlayout.choseTermsDrawlayoutFragment;
import com.young.entry.Student;
import com.young.sqlite.DatabaseHelper;

public class ChoseTermsActivity extends FragmentActivity implements
		OnClickListener {

	private ImageButton loginIb;
	private ImageButton roomIb;
	private ImageButton scoreIb;
	private ImageButton mapIb;
	private ImageButton newsIb;
	private ImageButton academyIb;
	private Student student;
	private TextView userName;
	private ImageView userHead;
	private String stuId;
	private String password;
	private DatabaseHelper helper;
	private TextView scoreTv;
	private ImageView scoreIv;
	// 抽屉菜单对象
	private ActionBarDrawerToggle drawerbar;
	public DrawerLayout drawerLayout;
	private choseTermsDrawlayoutFragment testFragment;
	private RelativeLayout left_menu_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_activity);

		@SuppressWarnings("deprecation")
		final SharedPreferences sp = this.getSharedPreferences("userInfo",
				Context.MODE_WORLD_READABLE);
		String stu = sp.getString("USER_NAME", "");
		// 判断你是否登陆过，如果是就直接进入ChoseTermsActivity
		if (!("".equals(stu))) {
			initView();
			initEvent();
			getUserIdAndPassWord();
			helper = new DatabaseHelper(this);
			student = helper.getStudent(stuId);
			Resources resources = getResources();
			if (student == null) {
				userName.setText("请登录");
			} else {
				userName.setText(student.getStuName());
			}
			if (student == null) {
				Drawable btnDrawable = resources
						.getDrawable(R.drawable.not_logged_in);
				userHead.setBackgroundDrawable(btnDrawable);
			} else {
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
		} else {
			Intent it = new Intent(this, LoginActivity.class);
			it.putExtra("Message", "查成绩");
			startActivity(it);
			finish();
		}

	}

	private void getUserIdAndPassWord() {
		@SuppressWarnings("deprecation")
		SharedPreferences sp = ChoseTermsActivity.this.getSharedPreferences(
				"userInfo", Context.MODE_WORLD_READABLE);
		stuId = sp.getString("USER_NAME", "");
		password = sp.getString("PASSWORD", "");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_ib:
			Intent it = new Intent(ChoseTermsActivity.this, LoginActivity.class);
			it.putExtra("Message", "地图");
			startActivity(it);
			drawerLayout.closeDrawer(left_menu_layout);
			finish();
			break;
		case R.id.room_ib:
			Intent it1 = new Intent(ChoseTermsActivity.this,
					SearchRoomActivity.class);
			startActivity(it1);
			drawerLayout.closeDrawer(left_menu_layout);
			finish();
			break;
		case R.id.score_ib:
			drawerLayout.closeDrawer(left_menu_layout);
			break;
		case R.id.news_ib:
			Intent it3 = new Intent(ChoseTermsActivity.this, NewsActivity.class);
			startActivity(it3);
			drawerLayout.closeDrawer(left_menu_layout);
			finish();
			break;
		case R.id.academy_ib:
			Intent it2 = new Intent(ChoseTermsActivity.this,
					AcademyNewsActivity.class);
			startActivity(it2);
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
		testFragment = new choseTermsDrawlayoutFragment();
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
		scoreIv = (ImageView) view2.findViewById(R.id.score_iv);
		scoreTv = (TextView) view2.findViewById(R.id.score_tv);
		loginIb.setOnClickListener(this);
		roomIb.setOnClickListener(this);
		scoreIb.setOnClickListener(this);
		mapIb.setOnClickListener(this);
		newsIb.setOnClickListener(this);
		academyIb.setOnClickListener(this);
		left_menu_layout.addView(view2);
		scoreTv.setTextColor(this.getResources().getColor(R.color.color_1));
		scoreIv.setImageDrawable(this.getResources().getDrawable(
				R.drawable.score_iv_in));
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
}
