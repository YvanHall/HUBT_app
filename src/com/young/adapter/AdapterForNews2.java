package com.young.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.R;

public class AdapterForNews2 extends BaseAdapter {
	private Context mcontext;
	private ArrayList<HashMap<String, Object>> mData;

	public AdapterForNews2(Context mcontext,
			ArrayList<HashMap<String, Object>> mData) {
		// TODO Auto-generated constructor stub
		this.mcontext = mcontext;
		this.mData = mData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(mcontext);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.news_listitem, null);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.img = (ImageView) convertView.findViewById(R.id.image);
		holder.tv_name = (TextView) convertView.findViewById(R.id.name);
		holder.tv_title = (TextView) convertView.findViewById(R.id.title);
		holder.tv_date = (TextView) convertView.findViewById(R.id.date);
		holder.img.setBackgroundResource((Integer) mData.get(position).get(
				"image"));
		holder.tv_name.setText((String) mData.get(position).get("name"));
		holder.tv_title.setText((String) mData.get(position).get("num"));
		holder.tv_date.setText((String) mData.get(position).get("date"));

		return convertView;
	}

	class ViewHolder {
		private ImageView img;
		private TextView tv_name;
		private TextView tv_title;
		private TextView tv_date;
	}

}
