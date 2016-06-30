package com.example.UtilTools;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.body.BbsRt;

public class JsonBbsRt {

	public static List<BbsRt> json2bean(String jsonstr){
		List<BbsRt> list = new ArrayList<>();
		BbsRt bbs;
		try {
			JSONObject object = new JSONObject(jsonstr);
			JSONArray array = object.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				 JSONObject object2 = array.getJSONObject(i);
				 String title = object2.getString("title");
				 String author = object2.getString("author");
				 int views = Integer.parseInt(object2.getString("views"));
				 int reply = Integer.parseInt(object2.getString("reply"));
				 String doc_url_v2 = object2.getString("doc_url_v2");
				 bbs = new BbsRt(title, author, views, reply, doc_url_v2);
				 list.add(bbs);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}
