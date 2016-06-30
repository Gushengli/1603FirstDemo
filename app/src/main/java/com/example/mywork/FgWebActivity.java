package com.example.mywork;

import java.util.ArrayList;
import java.util.List;

import com.example.Task.FgWebTask;
import com.example.Url.OnGetDataListener;
import com.example.body.FgWeb;
import com.example.fragment.FragmentFgWeb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class FgWebActivity extends FragmentActivity implements OnGetDataListener{

	private ViewPager vpshow = null;
	private List<Fragment> list = null;
	private String url;
	private List<FgWeb> fw = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		
		initview();
		jump();
		setdata();
		
	}
	
	private void jump() {
		
		Intent intent = getIntent();
		url = intent.getStringExtra("url");
	}

	private void setdata() {
		list = new ArrayList<Fragment>();
		fw = new ArrayList<>();
		new FgWebTask(this, this, fw).execute(url);
	}

	private void initview() {
	  vpshow = (ViewPager) findViewById(R.id.vp_gu_fgweb);
	}

	@Override
	public void getDataListener(List<FgWeb> fw) {
		this.fw = fw;
		for (int i = 0; i < fw.size(); i++) {
			list.add(new FragmentFgWeb(fw.get(i)));
		}
		vpshow.setAdapter(new HardAdapter(getSupportFragmentManager()));
	}
	
	class HardAdapter extends FragmentStatePagerAdapter{

		public HardAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			return list.size();
		}
	}
}
