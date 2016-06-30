package com.example.Task;

import java.util.List;

import com.example.Adapter.MyAdapterFg;
import com.example.Adapter.MyAdapterQc;
import com.example.UtilTools.HttpdownUtil;
import com.example.UtilTools.JSONFG;
import com.example.UtilTools.JsonQc;
import com.example.body.QiCai;
import com.example.body.Scenery;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class MyTaskQC extends AsyncTask<String, Void, byte[]> {

	private Context context = null;
	private List<QiCai> list = null;
	private MyAdapterQc mycq = null;
	
	
	public MyTaskQC(Context context, List<QiCai> list, MyAdapterQc mycq) {
		super();
		this.context = context;
		this.list = list;
		this.mycq = mycq;
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
			List<QiCai> json2bean = JsonQc.json2Jx(jsonString);
			list.addAll(json2bean);
			mycq.notifyDataSetChanged();
		}
	}
}