package com.young.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.young.R;

public class AdapterForAcademyNews extends BaseAdapter {
	private Context mcontext;
	private ArrayList<HashMap<String, Object>> mData;

	public AdapterForAcademyNews(Context mcontext,
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
			convertView = inflater.inflate(R.layout.aca_news_listitem, null);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
		holder.tv_thing = (TextView) convertView.findViewById(R.id.tv_thing);
		holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
		holder.tv_title.setText((String) mData.get(position).get("title"));
		holder.tv_thing.setText((String) mData.get(position).get("thing"));
		holder.tv_date.setText((String) mData.get(position).get("date"));

		return convertView;
	}

	class ViewHolder {
		private TextView tv_title;
		private TextView tv_thing;
		private TextView tv_date;
	}

}
