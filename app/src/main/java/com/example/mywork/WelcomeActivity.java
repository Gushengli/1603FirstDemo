package com.example.mywork;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);

		new Thread(){
			public void run() {
				SystemClock.sleep(1500);
				Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
				startActivity(intent);	
				finish();
				//overridePendingTransition(R.anim.slide_left_out, R.anim.slide_left_in);
			};
		}.start();
		
		
	}
}
