package com.young.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.R;

public class EventActivity extends Activity implements OnClickListener {
	private ImageView eventBackIb;
	private TextView publicTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		eventBackIb = (ImageView) findViewById(R.id.event_back_ib);
		publicTv = (TextView) findViewById(R.id.public_tv);
		eventBackIb.setOnClickListener(this);
		Intent it = getIntent();
		String thing = it.getStringExtra("barTitle");
		if (thing.equals("轻工")) {
			publicTv.setText("轻工");
		} else if (thing.equals("机械")) {
			publicTv.setText("机械");
		} else if (thing.equals("计算机")) {
			publicTv.setText("计算机");
		} else if (thing.equals("艺设")) {
			publicTv.setText("艺设");
		} else if (thing.equals("土木")) {
			publicTv.setText("土木");
		} else if (thing.equals("工设")) {
			publicTv.setText("工设");
		} else if (thing.equals("经管")) {
			publicTv.setText("经管");
		} else if (thing.equals("外国语")) {
			publicTv.setText("外国语");
		} else if (thing.equals("马克思")) {
			publicTv.setText("马克思");
		} else if (thing.equals("工程")) {
			publicTv.setText("工程");
		} else if (thing.equals("理学院")) {
			publicTv.setText("理学院");
		} else if (thing.equals("电气")) {
			publicTv.setText("电气");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.event_back_ib:
			finish();
			break;
		}
	}
}
