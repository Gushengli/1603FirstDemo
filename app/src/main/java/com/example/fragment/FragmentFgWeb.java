package com.example.fragment;

import java.util.List;

import com.example.Task.ImageViewFgTask;
import com.example.body.FgWeb;
import com.example.mywork.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class FragmentFgWeb extends Fragment {
    private View v = null;
    private ImageView iv = null;
    private TextView tv;

    private FgWeb fg;

	public FragmentFgWeb(FgWeb fg) {
		super();
		this.fg = fg;
	}

	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.pic_detail, null);
		initview();
		
		return v;
	}
	
	private void initview() {
		tv = (TextView) v.findViewById(R.id.pic_detail_title);
		iv = (ImageView) v.findViewById(R.id.pi);
		
		tv.setText(fg.doc_title);
		new ImageViewFgTask(getActivity(), fg.web_url,iv).execute(fg.web_url);
	}
}
