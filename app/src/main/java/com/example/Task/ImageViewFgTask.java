package com.example.Task;

import java.util.Map;

import com.example.UtilTools.HttpdownUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

public class ImageViewFgTask extends AsyncTask<String, Void, byte[]> {

	private Context context = null;
	private String pic_url = null;
	private ImageView iv = null;
	private Map<String,Bitmap> mapcontent = null;
	
	public ImageViewFgTask(Context context, String pic_url, ImageView iv,
			Map<String, Bitmap> mapcontent) {
		super();
		this.context = context;
		this.pic_url = pic_url;
		this.iv = iv;
		this.mapcontent = mapcontent;
	}

	public ImageViewFgTask(Context context, String pic_url, ImageView iv) {
		super();
		this.context = context;
		this.pic_url = pic_url;
		this.iv = iv;
	}

	@Override
	protected byte[] doInBackground(String... params) {
		return HttpdownUtil.getdown(params[0]);
	}

	@Override
	protected void onPostExecute(byte[] result) {
		super.onPostExecute(result);
		
		if(result != null){
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 4;
			Bitmap bit = BitmapFactory.decodeByteArray(result, 0, result.length,options);
			iv.setImageBitmap(bit);
			/*if(iv.getTag().toString().equals(pic_url)){
				iv.setImageBitmap(bit);
				mapcontent.put(iv.getTag().toString(), bit);
			}*/
		}
	}
}
