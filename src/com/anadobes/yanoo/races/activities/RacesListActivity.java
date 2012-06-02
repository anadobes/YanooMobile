package com.anadobes.yanoo.races.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.anadobes.yanoo.R;
import com.anadobes.yanoo.races.data.RacesData;
import com.anadobes.yanoo.races.manager.RacesManager;
import com.anadobes.yanoo.races.models.Race;

public class RacesListActivity extends ListActivity {

	private ListView list;
	private ListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.races_raceslist);

		list = getListView();
		adapter = getListAdapter();

		Toast.makeText(this, "vouvou", Toast.LENGTH_SHORT).show();

		// Récup data si clic sur bouton : 
		findViewById(R.id_raceslist.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DownloadRacesTask().execute();
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

		HashMap<String, String> map; // HashMap temporaire qui contiendra les informations pour un item

		for (Race race : RacesManager.instance().getRacesList()) {
			map = new HashMap<String, String>();
			map.put("name", race.getName());
			map.put("date", (new SimpleDateFormat("dd/MM/yyyy")).format(race.getDate()));
			map.put("image", "/mnt/sdcard/tmp/" + race.getIconFileName());
			
			listItem.add(map);
		}

		String[] from = new String[] { "name", "date", "image" };
		int[] to = new int[] { R.id_raceslist_row_adapter.name, R.id_raceslist_row_adapter.date, R.id_raceslist_row_adapter.image };
		adapter = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.races_raceslist_row_adapter, from, to);

		list.setAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Map<String, String> map = (Map<String, String>) l.getAdapter().getItem(position);
		String raceName = map.get("name");

		Intent i = new Intent(this, RaceDetailsActivity.class);
		i.putExtra("raceName", raceName);
		startActivity(i);
	}

	private class DownloadRacesTask extends AsyncTask<Void, Void, List<Race>> {

		@Override
		protected List<Race> doInBackground(Void... params) {
			return RacesData.getRaces();
		}

		@Override
		protected void onPostExecute(List<Race> result) {
			Toast.makeText(getApplicationContext(), "Liste des courses mise à jour", Toast.LENGTH_SHORT).show();
			RacesManager.instance().setRacesList(result);
			bindListViewData();
		}
	}
}