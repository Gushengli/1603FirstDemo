package com.example.mywork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

public class WebLtActivity extends Activity {

	private WebView wv = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		
		initView();
		jump();
	}

	private void jump() {
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		System.out.println(url);
		wv.loadUrl(url);
	}

	private void initView() {
		wv = (WebView)findViewById(R.id.wv_gu);
	}
}
