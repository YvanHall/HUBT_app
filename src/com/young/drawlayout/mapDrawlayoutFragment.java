package com.young.drawlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.young.R;
import com.young.activity.MapActivity;

public class mapDrawlayoutFragment extends Fragment {
	private View view;
	public ImageView menuBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.map_activity, null);
		initView();
		return view;
	}

	public void initView() {
		menuBtn = (ImageView) view.findViewById(R.id.map_sidebar_ib);
		// 点击打开左边菜单
		menuBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MapActivity) getActivity()).openLeftLayout();
			}
		});
	}
}
