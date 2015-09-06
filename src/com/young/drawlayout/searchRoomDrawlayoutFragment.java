package com.young.drawlayout;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.young.R;
import com.young.activity.SearchRoomActivity;
import com.young.fragment.SearchRoomFragment1;
import com.young.fragment.SearchRoomFragment2;
import com.young.fragment.SearchRoomFragment3;
import com.young.fragment.SearchRoomFragment4;
import com.young.fragment.SearchRoomFragment5;

public class searchRoomDrawlayoutFragment extends Fragment {
	private View view;
	public ImageView menuBtn;
	private TextView tv1, tv2, tv3, tv4, tv5;
	private FrameLayout flRoom;
	private FragmentTransaction ft1;
	private ImageView arrowIb;
	private Button room1;
	private Button room2;
	private Button room3;
	private Button room4;
	private Button room5;
	private Button room6;
	private Button room7;
	private Button room8;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.search_room_activity, null);
		initView();
		return view;
	}

	public void initView() {
		menuBtn = (ImageView) view.findViewById(R.id.search_room_sidebar_ib);
		flRoom = (FrameLayout) view.findViewById(R.id.fl_room);
		arrowIb = (ImageView) view.findViewById(R.id.searchroom_arrow_ib);
		tv1 = (TextView) view.findViewById(R.id.tv_1);
		tv2 = (TextView) view.findViewById(R.id.tv_2);
		tv3 = (TextView) view.findViewById(R.id.tv_3);
		tv4 = (TextView) view.findViewById(R.id.tv_4);
		tv5 = (TextView) view.findViewById(R.id.tv_5);
		// 开启一个事务
		ft1 = getFragmentManager().beginTransaction();
		// 添加一个Fragment对象
		SearchRoomFragment1 fg1 = new SearchRoomFragment1();
		ft1.add(R.id.fl_room, fg1);
		// 提交
		ft1.commit();
		tv1.setSelected(true);
		tv2.setSelected(false);
		tv3.setSelected(false);
		tv4.setSelected(false);
		tv5.setSelected(false);

		// 点击打开左边菜单
		menuBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((SearchRoomActivity) getActivity()).openLeftLayout();
			}
		});
		tv1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 开启一个事务
				ft1 = getFragmentManager().beginTransaction();
				// 添加一个Fragment对象
				SearchRoomFragment1 fg1 = new SearchRoomFragment1();
				ft1.add(R.id.fl_room, fg1);
				// 提交
				ft1.commit();
				tv1.setSelected(true);
				tv2.setSelected(false);
				tv3.setSelected(false);
				tv4.setSelected(false);
				tv5.setSelected(false);
			}
		});
		tv2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 开启一个事务
				ft1 = getFragmentManager().beginTransaction();
				// 添加一个Fragment对象
				SearchRoomFragment2 fg2 = new SearchRoomFragment2();
				ft1.add(R.id.fl_room, fg2);
				// 提交
				ft1.commit();
				tv1.setSelected(false);
				tv2.setSelected(true);
				tv3.setSelected(false);
				tv4.setSelected(false);
				tv5.setSelected(false);
			}
		});
		tv3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 开启一个事务
				ft1 = getFragmentManager().beginTransaction();
				// 添加一个Fragment对象
				SearchRoomFragment3 fg3 = new SearchRoomFragment3();
				ft1.add(R.id.fl_room, fg3);
				// 提交
				ft1.commit();
				tv1.setSelected(false);
				tv2.setSelected(false);
				tv3.setSelected(true);
				tv4.setSelected(false);
				tv5.setSelected(false);
			}
		});
		tv4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 开启一个事务
				ft1 = getFragmentManager().beginTransaction();
				// 添加一个Fragment对象
				SearchRoomFragment4 fg4 = new SearchRoomFragment4();
				ft1.add(R.id.fl_room, fg4);
				// 提交
				ft1.commit();
				tv1.setSelected(false);
				tv2.setSelected(false);
				tv3.setSelected(false);
				tv4.setSelected(true);
				tv5.setSelected(false);
			}
		});
		tv5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 开启一个事务
				ft1 = getFragmentManager().beginTransaction();
				// 添加一个Fragment对象
				SearchRoomFragment5 fg5 = new SearchRoomFragment5();
				ft1.add(R.id.fl_room, fg5);
				// 提交
				ft1.commit();
				tv1.setSelected(false);
				tv2.setSelected(false);
				tv3.setSelected(false);
				tv4.setSelected(false);
				tv5.setSelected(true);
			}
		});
		final View popupView = getActivity().getLayoutInflater().inflate(
				R.layout.room_pop, null);
		final PopupWindow popupWindow = new PopupWindow(popupView,
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, true);

		arrowIb.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.setBackgroundDrawable(new BitmapDrawable());
				popupWindow.showAsDropDown(v, 0, 11);
				flRoom.setAlpha(0.2f);
				popupWindow.setOnDismissListener(new OnDismissListener() {

					@Override
					public void onDismiss() {
						// TODO Auto-generated method stub

						flRoom.setAlpha(1f);
					}
				});
			}
		});
		room1 = (Button) popupView.findViewById(R.id.room1);
		room2 = (Button) popupView.findViewById(R.id.room2);
		room3 = (Button) popupView.findViewById(R.id.room3);
		room4 = (Button) popupView.findViewById(R.id.room4);
		room5 = (Button) popupView.findViewById(R.id.room5);
		room6 = (Button) popupView.findViewById(R.id.room6);
		room7 = (Button) popupView.findViewById(R.id.room7);
		room8 = (Button) popupView.findViewById(R.id.room8);
		room1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				room1.setSelected(true);
				room2.setSelected(false);
				room3.setSelected(false);
				room4.setSelected(false);
				room5.setSelected(false);
				room6.setSelected(false);
				room7.setSelected(false);
				room8.setSelected(false);
				tv1.setSelected(true);
				tv2.setSelected(false);
				tv3.setSelected(false);
				tv4.setSelected(false);
				tv5.setSelected(false);
				popupWindow.dismiss();
				ft1 = getFragmentManager().beginTransaction();
				SearchRoomFragment1 fg1 = new SearchRoomFragment1();
				ft1.add(R.id.fl_room, fg1);
				ft1.commit();
			}
		});
		room2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				room1.setSelected(false);
				room2.setSelected(true);
				room3.setSelected(false);
				room4.setSelected(false);
				room5.setSelected(false);
				room6.setSelected(false);
				room7.setSelected(false);
				room8.setSelected(false);
				tv1.setSelected(false);
				tv2.setSelected(true);
				tv3.setSelected(false);
				tv4.setSelected(false);
				tv5.setSelected(false);
				popupWindow.dismiss();
				ft1 = getFragmentManager().beginTransaction();
				SearchRoomFragment2 fg2 = new SearchRoomFragment2();
				ft1.add(R.id.fl_room, fg2);
				ft1.commit();
			}
		});
		room3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				room1.setSelected(false);
				room2.setSelected(false);
				room3.setSelected(true);
				room4.setSelected(false);
				room5.setSelected(false);
				room6.setSelected(false);
				room7.setSelected(false);
				room8.setSelected(false);
				tv1.setSelected(false);
				tv2.setSelected(false);
				tv3.setSelected(true);
				tv4.setSelected(false);
				tv5.setSelected(false);
				popupWindow.dismiss();
				ft1 = getFragmentManager().beginTransaction();
				SearchRoomFragment3 fg3 = new SearchRoomFragment3();
				ft1.add(R.id.fl_room, fg3);
				ft1.commit();
			}
		});
		room4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				room1.setSelected(false);
				room2.setSelected(false);
				room3.setSelected(false);
				room4.setSelected(true);
				room5.setSelected(false);
				room6.setSelected(false);
				room7.setSelected(false);
				room8.setSelected(false);
				tv1.setSelected(false);
				tv2.setSelected(false);
				tv3.setSelected(false);
				tv4.setSelected(true);
				tv5.setSelected(false);
				popupWindow.dismiss();
				ft1 = getFragmentManager().beginTransaction();
				SearchRoomFragment4 fg4 = new SearchRoomFragment4();
				ft1.add(R.id.fl_room, fg4);
				ft1.commit();
			}
		});
		room5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				room1.setSelected(false);
				room2.setSelected(false);
				room3.setSelected(false);
				room4.setSelected(false);
				room5.setSelected(true);
				room6.setSelected(false);
				room7.setSelected(false);
				room8.setSelected(false);
				tv1.setSelected(false);
				tv2.setSelected(false);
				tv3.setSelected(false);
				tv4.setSelected(false);
				tv5.setSelected(true);
				popupWindow.dismiss();
				ft1 = getFragmentManager().beginTransaction();
				SearchRoomFragment5 fg5 = new SearchRoomFragment5();
				ft1.add(R.id.fl_room, fg5);
				ft1.commit();
			}
		});
		room6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				room1.setSelected(false);
				room2.setSelected(false);
				room3.setSelected(false);
				room4.setSelected(false);
				room5.setSelected(false);
				room6.setSelected(true);
				room7.setSelected(false);
				room8.setSelected(false);
				popupWindow.dismiss();
				ft1 = getFragmentManager().beginTransaction();
				SearchRoomFragment1 fg1 = new SearchRoomFragment1();
				ft1.add(R.id.fl_room, fg1);
				ft1.commit();
			}
		});
		room7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				room1.setSelected(false);
				room2.setSelected(false);
				room3.setSelected(false);
				room4.setSelected(false);
				room5.setSelected(false);
				room6.setSelected(false);
				room7.setSelected(true);
				room8.setSelected(false);
				popupWindow.dismiss();
				ft1 = getFragmentManager().beginTransaction();
				SearchRoomFragment3 fg3 = new SearchRoomFragment3();
				ft1.add(R.id.fl_room, fg3);
				ft1.commit();
			}
		});
		room8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				room1.setSelected(false);
				room2.setSelected(false);
				room3.setSelected(false);
				room4.setSelected(false);
				room5.setSelected(false);
				room6.setSelected(false);
				room7.setSelected(false);
				room8.setSelected(true);
				popupWindow.dismiss();
				ft1 = getFragmentManager().beginTransaction();
				SearchRoomFragment4 fg4 = new SearchRoomFragment4();
				ft1.add(R.id.fl_room, fg4);
				ft1.commit();
			}
		});

	}
}
