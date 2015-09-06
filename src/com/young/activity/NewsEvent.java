package com.young.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.R;

public class NewsEvent extends Activity implements OnClickListener {
	private ImageView eventBackIb;
	private TextView newsEventTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_event);
		eventBackIb = (ImageView) findViewById(R.id.news_event_back_ib);
		newsEventTv = (TextView) findViewById(R.id.news_event_tv);
		eventBackIb.setOnClickListener(this);
		Intent it = getIntent();
		String thing = it.getStringExtra("NewsBarTitle");
		if (thing.equals("公告")) {
			newsEventTv.setText("公告");
		} else if (thing.equals("讲座")) {
			newsEventTv.setText("讲座");
		} else if (thing.equals("活动")) {
			newsEventTv.setText("活动");
		} else if (thing.equals("招聘")) {
			newsEventTv.setText("招聘");
		} else if (thing.equals("失物招领")) {
			newsEventTv.setText("失物招领");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.news_event_back_ib:
			finish();
			break;
		}
	}
}
