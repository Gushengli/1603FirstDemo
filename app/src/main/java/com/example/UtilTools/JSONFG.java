package com.example.UtilTools;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.body.Scenery;

public class JSONFG {
	public static List<Scenery> Json2bean(String strjson){
		List<Scenery> list = new ArrayList<>();
		JSONObject obj;
		try {
			obj = new JSONObject(strjson);
			JSONArray Array = obj.getJSONArray("list");
			Scenery scen = null;
			for (int i = 0; i < Array.length(); i++) {
				JSONObject obj1 = Array.getJSONObject(i);
				String title = obj1.getString("title");
				String pic_url = obj1.getString("pic_url");
				String detail_url = obj1.getString("detail_url");
				scen = new Scenery(title,pic_url,detail_url);
				list.add(scen);
				}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
