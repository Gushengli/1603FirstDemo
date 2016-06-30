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
import com.example.body.Scenery;
import com.example.mywork.FgWebActivity;
import com.example.mywork.R;

public class MyAdapterFg extends BaseAdapter{

	private Context context ;
	private List<Scenery> data ;
	private View leftView = null;
	private View rightView = null;
	Scenery leftscenery;
	Scenery rightscenery;
	private Map<String, Bitmap> mapcontent = new HashMap<>();

	public MyAdapterFg(Context context, List<Scenery> data) {
		super();
		this.context = context;
		this.data = data;
	}


	@Override
	public int getCount() {
		return data.size()/2;
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
			convertView = View.inflate(context, R.layout.item_pic_list, null);
			vh = new ViewHolder();
			leftView = convertView.findViewById(R.id.item280210_0);
			rightView = convertView.findViewById(R.id.item280210_1);
			vh.imageleft = (ImageView) leftView.findViewById(R.id.imageView_FG);
			vh.textleft = (TextView) leftView.findViewById(R.id.title_FG);
			vh.imageright = (ImageView) rightView.findViewById(R.id.imageView_FG);
			vh.textright = (TextView)rightView.findViewById(R.id.title_FG);
			convertView.setTag(vh);
			
			leftView.setTag(data.get(position*2).detail_url);
			rightView.setTag(data.get(position*2+1).detail_url);
			setonlistener();
			
		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		vh.imageleft.setTag(leftView);
		vh.imageright.setTag(rightView);
		leftscenery = data.get(2*position);
		rightscenery = data.get(2*position + 1);
		
		vh.textleft.setText( leftscenery.getTitle());
		vh.textright.setText(rightscenery.getTitle());

		if(mapcontent.containsKey(leftscenery.pic_url)){
			vh.imageleft.setImageBitmap(mapcontent.get(leftscenery.pic_url));
		}else{
			new ImageViewFgTask(context, leftscenery.pic_url, vh.imageleft, mapcontent)
			.execute(leftscenery.pic_url);
		}

		if(mapcontent.containsKey(rightscenery.pic_url)){
			vh.imageleft.setImageBitmap(mapcontent.get(rightscenery.pic_url));
		}else{
			new ImageViewFgTask(context, rightscenery.pic_url, vh.imageright, mapcontent)
			.execute(rightscenery.pic_url);

		}
		return convertView;
	}

	class ViewHolder{
		ImageView imageleft;	
		ImageView imageright;
		TextView textleft ;
		TextView textright ;
	}

	public void setonlistener(){
		leftView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = v.getTag().toString();
				Intent intent = new Intent(context,FgWebActivity.class);
				intent.putExtra("url", url);
				context.startActivity(intent);
			}
		});

		rightView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = v.getTag().toString();
				Intent intent = new Intent(context,FgWebActivity.class);
				intent.putExtra("url", url);
				context.startActivity(intent);
			}
		});
	}
}
