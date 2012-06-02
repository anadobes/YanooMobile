package com.anadobes.yanoo.races.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.anadobes.yanoo.R;
import com.anadobes.yanoo.races.data.RaceData;
import com.anadobes.yanoo.races.manager.RacesManager;
import com.anadobes.yanoo.races.models.Race;

public class RacesListActivity extends ListActivity {

	private ListView list;
	private ListAdapter adapter;

	private RacesManager racesManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.races_raceslist);

		list = getListView();
		adapter = getListAdapter();
		
		racesManager = new RacesManager();

		Toast.makeText(this, "vouvou", Toast.LENGTH_SHORT).show();

		// Test recup data : 
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RaceData.getRacesThread();
			};
		});

		bindListViewData();
	}

	/**
	 * Bind the races data model to the ListView.
	 */
	private void bindListViewData() {

		// Création de la ArrayList qui nous permettra de remplir la listView
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map;	// HashMap temporaire qui contiendra les informations pour un item
		
		for (Race race : racesManager.getRacesList()) {
			map = new HashMap<String, String>();
			map.put("name", race.getName());
			map.put("date", (new SimpleDateFormat("dd/MM/yyyy")).format(race.getDate()));
			listItem.add(map);
		}
		
		adapter = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.races_raceslist_row_adapter, 
				new String[] { "name", "date" }, 
				new int[] { R.id.name, R.id.date });

		list.setAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		// TODO call other activity

		Toast.makeText(getApplicationContext(), "cool", Toast.LENGTH_SHORT).show();
	}
}