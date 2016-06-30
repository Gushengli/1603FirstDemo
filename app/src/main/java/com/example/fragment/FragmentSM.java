package com.example.fragment;

import com.example.mywork.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentSM extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View fragmentQC = inflater.inflate(R.layout.fragment_listview_gu, null);
		return fragmentQC;
	}
}
