package com.young.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.young.R;
import com.young.util.NetworkUtil;

public class LoginActivity extends Activity implements OnClickListener {

	public static final String USERNAME = "userName";
	public static final String PASSWORD = "passWord";
	private ImageView loginBackIb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginBackIb = (ImageView) findViewById(R.id.login_back_ib);
		loginBackIb.setOnClickListener(this);
		@SuppressWarnings("deprecation")
		final SharedPreferences sp = this.getSharedPreferences("userInfo",
				Context.MODE_WORLD_READABLE);
		String stuId = sp.getString("USER_NAME", "");
		if (!("".equals(stuId))) {
			Intent it = getIntent();
			String str = it.getStringExtra("Message");
			if (str.equals("查成绩")) {
				Intent it1 = new Intent(LoginActivity.this,
						ChoseTermsActivity.class);
				LoginActivity.this.startActivity(it1);
				finish();
			} else if (str.equals("地图")) {
				Intent it2 = new Intent(LoginActivity.this, MainActivity.class);
				LoginActivity.this.startActivity(it2);
				finish();
			} else if (str.equals("注销")) {
				Intent it3 = new Intent(LoginActivity.this, MainActivity.class);
				LoginActivity.this.startActivity(it3);
				finish();
			}

		} else {
			if (!NetworkUtil.isOpenNetwork()) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						LoginActivity.this);
				builder.setTitle("没有可用的网络？").setMessage("是否对网络进行设置？");
				builder.setPositiveButton("是",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = null;

								try {
									@SuppressWarnings("deprecation")
									String sdkVersion = android.os.Build.VERSION.SDK;
									if (Integer.valueOf(sdkVersion) > 10) {
										intent = new Intent(
												android.provider.Settings.ACTION_WIRELESS_SETTINGS);
									} else {
										intent = new Intent();
										ComponentName comp = new ComponentName(
												"com.android.settings",
												"com.android.settings.WirelessSettings");
										intent.setComponent(comp);
										intent.setAction("android.intent.action.VIEW");
									}
									LoginActivity.this.startActivity(intent);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						})
						.setNegativeButton("否",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								}).show();
			}
		}

		final EditText usernameEditText = (EditText) findViewById(R.id.username);
		final EditText passwordEditText = (EditText) findViewById(R.id.password);
		Button loginButton = (Button) findViewById(R.id.login_ok);

		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String username = usernameEditText.getText().toString();
				String password = passwordEditText.getText().toString();
				Intent it = getIntent();
				String str = it.getStringExtra("Message");
				if (str.equals("查成绩")) {
					Intent it1 = new Intent(LoginActivity.this,
							LogoScoreActivity.class);
					it1.putExtra(USERNAME, username);
					it1.putExtra(PASSWORD, password);
					LoginActivity.this.startActivity(it1);
					finish();
				} else if (str.equals("地图")) {
					Intent it2 = new Intent(LoginActivity.this,
							LogoMainActivity.class);
					it2.putExtra(USERNAME, username);
					it2.putExtra(PASSWORD, password);
					LoginActivity.this.startActivity(it2);
					finish();
				} else if (str.equals("注销")) {
					Intent it3 = new Intent(LoginActivity.this,
							LogoMainActivity.class);
					it3.putExtra(USERNAME, username);
					it3.putExtra(PASSWORD, password);
					LoginActivity.this.startActivity(it3);
					finish();
				}
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_back_ib:
			finish();
			break;
		}
	}
}
