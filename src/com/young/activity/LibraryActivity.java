package com.young.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;

import com.young.R;

@SuppressLint("SetJavaScriptEnabled")
public class LibraryActivity extends Activity implements OnClickListener {
	private ImageView libraryBackIv;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.library_activity);
		libraryBackIv = (ImageView) findViewById(R.id.library_back_ib);
		libraryBackIv.setOnClickListener(this);
		WebView wv = (WebView) findViewById(R.id.word_web_view);
		wv.loadUrl("http://lib.hbut.edu.cn/v13/index.asp");
		WebSettings websettings = wv.getSettings();
		websettings.setJavaScriptEnabled(true);

		websettings.setJavaScriptEnabled(true);
		websettings.setJavaScriptCanOpenWindowsAutomatically(true);
		websettings.setUseWideViewPort(true);// 关键点

		websettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

		websettings.setDisplayZoomControls(false);
		websettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
		websettings.setAllowFileAccess(true); // 允许访问文件
		websettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
		websettings.setSupportZoom(true); // 支持缩放
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.library_back_ib:
			finish();
			break;
		}
	}
}
