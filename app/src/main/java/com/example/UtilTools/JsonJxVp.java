package com.example.UtilTools;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.body.JingXuanVp;

public class JsonJxVp {

	public static List<JingXuanVp>  json2JxVp(String jsonstr){
		List<JingXuanVp> list = new ArrayList<>();
		JingXuanVp jxv = null;
		try {
			JSONArray array = new JSONArray(jsonstr);
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				String title = object.getString("title");
				String url = object.getString("pic_src");
				String web_url = object.getString("web_url");
				jxv = new JingXuanVp(url,title,web_url);
				list.add(jxv);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
		
	}
}
