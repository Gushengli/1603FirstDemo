package com.example.Task;

import java.util.List;

import com.example.Adapter.MyAdapterFg;
import com.example.UtilTools.HttpdownUtil;
import com.example.UtilTools.JSONFG;
import com.example.body.Scenery;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class MyTaskFG extends AsyncTask<String, Void, byte[]> {

	private Context context = null;
	private List<Scenery> list = null;
	private MyAdapterFg myfg = null;
	
	
	public MyTaskFG(Context context, List<Scenery> list, MyAdapterFg myfg) {
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
			List<Scenery> json2bean = JSONFG.Json2bean(jsonString);
			list.addAll(json2bean);
			myfg.notifyDataSetChanged();
		}
	}
}