package com.young.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.young.R;
import com.young.activity.EventActivity;
import com.young.activity.NewsEvent;
import com.young.adapter.AdapterForNews1;

@SuppressLint("NewApi")
public class NewsFragment1 extends Fragment {
	private View view;
	private ListView listNews;
	private ArrayList<HashMap<String, Object>> mdata;
	private String[] mData = { "腾讯发布高校创业榜 湖工大", "腾讯发布高校创业榜 湖工大",
			"腾讯发布高校创业榜 湖工大" };
	private String[] name = { "正式宣布开放以来，腾讯开放平台上的创业者人数达到500万，创业者…",
			"正式宣布开放以来，腾讯开放平台上的创业者人数达到500万，创业者…",
			"正式宣布开放以来，腾讯开放平台上的创业者人数达到500万，创业者…" };
	private int[] image = { R.drawable.competition_banner,
			R.drawable.first_focus_ucan, R.drawable.first_focus_ucan };
	private String[] date = { "2015-01-27", "2015-01-27", "2015-01-27" };

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.news_fragment1, null);
		mdata = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < mData.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", name[i]);
			map.put("num", mData[i]);
			map.put("image", image[i]);
			map.put("date", date[i]);
			mdata.add(map);
		}
		initView();
		return view;
	}

	private void initView() {
		// TODO Auto-generated method stub
		listNews = (ListView) view.findViewById(R.id.list_news1);
		AdapterForNews1 adapter = new AdapterForNews1(getActivity(), mdata);
		listNews.setAdapter(adapter);
		listNews.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Intent it = new Intent(getActivity(), NewsEvent.class);
					it.putExtra("NewsBarTitle", "公告");
					getActivity().startActivity(it);
					break;
				default:
					Intent it1 = new Intent(getActivity(), NewsEvent.class);
					it1.putExtra("NewsBarTitle", "公告");
					getActivity().startActivity(it1);
					break;
				}
			}
		});
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
