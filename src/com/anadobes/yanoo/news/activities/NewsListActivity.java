package com.anadobes.yanoo.news.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.anadobes.yanoo.R;
import com.anadobes.yanoo.home.activities.HomeActivity;

public class NewsListActivity extends SherlockListActivity {

	private ListView list;
	private ListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_newslist);

		list = getListView();
		adapter = getListAdapter();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
        getSupportMenuInflater().inflate(R.menu.news_menu_items, menu);
        getSupportActionBar().setHomeButtonEnabled(true);
		return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
    	
    	case android.R.id.home:
    		Intent i = new Intent(this, HomeActivity.class);
    		startActivity(i);
            return true;

    	case R.id_newslist_menu.refresh:
    		// TODO download news
            return true;
            
        default:
            return super.onOptionsItemSelected(item);
		}
    }
}