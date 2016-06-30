package com.example.Task;

import java.util.List;

import com.example.Adapter.MyAdapterFg;
import com.example.Adapter.MyAdapterJx;
import com.example.UtilTools.HttpdownUtil;
import com.example.UtilTools.JSONFG;
import com.example.UtilTools.JsonJx;
import com.example.body.JingXuan;
import com.example.body.Scenery;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class MyTaskJX extends AsyncTask<String, Void, byte[]> {

	private Context context = null;
	private List<JingXuan> list = null;
	private MyAdapterJx myfg = null;
	
	public MyTaskJX(Context context, List<JingXuan> list, MyAdapterJx myfg) {
		super();
		this.context = context;
		this.list = list;
		this.myfg = myfg;
	}
	
	@Override
	protected byte[] doInBackground(String... params) {
		
		return  HttpdownUtil.getdown(params[0]);
	}
	@Override
	protected void onPostExecute(byte[] result) {
		super.onPostExecute(result);
		if(result != null){
			String jsonString = new String(result);
			List<JingXuan> json2bean = JsonJx.json2Jx(jsonString);
			list.addAll(json2bean);
			myfg.notifyDataSetChanged();
		
		}
	}
}