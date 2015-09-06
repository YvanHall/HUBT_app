package com.young.drawlayout;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.young.R;
import com.young.activity.AcademyNewsActivity;
import com.young.activity.EventActivity;
import com.young.adapter.AdapterForAcademyNews;

public class academyDrawlayoutFragment extends Fragment {
	private View view;
	public ImageView menuBtn;
	private ImageView acaNewsIb;
	private ListView listAca;
	private LinearLayout acall;

	private TextView acaTitle;
	private Button aca1;
	private Button aca2;
	private Button aca3;
	private Button aca4;
	private Button aca5;
	private Button aca6;
	private Button aca7;
	private Button aca8;
	private Button aca9;
	private Button aca10;
	private Button aca11;
	private Button aca12;

	private ArrayList<HashMap<String, Object>> mdata;
	private String[] title = { "腾讯发布高校创业榜 湖工大", "腾讯发布高校创业榜 湖工大",
			"腾讯发布高校创业榜 湖工大", "腾讯发布高校创业榜 湖工大", "腾讯发布高校创业榜 湖工大", "腾讯发布高校创业榜 湖工大" };
	private String[] thing = {
			"腾讯正式宣布开放以来，腾讯开放平台上的创业者人数达到500万，创业者公司总市值达到了2000亿。",
			"腾讯正式宣布开放以来，腾讯开放平台上的创业者人数达到500万，创业者公司总市值达到了2000亿。",
			"腾讯正式宣布开放以来，腾讯开放平台上的创业者人数达到500万，创业者公司总市值达到了2000亿。",
			"腾讯正式宣布开放以来，腾讯开放平台上的创业者人数达到500万，创业者公司总市值达到了2000亿。",
			"腾讯正式宣布开放以来，腾讯开放平台上的创业者人数达到500万，创业者公司总市值达到了2000亿。",
			"腾讯正式宣布开放以来，腾讯开放平台上的创业者人数达到500万，创业者公司总市值达到了2000亿。" };
	private String[] date = { "2015-01-27", "2015-01-27", "2015-01-27",
			"2015-01-27", "2015-01-27", "2015-01-27" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.academy_news_activity, null);
		mdata = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < title.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("title", title[i]);
			map.put("thing", thing[i]);
			map.put("date", date[i]);
			mdata.add(map);
		}
		initView();
		return view;
	}

	public void initView() {
		menuBtn = (ImageView) view.findViewById(R.id.academy_news_sidebar_ib);
		acall = (LinearLayout) view.findViewById(R.id.aca_ll);
		acaTitle = (TextView) view.findViewById(R.id.aca_title);
		listAca = (ListView) view.findViewById(R.id.list_aca);
		AdapterForAcademyNews adapter = new AdapterForAcademyNews(
				getActivity(), mdata);
		listAca.setAdapter(adapter);
		listAca.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Intent it = new Intent(getActivity(), EventActivity.class);
					it.putExtra("barTitle", "艺设");
					getActivity().startActivity(it);
					break;
				default:
					Intent it1 = new Intent(getActivity(), EventActivity.class);
					it1.putExtra("barTitle", "艺设");
					getActivity().startActivity(it1);
					break;
				}
			}
		});
		// 点击打开左边菜单
		menuBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((AcademyNewsActivity) getActivity()).openLeftLayout();
			}
		});
		final View popupView = getActivity().getLayoutInflater().inflate(
				R.layout.aca_pop, null);
		final PopupWindow popupWindow = new PopupWindow(popupView,
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		acall.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.showAsDropDown(v, 0, 23);
				listAca.setAlpha(0.2f);
				// backgroundAlpha(0.7f);
				popupWindow.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss() {
						// TODO Auto-generated method stub
						// backgroundAlpha(1f);
						listAca.setAlpha(1f);
					}
				});
			}
		});
		aca1 = (Button) popupView.findViewById(R.id.aca_1);
		aca2 = (Button) popupView.findViewById(R.id.aca_2);
		aca3 = (Button) popupView.findViewById(R.id.aca_3);
		aca4 = (Button) popupView.findViewById(R.id.aca_4);
		aca5 = (Button) popupView.findViewById(R.id.aca_5);
		aca6 = (Button) popupView.findViewById(R.id.aca_6);
		aca7 = (Button) popupView.findViewById(R.id.aca_7);
		aca8 = (Button) popupView.findViewById(R.id.aca_8);
		aca9 = (Button) popupView.findViewById(R.id.aca_9);
		aca10 = (Button) popupView.findViewById(R.id.aca_10);
		aca11 = (Button) popupView.findViewById(R.id.aca_11);
		aca12 = (Button) popupView.findViewById(R.id.aca_12);
		aca4.setSelected(true);
		aca1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(true);
				aca2.setSelected(false);
				aca3.setSelected(false);
				aca4.setSelected(false);
				aca5.setSelected(false);
				aca6.setSelected(false);
				aca7.setSelected(false);
				aca8.setSelected(false);
				aca9.setSelected(false);
				aca10.setSelected(false);
				aca11.setSelected(false);
				aca12.setSelected(false);
				acaTitle.setText("轻工");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "轻工");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "轻工");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(true);
				aca3.setSelected(false);
				aca4.setSelected(false);
				aca5.setSelected(false);
				aca6.setSelected(false);
				aca7.setSelected(false);
				aca8.setSelected(false);
				aca9.setSelected(false);
				aca10.setSelected(false);
				aca11.setSelected(false);
				aca12.setSelected(false);
				acaTitle.setText("机械");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "机械");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "机械");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(false);
				aca3.setSelected(true);
				aca4.setSelected(false);
				aca5.setSelected(false);
				aca6.setSelected(false);
				aca7.setSelected(false);
				aca8.setSelected(false);
				aca9.setSelected(false);
				aca10.setSelected(false);
				aca11.setSelected(false);
				aca12.setSelected(false);
				acaTitle.setText("计算机");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "计算机");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "计算机");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(false);
				aca3.setSelected(false);
				aca4.setSelected(true);
				aca5.setSelected(false);
				aca6.setSelected(false);
				aca7.setSelected(false);
				aca8.setSelected(false);
				aca9.setSelected(false);
				aca10.setSelected(false);
				aca11.setSelected(false);
				aca12.setSelected(false);
				acaTitle.setText("艺设");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "艺设");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "艺设");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(false);
				aca3.setSelected(false);
				aca4.setSelected(false);
				aca5.setSelected(true);
				aca6.setSelected(false);
				aca7.setSelected(false);
				aca8.setSelected(false);
				aca9.setSelected(false);
				aca10.setSelected(false);
				aca11.setSelected(false);
				aca12.setSelected(false);
				acaTitle.setText("土木");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "土木");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "土木");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(false);
				aca3.setSelected(false);
				aca4.setSelected(false);
				aca5.setSelected(false);
				aca6.setSelected(true);
				aca7.setSelected(false);
				aca8.setSelected(false);
				aca9.setSelected(false);
				aca10.setSelected(false);
				aca11.setSelected(false);
				aca12.setSelected(false);
				acaTitle.setText("工设");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "工设");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "工设");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(false);
				aca3.setSelected(false);
				aca4.setSelected(false);
				aca5.setSelected(false);
				aca6.setSelected(false);
				aca7.setSelected(true);
				aca8.setSelected(false);
				aca9.setSelected(false);
				aca10.setSelected(false);
				aca11.setSelected(false);
				aca12.setSelected(false);
				acaTitle.setText("经管");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "经管");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "经管");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(false);
				aca3.setSelected(false);
				aca4.setSelected(false);
				aca5.setSelected(false);
				aca6.setSelected(false);
				aca7.setSelected(false);
				aca8.setSelected(true);
				aca9.setSelected(false);
				aca10.setSelected(false);
				aca11.setSelected(false);
				aca12.setSelected(false);
				acaTitle.setText("外国语");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "外国语");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "外国语");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(false);
				aca3.setSelected(false);
				aca4.setSelected(false);
				aca5.setSelected(false);
				aca6.setSelected(false);
				aca7.setSelected(false);
				aca8.setSelected(false);
				aca9.setSelected(true);
				aca10.setSelected(false);
				aca11.setSelected(false);
				aca12.setSelected(false);
				acaTitle.setText("马克思");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "马克思");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "马克思");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(false);
				aca3.setSelected(false);
				aca4.setSelected(false);
				aca5.setSelected(false);
				aca6.setSelected(false);
				aca7.setSelected(false);
				aca8.setSelected(false);
				aca9.setSelected(false);
				aca10.setSelected(true);
				aca11.setSelected(false);
				aca12.setSelected(false);
				acaTitle.setText("工程");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "工程");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "工程");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca11.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(false);
				aca3.setSelected(false);
				aca4.setSelected(false);
				aca5.setSelected(false);
				aca6.setSelected(false);
				aca7.setSelected(false);
				aca8.setSelected(false);
				aca9.setSelected(false);
				aca10.setSelected(false);
				aca11.setSelected(true);
				aca12.setSelected(false);
				acaTitle.setText("理学院");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "理学院");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "理学院");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
		aca12.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				aca1.setSelected(false);
				aca2.setSelected(false);
				aca3.setSelected(false);
				aca4.setSelected(false);
				aca5.setSelected(false);
				aca6.setSelected(false);
				aca7.setSelected(false);
				aca8.setSelected(false);
				aca9.setSelected(false);
				aca10.setSelected(false);
				aca11.setSelected(false);
				aca12.setSelected(true);
				acaTitle.setText("电气");
				popupWindow.dismiss();
				listAca.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View v,
							int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							Intent it = new Intent(getActivity(),
									EventActivity.class);
							it.putExtra("barTitle", "电气");
							getActivity().startActivity(it);
							break;
						default:
							Intent it1 = new Intent(getActivity(),
									EventActivity.class);
							it1.putExtra("barTitle", "电气");
							getActivity().startActivity(it1);
							break;
						}
					}
				});
			}
		});
	}
}
