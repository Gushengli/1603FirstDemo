package com.example.Adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.body.BbsRt;
import com.example.mywork.R;

public class BbsAdapter extends BaseAdapter{

	private Context context;
	private List<BbsRt> list;
	

	public BbsAdapter(Context context, List<BbsRt> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.forum_item, null);
			vh = new ViewHolder();
			vh.tv1 = (TextView) convertView.findViewById(R.id.textView1_gu);
			vh.tv2 = (TextView) convertView.findViewById(R.id.textView2_gu);
			vh.tv3 = (TextView) convertView.findViewById(R.id.textView3_gu);
			vh.tv4 = (TextView) convertView.findViewById(R.id.textView4_gu);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		
		BbsRt bbs = list.get(position);
		vh.tv1.setText(bbs.title);
		vh.tv2.setText(bbs.author);
		vh.tv3.setText(bbs.views+"");
		vh.tv4.setText(bbs.reply+"");
		return convertView;
	}

	class ViewHolder{
		TextView tv1;
		TextView tv2;
		TextView tv3;
		TextView tv4;
	}
}
