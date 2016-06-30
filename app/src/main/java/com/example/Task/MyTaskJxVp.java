package com.example.Task;

import java.util.List;

import com.example.Url.TransData;
import com.example.UtilTools.HttpdownUtil;
import com.example.UtilTools.JsonJxVp;
import com.example.body.JingXuanVp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.widget.ImageView;

public class MyTaskJxVp extends AsyncTask<String, Void, byte[]>{

	private Context context;
	private List<JingXuanVp> jingxuanvp;
	private TransData trans;
	
	
	public MyTaskJxVp(Context context,
			List<JingXuanVp> jingxuanvp,TransData trans) {
		super();
		this.context = context;
		this.jingxuanvp = jingxuanvp;
		this.trans = trans;
	}

	@Override
	protected byte[] doInBackground(String... params) {
		
		return HttpdownUtil.getdown(params[0]);
	}

	@Override
	protected void onPostExecute(byte[] result) {
		super.onPostExecute(result);
		String strjson = new String(result);
		List<JingXuanVp> json2JxVp = JsonJxVp.json2JxVp(strjson);
		jingxuanvp.addAll(json2JxVp);
		trans.getdata(json2JxVp);
	}
}
