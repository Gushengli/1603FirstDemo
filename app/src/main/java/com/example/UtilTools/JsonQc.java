package com.example.UtilTools;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.body.JingXuan;
import com.example.body.QiCai;

public class JsonQc {
	
	public static List<QiCai> json2Jx(String jsonstr){
		List<QiCai> list = new ArrayList<>();
		QiCai qc = null;
		try {
			JSONObject object = new JSONObject(jsonstr);
			JSONArray array = object.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				JSONObject object2 = array.getJSONObject(i);
				String title = object2.getString("title");
				String url = object2.getString("pic_url");
				String web = object2.getString("web_url");
				qc = new QiCai(title,url,web);
				list.add(qc);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;

	}
}
