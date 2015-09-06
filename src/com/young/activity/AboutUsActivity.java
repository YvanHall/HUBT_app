package com.young.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.young.R;

public class AboutUsActivity extends BaseActivity implements OnClickListener {

	private ImageView aboutUsBackIb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		aboutUsBackIb = (ImageView) findViewById(R.id.about_us_back_ib);
		aboutUsBackIb.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.about_us_back_ib:
			finish();
			break;
		}
	}

}
