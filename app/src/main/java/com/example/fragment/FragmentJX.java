package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.Adapter.MyAdapterJx;
import com.example.Task.ImageViewFgTask;
import com.example.Task.MyTaskJX;
import com.example.Task.MyTaskJxVp;
import com.example.Url.TransData;
import com.example.Url.URLinterface;
import com.example.body.JingXuan;
import com.example.body.JingXuanVp;
import com.example.mywork.R;
import com.example.mywork.VptopActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
public class FragmentJX extends Fragment implements OnScrollListener,TransData,OnPageChangeListener{

	private View fragmentJX  = null;
	private ListView lv = null;
	private TextView tv;
	private List<JingXuan> list = null;
	private int position = 1;
	private int preposition = 0;
	private boolean flag = true;
	private String path = null;
	private MyAdapterJx myfg = null;
	private List<JingXuanVp> jingxuanvp;
	private ViewPager vpshow;
	private ViewPagerAdapter vpadapter;
	private LinearLayout ll;
	private ImageView image;
	private List<ImageView> listimag;
	private int num = 0;
	public FragmentJX(String path) {
		super();
		this.path = path;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fragmentJX = inflater.inflate(R.layout.fragment_listview_gu, null);

		initView();
		setViewPager();
		setdata();
		setlistener();

		return fragmentJX;
	}

	private void initView() {
		lv = (ListView) fragmentJX.findViewById(R.id.lv_gu_zong);
		View refreshview = View.inflate(getActivity(), R.layout.refresh_bottom, null);
		lv.addFooterView(refreshview);
	}

	private void setlistener() {
		lv.setOnScrollListener(this);
		vpshow.setOnPageChangeListener(this);
	}

	private void setdata() {
		list = new ArrayList<>();
		myfg = new MyAdapterJx(getActivity(), list);
		lv.setAdapter(myfg);
		new MyTaskJX(getActivity(), list, myfg).execute(path+position);

		jingxuanvp = new ArrayList<>();
		vpadapter = new ViewPagerAdapter();
		listimag = new ArrayList<ImageView>();
		new MyTaskJxVp(getActivity(), jingxuanvp,this).execute(URLinterface.URLJX_VP);
		vpshow.setAdapter(vpadapter);

	}

	private void setViewPager() {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_addheader, null);
		vpshow = (ViewPager) v.findViewById(R.id.vpshow_headgu);
		tv = (TextView) v.findViewById(R.id.tv_vpshow_gu);
		ll = (LinearLayout) v.findViewById(R.id.ll_vpshow_gu);
		lv.addHeaderView(v);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(flag == true && scrollState == SCROLL_STATE_IDLE){
			position += 1;
			new MyTaskJX(getActivity(), list, myfg).execute(path+position);
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

	@Override
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
