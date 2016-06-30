package com.example.fragment;

import com.example.Url.URLinterface;
import com.example.mywork.BbsActivity;
import com.example.mywork.R;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentBBS extends Fragment {

	private ListView listViewBbsLeft, listViewBbsRight;

	private int selectBBSListViewItem = 0;
	private View selectBBSView;
	private BBSListViewAdapter adapterRight = null;

	private int images[] = new int[] { R.drawable.bbs_icon_0,
			R.drawable.bbs_icon_1, R.drawable.bbs_icon_2,
			R.drawable.bbs_icon_3, R.drawable.bbs_icon_4,
			R.drawable.bbs_icon_5, R.drawable.bbs_icon_6 };

	private String[][] arrR = new String[7][];

	private String[] arrRight;

	private boolean isflag = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_bbs, null);

		listViewBbsLeft = (ListView) v.findViewById(R.id.listViewBbsLeft);
		listViewBbsRight = (ListView) v.findViewById(R.id.listViewBbsRight);

		String[] arrLeft = getResources().getStringArray(R.array.bbsListL);

		listViewBbsLeft.setAdapter(new BBSListViewAdapter(arrLeft, 0));

		arrR[0] = getResources().getStringArray(R.array.bbsListR0);
		arrR[1] = getResources().getStringArray(R.array.bbsListR1);
		arrR[2] = getResources().getStringArray(R.array.bbsListR2);
		arrR[3] = getResources().getStringArray(R.array.bbsListR3);
		arrR[4] = getResources().getStringArray(R.array.bbsListR4);
		arrR[5] = getResources().getStringArray(R.array.bbsListR5);
		arrR[6] = getResources().getStringArray(R.array.bbsListR6);

		arrRight = arrR[0];

		adapterRight = new BBSListViewAdapter(arrRight, 1);
		listViewBbsRight.setAdapter(adapterRight);

		BBSListViewListener listener = new BBSListViewListener();

		listViewBbsLeft.setOnItemClickListener(listener);
		listViewBbsRight.setOnItemClickListener(listener);

		return v;
	}

	class BBSListViewListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
             int index = position;
			if(listViewBbsLeft.equals(parent)){
				arrRight = arrR[position];
				adapterRight = new BBSListViewAdapter(arrRight, 1);
				listViewBbsRight.setAdapter(adapterRight);

				changeItem(view, position);
			}
			else if(listViewBbsRight.equals(parent)){
				Intent intent = new Intent(getActivity(),BbsActivity.class);
				String title = arrR[selectBBSListViewItem][position];
				String url = URLinterface.URLBBS_RT;
				Bundle bundle = new Bundle();
				bundle.putString("title", title);
				bundle.putString("url", url);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		}

	}

	public void changeItem(View view, int position) {
		if(position == selectBBSListViewItem){
			return;
		}

		ImageView i = (ImageView) selectBBSView.findViewById(R.id.imageViewBg);
		i.setImageResource(R.drawable.bbs_item_main_class_bg_normal);

		TextView t = (TextView) selectBBSView.findViewById(R.id.textViewgu);
		t.setTextColor(Color.BLACK);

		selectBBSListViewItem = position;
		selectBBSView = view;

		ImageView i2 = (ImageView) selectBBSView.findViewById(R.id.imageViewBg);
		i2.setImageResource(R.drawable.bbs_item_main_class_bg_selected);

		TextView t2 = (TextView) selectBBSView.findViewById(R.id.textViewgu);
		t2.setTextColor(Color.GRAY);
	}

	class BBSListViewAdapter extends BaseAdapter {

		private String[] arr;
		private int flag;

		private LayoutInflater inflater = null;

		public BBSListViewAdapter(String[] arr, int flag) {
			super();
			this.arr = arr;
			this.flag = flag;
			inflater = LayoutInflater.from(getActivity());
		}

		@Override
		public int getCount() {
			return arr.length;
		}

		@Override
		public Object getItem(int position) {
			return arr[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.item_bbs_listview, null);
			}

			TextView textView = (TextView) convertView
					.findViewById(R.id.textViewgu);
			textView.setText(arr[position]);

			ImageView imageViewBg = (ImageView) convertView
					.findViewById(R.id.imageViewBg);

			if (flag == 0) {
				textView.setTextSize(15);

				ImageView imageView = (ImageView) convertView
						.findViewById(R.id.imageViewgu);
				imageView.setImageResource(images[position]);

				if (position == selectBBSListViewItem) {
					textView.setTextColor(Color.GRAY);
					imageViewBg
					.setImageResource(R.drawable.bbs_item_main_class_bg_selected);
				} else {
					textView.setTextColor(Color.BLACK);
					imageViewBg
					.setImageResource(R.drawable.bbs_item_main_class_bg_normal);
				}

				if(isflag){
					selectBBSView = convertView;
					isflag = false;
				}
			} else if (flag == 1) {
				textView.setTextSize(13);
				imageViewBg
				.setImageResource(R.drawable.bbs_item_sub_class_bg_normal);
			}
			return convertView;
		}

	}
}
