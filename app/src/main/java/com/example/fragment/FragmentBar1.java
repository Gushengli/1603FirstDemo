package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.Url.URLinterface;
import com.example.mywork.R;



import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentBar1 extends Fragment implements OnPageChangeListener,android.view.View.OnClickListener{

	private ViewPager vpshow = null;
	private ImageView imag1,imag2,imag3,imag4;
	private TextView text1,text2,text3,text4;
	private LinearLayout ll = null;
	private List<ImageView> listimag = null;
	private List<Fragment> listfragment = null;
	private int preposition = 0;
	private View v;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		v = inflater.inflate(R.layout.fragment_information, null);
		initView();
		setdata();
		
		setlistener();
		return v;

	}

	private void setlistener() {
		text1.setOnClickListener(this);
		text2.setOnClickListener(this);
		text3.setOnClickListener(this);
		text4.setOnClickListener(this);
		
		vpshow.setOnPageChangeListener(this);
	}

	private void setdata() {
		listimag = new ArrayList<>();
		listimag.add(imag1);
		listimag.add(imag2);
		listimag.add(imag3);
		listimag.add(imag4);

		listfragment = new ArrayList<>();
		listfragment.add(new FragmentJX(URLinterface.URLJX));
		listfragment.add(new FragmentQC(URLinterface.URLQC,URLinterface.URLJX_VP));
		listfragment.add(new FragmentQC(URLinterface.URLYX,URLinterface.URLYX_VP));
		listfragment.add(new FragmentQC(URLinterface.URLXY,URLinterface.URLXY_VP));
		
		MyAdapter my = new MyAdapter(getFragmentManager());
		vpshow.setAdapter(my);
	}

	private void initView() {
		vpshow = (ViewPager) v.findViewById(R.id.vpShow_my);
        ll = (LinearLayout) v.findViewById(R.id.liearlayoutHead);
		imag1 = (ImageView) v.findViewById(R.id.iv_jingxuan);
		imag2 = (ImageView) v.findViewById(R.id.iv_qicai);
		imag3 = (ImageView) v.findViewById(R.id.iv_yingxiang);
		imag4 = (ImageView) v.findViewById(R.id.iv_xuyuan);

		text1 = (TextView) v.findViewById(R.id.textView1);
		text2 = (TextView) v.findViewById(R.id.textView2);
		text3 = (TextView) v.findViewById(R.id.textView3);
		text4 = (TextView) v.findViewById(R.id.textView4);
	}

	@Override
	public void onClick(View v) {

		switch(v.getId()){
		case R.id.textView1:
			vpshow.setCurrentItem(0);
			break;
		case R.id.textView2:
			vpshow.setCurrentItem(1);
			break;
		case R.id.textView3:
			vpshow.setCurrentItem(2);
			break;
		case R.id.textView4:
			vpshow.setCurrentItem(3);
			break;
		}
	}

	class MyAdapter extends FragmentStatePagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return listfragment.get(arg0);
		}

		@Override
		public int getCount() {
			return listfragment.size();
		}
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {

		listimag.get(arg0).setBackgroundColor(Color.RED);
		listimag.get(preposition).setBackgroundColor(Color.GRAY);
		preposition = arg0;
	}
}
