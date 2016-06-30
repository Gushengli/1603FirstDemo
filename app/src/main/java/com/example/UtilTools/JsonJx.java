package com.example.UtilTools;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.body.JingXuan;

public class JsonJx {

	public static List<JingXuan> json2Jx(String jsonstr){
		List<JingXuan> list = new ArrayList<>();
		JingXuan jx;
		try {
			JSONObject object = new JSONObject(jsonstr);
			JSONArray array = object.getJSONArray("160120");
			for (int i = 0; i < array.length(); i++) {
				JSONObject object2 = array.getJSONObject(i);
				String title = object2.optString("title");
				String pic_url = object2.optString("pic_url");
				String date = object2.optString("date");
				String web_url = object2.getString("web_url");
				jx = new JingXuan(title,pic_url,date,web_url);
				list.add(jx);
				}
			JSONArray array1 = object.getJSONArray("280280");
			for (int j = 0; j < array1.length(); j++) {
				JSONObject obj1 = array.getJSONObject(j);
				String title = obj1.optString("title");
				String pic_url = obj1.optString("pic_url");
				String web_url = obj1.getString("web_url");
				jx = new JingXuan(title,pic_url,web_url);
				list.add(jx);
			}
		
		}catch (JSONException e) {
				e.printStackTrace();
			}
			return list;
		}
}