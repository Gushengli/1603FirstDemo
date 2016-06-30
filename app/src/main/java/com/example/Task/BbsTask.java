package com.example.Task;

import java.util.List;

import com.example.Adapter.BbsAdapter;
import com.example.UtilTools.HttpdownUtil;
import com.example.UtilTools.JsonBbsRt;
import com.example.body.BbsRt;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class BbsTask extends AsyncTask<String, Void, byte[]>{

	private Context context;
	private List<BbsRt> listbbs;
	private BbsAdapter adapter;
	
	public BbsTask(Context context, List<BbsRt> listbbs, BbsAdapter adapter) {
		super();
		this.context = context;
		this.listbbs = listbbs;
		this.adapter = adapter;
	}

	@Override
	protected byte[] doInBackground(String... params) {
		System.out.println(".....");
		return HttpdownUtil.getdown(params[0]);
	}

	@Override
	protected void onPostExecute(byte[] result) {
		if(result != null){
			String strjson =  new String(result);
			List<BbsRt> json2bean = JsonBbsRt.json2bean(strjson);	
			listbbs.addAll(json2bean);
			
			adapter.notifyDataSetChanged();
			
		}
	}
}
