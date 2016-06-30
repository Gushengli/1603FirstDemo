package com.example.UtilTools;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.body.FgWeb;

public class JsonFgWeb {
	public static List<FgWeb> Json2bean(String strjson){
		List<FgWeb> list = new ArrayList<>();
           FgWeb fg;
		try {
			JSONArray array = new JSONArray(strjson);
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				String title = obj.getString("doc_title");
				JSONObject obj1 = obj.getJSONObject("pic");
				String web_url = obj1.getString("gq");
				fg = new FgWeb(title,web_url);
				list.add(fg);
				}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
