package com.example.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.example.Adapter.MyAdapterFg;
import com.example.Task.MyTaskFG;
import com.example.Url.URLinterface;
import com.example.body.Scenery;
import com.example.mywork.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class FragmentFG extends Fragment implements OnScrollListener{
     private String url = null;
     private View fragmentQC = null;
     private ListView listview = null;
     private List<Scenery> list = null;
     private MyAdapterFg  myfg = null;
     private int position = 1;
     private boolean flag = true;
     
     
	public FragmentFG(String url) {
		super();
		this.url = url;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		fragmentQC = inflater.inflate(R.layout.fragment_listview_gu, null);
		
		initview();
		setdata();
		new MyTaskFG(getActivity(), list, myfg).execute(url+position);
		setlistener();
		return fragmentQC;
	}
	
	private void setlistener() {
		listview.setOnScrollListener(this);
	}

	private void setdata() {
     list = new ArrayList<>();
		myfg = new MyAdapterFg(getActivity(), list);
		listview.setAdapter(myfg);
	}

	private void initview() {
		listview = (ListView) fragmentQC.findViewById(R.id.lv_gu_zong);
		
		View footerview = View.inflate(getActivity(), R.layout.refresh_bottom, null);
		listview.addFooterView(footerview);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(flag == true&&scrollState == SCROLL_STATE_IDLE){
			position += 1;
			new MyTaskFG(getActivity(), list, myfg).execute(url+position);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if(firstVisibleItem + visibleItemCount == totalItemCount){
			flag = true;
		}
	}
}
