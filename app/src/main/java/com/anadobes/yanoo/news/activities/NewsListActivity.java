package com.anadobes.yanoo.news.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.anadobes.yanoo.R;
import com.anadobes.yanoo.home.activities.HomeActivity;
import com.anadobes.yanoo.news.manager.NewsManager;
import com.anadobes.yanoo.news.models.News;

public class NewsListActivity extends SherlockListActivity {

	private ListView list;
	private ListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_newslist);

		// Affiche la flèche à coté du bouton home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
		list = getListView();
		adapter = getListAdapter();

		// Mise à jour de démarage avec données de test :
		bindListViewData();
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

	/**
	 * Bind the news data model to the ListView.
	 */
	private void bindListViewData() {

		// Création de la ArrayList qui nous permettra de remplir la listView
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map; // HashMap temporaire qui contiendra les informations pour un item

		for (News news : NewsManager.instance().getNewsList()) {
			map = new HashMap<String, String>();
			map.put("title", news.getTitle());
			map.put("date", (new SimpleDateFormat("dd - MM - yyyy")).format(news.getDate()));
			map.put("writer", news.getWriter());
			
			listItem.add(map);
		}

		String[] from = new String[] { "title", "date", "writer" };
		int[] to = new int[] { R.id_newslist_row_adapter.title, R.id_newslist_row_adapter.date, R.id_newslist_row_adapter.writer };
		adapter = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.news_newslist_row_adapter, from, to);

		list.setAdapter(adapter);
	}
}