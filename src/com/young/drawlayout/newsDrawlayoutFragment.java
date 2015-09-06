package com.young.drawlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.R;
import com.young.activity.NewsActivity;
import com.young.fragment.NewsFragment1;
import com.young.fragment.NewsFragment2;
import com.young.fragment.NewsFragment3;
import com.young.fragment.NewsFragment4;
import com.young.fragment.NewsFragment5;

public class newsDrawlayoutFragment extends Fragment {
	private View view;
	public ImageView menuBtn;
	private TextView announcementTv;
	private TextView lectureTv;
	private TextView eventTv;
	private TextView recruitmentTv;
	private TextView lostAndFoundTv;

	private FragmentManager fm;
	private FragmentTransaction ft1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.news_activity, null);
		initView();
		return view;
	}

	public void initView() {
		menuBtn = (ImageView) view.findViewById(R.id.news_sidebar_ib);
		announcementTv = (TextView) view.findViewById(R.id.announcement_tv);
		lectureTv = (TextView) view.findViewById(R.id.lecture_tv);
		eventTv = (TextView) view.findViewById(R.id.event_tv);
		recruitmentTv = (TextView) view.findViewById(R.id.recruitment_tv);
		lostAndFoundTv = (TextView) view.findViewById(R.id.lost_and_found_tv);
		// 开启一个事务
		ft1 = getFragmentManager().beginTransaction();
		// 添加一个Fragment对象
		NewsFragment1 fg1 = new NewsFragment1();
		ft1.add(R.id.fl_news, fg1);
		// 提交
		ft1.commit();
		announcementTv.setSelected(true);
		lectureTv.setSelected(false);
		eventTv.setSelected(false);
		recruitmentTv.setSelected(false);
		lostAndFoundTv.setSelected(false);

		// 点击打开左边菜单
		menuBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((NewsActivity) getActivity()).openLeftLayout();
			}
		});
		announcementTv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 开启一个事务
				ft1 = getFragmentManager().beginTransaction();
				// 添加一个Fragment对象
				NewsFragment1 fg1 = new NewsFragment1();
				ft1.add(R.id.fl_news, fg1);
				// 提交
				ft1.commit();
				announcementTv.setSelected(true);
				lectureTv.setSelected(false);
				eventTv.setSelected(false);
				recruitmentTv.setSelected(false);
				lostAndFoundTv.setSelected(false);
			}
		});
		lectureTv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 开启一个事务
				ft1 = getFragmentManager().beginTransaction();
				// 添加一个Fragment对象
				NewsFragment2 fg2 = new NewsFragment2();
				ft1.add(R.id.fl_news, fg2);
				// 提交
				ft1.commit();
				announcementTv.setSelected(false);
				lectureTv.setSelected(true);
				eventTv.setSelected(false);
				recruitmentTv.setSelected(false);
				lostAndFoundTv.setSelected(false);
			}
		});
		eventTv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 开启一个事务
				ft1 = getFragmentManager().beginTransaction();
				// 添加一个Fragment对象
				NewsFragment3 fg3 = new NewsFragment3();
				ft1.add(R.id.fl_news, fg3);
				// 提交
				ft1.commit();
				announcementTv.setSelected(false);
				lectureTv.setSelected(false);
				eventTv.setSelected(true);
				recruitmentTv.setSelected(false);
				lostAndFoundTv.setSelected(false);
			}
		});

		recruitmentTv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 开启一个事务
				ft1 = getFragmentManager().beginTransaction();
				// 添加一个Fragment对象
				NewsFragment4 fg4 = new NewsFragment4();
				ft1.add(R.id.fl_news, fg4);
				// 提交
				ft1.commit();
				announcementTv.setSelected(false);
				lectureTv.setSelected(false);
				eventTv.setSelected(false);
				recruitmentTv.setSelected(true);
				lostAndFoundTv.setSelected(false);
			}
		});
		lostAndFoundTv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 开启一个事务
				ft1 = getFragmentManager().beginTransaction();
				// 添加一个Fragment对象
				NewsFragment5 fg5 = new NewsFragment5();
				ft1.add(R.id.fl_news, fg5);
				// 提交
				ft1.commit();
				announcementTv.setSelected(false);
				lectureTv.setSelected(false);
				eventTv.setSelected(false);
				recruitmentTv.setSelected(false);
				lostAndFoundTv.setSelected(true);
			}
		});
	}
}
