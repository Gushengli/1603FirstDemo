package com.example.mywork;


import com.example.fragment.FragmentBBS;
import com.example.fragment.FragmentBar1;
import com.example.fragment.FragmentBar2;
import com.example.fragment.FragmentSet;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {

	private RadioGroup rg = null;
	private RadioButton rb1,rb2,rb3,rb4;
	private FragmentBar1 firstFragment = null;
	private FragmentBar2 secondFragment = null;
	private FragmentBBS bbsFragment = null;
	private FragmentSet setFragment = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initFragmentFirst();
		initView();
		setlistener();

	}
	
	private void setlistener() {
		rg.setOnCheckedChangeListener(this);

	}
	
	private void initView() {
		rg = (RadioGroup) findViewById(R.id.mainRadioTabs);

		rb1 = (RadioButton) findViewById(R.id.mainRadio_news);
		rb2 = (RadioButton) findViewById(R.id.mainRadio_pic);
		rb3 = (RadioButton) findViewById(R.id.mainRadio_bbs);
		rb4 = (RadioButton) findViewById(R.id.mainRadio_set);

	}
	
	private void initFragmentFirst() {
		firstFragment = new FragmentBar1();
		secondFragment = new FragmentBar2();
		bbsFragment = new FragmentBBS();
		setFragment = new FragmentSet();
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction()
		.replace(R.id.main_contents_fl, firstFragment)
		.commit();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		if(checkedId == R.id.mainRadio_news){
			ft.replace(R.id.main_contents_fl, firstFragment).commit();
		}else if(checkedId == R.id.mainRadio_pic){
			ft.replace(R.id.main_contents_fl, secondFragment).commit();
		}else if(checkedId == R.id.mainRadio_bbs){
			ft.replace(R.id.main_contents_fl, bbsFragment).commit();
		}else if(checkedId == R.id.mainRadio_set){
			ft.replace(R.id.main_contents_fl, setFragment).commit();
		}
	}
}
