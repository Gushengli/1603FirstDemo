package com.example.mywork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class JxWebActivity extends Activity {

	private WebView wv = null;
	private String url = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		
		initview();
		jump();
		setdata();
	}

	private void setdata() {
		wv.loadUrl(url);
	}

	private void jump() {
		Intent intent = getIntent();
		url = intent.getStringExtra("url");
	}

	private void initview() {
		wv = (WebView) findViewById(R.id.wv_gu);
	}
}
