package com.example.mywork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import com.example.Adapter.BbsAdapter;
import com.example.Task.BbsTask;
import com.example.body.BbsRt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class BbsActivity extends Activity implements OnItemClickListener{

	private ListView lvshow = null;
	private List<BbsRt> listbbs = null;
	private TextView tvshow = null;
	private BbsAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forum_activity);
		initview();
		setdata();
		transelation();
		setlistener();
	}

	private void setlistener() {
         lvshow.setOnItemClickListener(this);		
	}

	private void transelation() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String title = bundle.getString("title");
		String url = bundle.getString("url");
		
		tvshow.setText(title);
		new BbsTask(this, listbbs, adapter).executeOnExecutor(Executors.newFixedThreadPool(1), url);
	}

	private void setdata() {
		listbbs = new ArrayList<>();
		adapter = new BbsAdapter(this,listbbs);
		lvshow.setAdapter(adapter);
	}

	private void initview() {
		lvshow = (ListView) findViewById(R.id.forum_list);
		tvshow = (TextView) findViewById(R.id.forum_title);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent =  new Intent(BbsActivity.this,WebLtActivity.class);
		BbsRt bbsRt = listbbs.get(position);
		String url = bbsRt.doc_url_v2;
		intent.putExtra("url", url);
		System.out.println(bbsRt);
		startActivity(intent);
	}
}
