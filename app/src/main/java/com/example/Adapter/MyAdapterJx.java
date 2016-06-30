package com.example.Adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.Task.ImageViewFgTask;
import com.example.body.JingXuan;
import com.example.mywork.JxWebActivity;
import com.example.mywork.R;

public class MyAdapterJx extends BaseAdapter {

	private Context context ;
	private List<JingXuan> data ;

	View leftViewUp = null;
	View rightViewUp = null;
	View firstView = null;
	View secondView = null;
	View thirdView = null;
	View fourthView = null;

	private Map<String, Bitmap> mapcontent = new HashMap<>();

	public MyAdapterJx(Context context, List<JingXuan> data) {
		super();
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size()/6;
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;

		if(convertView == null){
			convertView = View.inflate(context, R.layout.item_news_list_jx, null);
			vh = new ViewHolder();
			leftViewUp = convertView.findViewById(R.id.item280280_0);
			rightViewUp = convertView.findViewById(R.id.item280280_1);
			firstView = convertView.findViewById(R.id.item160120_0);
			secondView = convertView.findViewById(R.id.item160120_1);
			thirdView = convertView.findViewById(R.id.item160120_2);
			fourthView = convertView.findViewById(R.id.item160120_3);

			vh.imageUpLfte = (ImageView) leftViewUp.findViewById(R.id.imageView_jx_gu);
			vh.textUpLeft = (TextView) leftViewUp.findViewById(R.id.title_jx_gu);
			vh.imageUpRight = (ImageView) rightViewUp.findViewById(R.id.imageView_jx_gu);
			vh.textUpRight = (TextView) rightViewUp.findViewById(R.id.title_jx_gu);

			vh.imageFirst = (ImageView) firstView.findViewById(R.id.imageView_jxbottem_gu);
			vh.textFirst = (TextView) firstView.findViewById(R.id.title_jxbottem_gu);
			vh.dateFirst = (TextView) firstView.findViewById(R.id.date_jxbottem_gu);
			vh.imageSecond = (ImageView) secondView.findViewById(R.id.imageView_jxbottem_gu);
			vh.textSecond = (TextView) secondView.findViewById(R.id.title_jxbottem_gu);
			vh.dateSecond = (TextView) secondView.findViewById(R.id.date_jxbottem_gu);
			vh.imageThird = (ImageView) thirdView.findViewById(R.id.imageView_jxbottem_gu);
			vh.textThird = (TextView) thirdView.findViewById(R.id.title_jxbottem_gu);
			vh.dateThird = (TextView) thirdView.findViewById(R.id.date_jxbottem_gu);
			vh.imageFourth = (ImageView) fourthView.findViewById(R.id.imageView_jxbottem_gu);
			vh.textFourth = (TextView) fourthView.findViewById(R.id.title_jxbottem_gu);
			vh.dateFourth = (TextView) fourthView.findViewById(R.id.date_jxbottem_gu);
			convertView.setTag(vh);

			leftViewUp.setTag(data.get(position*6).web_url);
			rightViewUp.setTag(data.get(position*6+1).web_url);
			firstView.setTag(data.get(position*6+2).web_url);
			secondView.setTag(data.get(position*6+3).web_url);
			thirdView.setTag(data.get(position*6+4).web_url);
			fourthView.setTag(data.get(position*6+5).web_url);
			
			setonlistener(leftViewUp);
			setonlistener(rightViewUp);
			setonlistener(firstView);
			setonlistener(secondView);
			setonlistener(thirdView);
			setonlistener(fourthView);

		}else{
			vh = (ViewHolder) convertView.getTag();
		}

		JingXuan leftUp = data.get(6*position);
		JingXuan rightUp = data.get(6*position+1);
		JingXuan first = data.get(6*position+2);
		JingXuan second = data.get(6*position+3);
		JingXuan third = data.get(6*position+4);
		JingXuan fourth = data.get(6*position+5);

		vh.textUpLeft.setText(leftUp.title);
		vh.textUpRight.setText(rightUp.title);
		vh.textFirst.setText(first.title);
		vh.dateFirst.setText(first.date);
		vh.textSecond.setText(second.title);
		vh.dateSecond.setText(second.date);
		vh.textThird.setText(third.title);
		vh.dateThird.setText(third.date);
		vh.textFourth.setText(fourth.title);
		vh.dateFourth.setText(fourth.date);

		if(mapcontent.containsKey(leftUp.pic_url)){
			vh.imageUpLfte.setImageBitmap(mapcontent.get(leftUp.pic_url));
		}else{
			new ImageViewFgTask(context, leftUp.pic_url, vh.imageUpLfte, mapcontent)
			.execute(leftUp.pic_url);
		}

		if(mapcontent.containsKey(rightUp.pic_url)){
			vh.imageUpRight.setImageBitmap(mapcontent.get(rightUp.pic_url));
		}else{
			new ImageViewFgTask(context, rightUp.pic_url, vh.imageUpRight, mapcontent)
			.execute(rightUp.pic_url);
		}

		if(mapcontent.containsKey(first.pic_url)){
			vh.imageFirst.setImageBitmap(mapcontent.get(first.pic_url));
		}else{
			new ImageViewFgTask(context, first.pic_url, vh.imageFirst, mapcontent)
			.execute(first.pic_url);
		}

		if(mapcontent.containsKey(second.pic_url)){
			vh.imageSecond.setImageBitmap(mapcontent.get(second.pic_url));
		}else{
			new ImageViewFgTask(context, second.pic_url, vh.imageSecond, mapcontent)
			.execute(second.pic_url);
		}

		if(mapcontent.containsKey(third.pic_url)){
			vh.imageThird.setImageBitmap(mapcontent.get(third.pic_url));
		}else{
			new ImageViewFgTask(context, third.pic_url, vh.imageThird, mapcontent)
			.execute(third.pic_url);
		}

		if(mapcontent.containsKey(fourth.pic_url)){
			vh.imageFourth.setImageBitmap(mapcontent.get(fourth.pic_url));
		}else{
			new ImageViewFgTask(context, fourth.pic_url, vh.imageFourth, mapcontent)
			.execute(fourth.pic_url);
		}

		return convertView;
	}

	class ViewHolder{
		ImageView imageUpLfte;	
		ImageView imageUpRight;
		TextView textUpLeft ;
		TextView textUpRight ;
		ImageView imageFirst;
		TextView textFirst;
		TextView dateFirst;
		ImageView imageSecond;
		TextView textSecond;
		TextView dateSecond;
		ImageView imageThird;
		TextView textThird;
		TextView dateThird;
		ImageView imageFourth;
		TextView textFourth;
		TextView dateFourth;
	}

	public void setonlistener(View v){
		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = v.getTag().toString();
				Intent intent = new Intent(context,JxWebActivity.class);
				intent.putExtra("url", url);
				context.startActivity(intent);
			}
		});
	}
}
