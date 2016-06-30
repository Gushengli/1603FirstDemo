package com.example.fragment;

import com.example.mywork.R;
import com.example.mywork.Set7Activity;
import com.example.mywork.SetregisActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
public class FragmentSet extends Fragment implements OnClickListener{
 
	private View fragmentBBS;
	private LinearLayout ll1,ll2,ll3,ll4,ll5,ll6,ll7,ll8;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		fragmentBBS = inflater.inflate(R.layout.fragment_set_main, null);
		
		initview();
		setlistener();
		return fragmentBBS;
	}
	
	private void setlistener() {
		ll1.setOnClickListener(this); 
		ll2.setOnClickListener(this);
		ll3.setOnClickListener(this);
		ll4.setOnClickListener(this);
		ll5.setOnClickListener(this);
		ll6.setOnClickListener(this);
		ll7.setOnClickListener(this);
		ll8.setOnClickListener(this);
		
	}
	
	private void initview() {
		 ll1 = (LinearLayout) fragmentBBS.findViewById(R.id.setting_reg_login);
		 ll2 = (LinearLayout) fragmentBBS.findViewById(R.id.setting_shareManager);
		 ll3 = (LinearLayout) fragmentBBS.findViewById(R.id.setting_bbs);
		 ll4 = (LinearLayout) fragmentBBS.findViewById(R.id.setting_collection);
		 ll5 = (LinearLayout) fragmentBBS.findViewById(R.id.setting_clearCache);
		 ll6 = (LinearLayout) fragmentBBS.findViewById(R.id.setting_autoImg);
		 ll7 = (LinearLayout) fragmentBBS.findViewById(R.id.setting_feedback);
		 ll8 = (LinearLayout) fragmentBBS.findViewById(R.id.setting_about);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.setting_reg_login:
			Intent intent = new Intent(getActivity(),SetregisActivity.class);
			getActivity().startActivity(intent);
			break;
		case R.id.setting_shareManager:
			Toast.makeText(getActivity(), "分享账号管理", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting_bbs:
			Toast.makeText(getActivity(), "我的论坛", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting_collection:
			Toast.makeText(getActivity(), "个人收藏", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting_clearCache:
			Toast.makeText(getActivity(), "清除应用缓存", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting_autoImg:
			Toast.makeText(getActivity(), "自动加载图片", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting_feedback:
			Toast.makeText(getActivity(), "建议反馈", Toast.LENGTH_SHORT).show();
			break;
		case R.id.setting_about:
			Intent intent1 = new Intent(getActivity(),Set7Activity.class);
			getActivity().startActivity(intent1);
			break;
		}
	}
}
