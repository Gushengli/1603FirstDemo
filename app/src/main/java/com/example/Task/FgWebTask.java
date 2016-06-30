package com.example.Task;

import java.util.List;

import com.example.Url.OnGetDataListener;
import com.example.UtilTools.HttpdownUtil;
import com.example.UtilTools.JsonFgWeb;
import com.example.body.FgWeb;
import com.example.mywork.FgWebActivity;

import android.content.Context;
import android.os.AsyncTask;

public class FgWebTask extends AsyncTask<String, Void, byte[]>{
	
	private OnGetDataListener odl;
	
	

	private Context context;
	private List<FgWeb> FgWeb;
	
	public FgWebTask(Context context,OnGetDataListener odl,
			List<FgWeb> fgWeb) {
		super();
		this.context = context;
		this.odl = odl;
		FgWeb = fgWeb;
	}

	@Override
	protected byte[] doInBackground(String... params) {
		return HttpdownUtil.getdown(params[0]);
	}

	@Override
	protected void onPostExecute(byte[] result) {
		super.onPostExecute(result);
		if(result != null){
			String jsonstr = new String(result);
			List<FgWeb> json2bean = JsonFgWeb.Json2bean(jsonstr);
			FgWeb.addAll(json2bean);
			odl.getDataListener(FgWeb);
		}
	}
}
