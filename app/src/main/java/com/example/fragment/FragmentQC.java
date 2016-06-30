package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.Adapter.MyAdapterQc;
import com.example.Task.ImageViewFgTask;
import com.example.Task.MyTaskJxVp;
import com.example.Task.MyTaskQC;
import com.example.Url.TransData;
import com.example.Url.URLinterface;
import com.example.body.JingXuanVp;
import com.example.body.QiCai;
import com.example.fragment.FragmentJX.ViewPagerAdapter;
import com.example.mywork.R;
import com.example.mywork.VptopActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;

public class FragmentQC extends Fragment implements OnScrollListener,TransData,OnPageChangeListener{

	private String path = null;
	private String pathvp = null;
	private ListView lvQC = null;
	private View fragmentQC = null;
	private List<QiCai> list = null;
	private MyAdapterQc myqc = null;
	private int position = 1;
	private boolean flag = true;
	
	private List<JingXuanVp> jingxuanvp = null;
	private ViewPagerAdapter vpadapter;
	private int preposition = 0;
	private ViewPager vpshow;
	private LinearLayout ll;
	private ImageView image;
	private List<ImageView> listimag;
	private int num = 0;
	private TextView tv;
	
	public FragmentQC(String path,String pathvp) {
		super();
		this.path = path;
		this.pathvp = pathvp;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		fragmentQC = inflater.inflate(R.layout.fragment_listview_gu, null);

		initview();
		setViewPager();
		setdata();
		setView();
		setlistener();

		return fragmentQC;
	}

	private void setView() {
		
	}

	private void setlistener() {
		lvQC.setOnScrollListener(this);	
		//
		vpshow.setOnPageChangeListener(this);
		
		
	}

	private void setdata() {
		list = new ArrayList<>();
		myqc = new MyAdapterQc(getActivity(), list);
		lvQC.setAdapter(myqc);
		new MyTaskQC(getActivity(), list, myqc).execute(path+position);
		
		jingxuanvp = new ArrayList<>();
		vpadapter = new ViewPagerAdapter();
		listimag = new ArrayList<ImageView>();
		new MyTaskJxVp(getActivity(), jingxuanvp,this).execute(URLinterface.URLQC_VP);
		vpshow.setAdapter(vpadapter);
	}

	private void setViewPager() {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_addheader, null);
		vpshow = (ViewPager) v.findViewById(R.id.vpshow_headgu);
		tv = (TextView) v.findViewById(R.id.tv_vpshow_gu);
		ll = (LinearLayout) v.findViewById(R.id.ll_vpshow_gu);
		lvQC.addHeaderView(v);
	}
	
	
	
	private void initview() {
		lvQC = (ListView) fragmentQC.findViewById(R.id.lv_gu_zong);
		View refreshview = View.inflate(getActivity(), R.layout.refresh_bottom, null);
		lvQC.addFooterView(refreshview);
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(flag == true && scrollState == 0){
			position += 1;
			new MyTaskQC(getActivity(), list, myqc).execute(path+position);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if(firstVisibleItem + visibleItemCount == totalItemCount){
			flag = true;
		}
	}
	
	class ViewPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return listimag.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View v = listimag.get(position);
			ViewGroup parent = (ViewGroup) v.getParent();
			if(parent != null) {
				parent.removeView(v);
			}
			container.addView(v);
			return v;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(listimag.get(position));
		}

	}
	
	public void getdata(final List<JingXuanVp> jingxuanvp) {

		this.jingxuanvp = jingxuanvp;	
		image = new ImageView(getActivity());
		image.setMaxHeight(LayoutParams.MATCH_PARENT);
		image.setMaxWidth(LayoutParams.MATCH_PARENT);
		image.setScaleType(ScaleType.CENTER_CROP);
		for (int i = 0; i < jingxuanvp.size(); i++) {
			new ImageViewFgTask(getActivity(), jingxuanvp.get(i).pic_src, image)
			.execute(jingxuanvp.get(i).pic_src);
			listimag.add(image);
			num = i;
			image.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(),VptopActivity.class);
					intent.putExtra("url", jingxuanvp.get(num).web_url);
					startActivity(intent);
				}
			});

			View view = new View(getActivity());
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
			params.leftMargin = 5;
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.dot_normal);
			view.setTag(i);
			ll.addView(view);

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int tag = (Integer)v.getTag();
					vpshow.setCurrentItem(tag);
				}
			});
		}
		vpadapter.notifyDataSetChanged();
		tv.setText(jingxuanvp.get(0).title);
		ll.getChildAt(0).setBackgroundResource(R.drawable.dot_enable);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		tv.setText(jingxuanvp.get(arg0).title);
		ll.getChildAt(arg0).setBackgroundResource(R.drawable.dot_enable);
		ll.getChildAt(preposition).setBackgroundResource(R.drawable.dot_normal);
		preposition = arg0 ;
	}

}
