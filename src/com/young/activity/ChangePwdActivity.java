package com.young.activity;

import java.io.IOException;

import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.young.R;
import com.young.business.HBUT;
import com.young.util.NetworkUtil;

public class ChangePwdActivity extends BaseActivity implements OnClickListener {
	private String stuId;
	private String password;
	private SharedPreferences sp;
	private ImageView changepwdBackIb;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepwd);
		sp = ChangePwdActivity.this.getSharedPreferences("userInfo",
				Context.MODE_WORLD_READABLE);
		getUserIdAndPassWord();
		Button modifyButton = (Button) findViewById(R.id.changeButton);
		changepwdBackIb = (ImageView) findViewById(R.id.changePwd_back_ib);
		changepwdBackIb.setOnClickListener(this);
		modifyButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.changeButton:
			new Thread(new Runnable() {

				@Override
				public void run() {
					EditText oldPass = (EditText) findViewById(R.id.change_oldpw);
					EditText newPass = (EditText) findViewById(R.id.change_newpw);
					EditText conPass = (EditText) findViewById(R.id.change_conpw);

					String oldPassword = oldPass.getText().toString();
					String newPassword = newPass.getText().toString();
					String conPassword = conPass.getText().toString();
					if (!NetworkUtil.isOpenNetwork()) {
						finish();
						Looper.prepare();
						Toast.makeText(getBaseContext(), "无网络连接",
								Toast.LENGTH_LONG).show();
						Looper.loop();
					} else {
						HBUT hbut = HBUT.getInstance();
						try {
							hbut.login(stuId, password);
							String message = hbut.changePass(oldPassword,
									newPassword, conPassword);
							Looper.prepare();

							Toast.makeText(getBaseContext(), message,
									Toast.LENGTH_LONG).show();
							if ("保存成功".equals(message)) {
								SharedPreferences.Editor editor = sp.edit();
								editor.putString("PASSWORD", newPassword);
								editor.commit();
								ChangePwdActivity.this.finish();
							}
							Looper.loop();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			break;
		case R.id.changePwd_back_ib:
			finish();
			break;
		}
	}

	private void getUserIdAndPassWord() {
		stuId = sp.getString("USER_NAME", "");
		password = sp.getString("PASSWORD", "");
	}
}
