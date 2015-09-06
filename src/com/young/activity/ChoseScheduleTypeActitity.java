package com.young.activity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.young.R;

public class ChoseScheduleTypeActitity extends Activity implements
		OnClickListener {
	private HorizontalScrollView hScrollView, hScrollView1, hScrollView2,
			hScrollView3, hScrollView4, hScrollView5;
	private ImageView tableBackIb;
	private LinearLayout scheduleIb;
	private LinearLayout llSchedule;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule_activity);
		hScrollView = (HorizontalScrollView) findViewById(R.id.h_sview);
		hScrollView.setHorizontalScrollBarEnabled(false);
		hScrollView1 = (HorizontalScrollView) findViewById(R.id.h_sview_1);
		hScrollView1.setHorizontalScrollBarEnabled(false);
		hScrollView2 = (HorizontalScrollView) findViewById(R.id.h_sview_2);
		hScrollView2.setHorizontalScrollBarEnabled(false);
		hScrollView3 = (HorizontalScrollView) findViewById(R.id.h_sview_3);
		hScrollView3.setHorizontalScrollBarEnabled(false);
		hScrollView4 = (HorizontalScrollView) findViewById(R.id.h_sview_4);
		hScrollView4.setHorizontalScrollBarEnabled(false);
		hScrollView5 = (HorizontalScrollView) findViewById(R.id.h_sview_5);
		hScrollView5.setHorizontalScrollBarEnabled(false);
		tableBackIb = (ImageView) findViewById(R.id.table_back_ib);
		scheduleIb = (LinearLayout) findViewById(R.id.week_bar_ll);
		llSchedule = (LinearLayout) findViewById(R.id.ll_schedule);
		scheduleIb.setOnClickListener(this);
		tableBackIb.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.table_back_ib:
			finish();
			break;
		case R.id.week_bar_ll:
			View popupView = this.getLayoutInflater().inflate(
					R.layout.schedule_pop, null);
			HorizontalScrollView hsviewWeek = (HorizontalScrollView) popupView
					.findViewById(R.id.hsview_week);
			hsviewWeek.setHorizontalScrollBarEnabled(false);
			PopupWindow popupWindow = new PopupWindow(popupView,
					ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT, true);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
			popupWindow.showAsDropDown(v, 0, 23);
			llSchedule.setAlpha(0.2f);
			popupWindow.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					// TODO Auto-generated method stub
					llSchedule.setAlpha(1.0f);
				}
			});
			break;
		}
	}

}
