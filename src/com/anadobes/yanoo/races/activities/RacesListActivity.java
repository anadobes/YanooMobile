package com.anadobes.yanoo.races.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.anadobes.yanoo.R;
import com.anadobes.yanoo.home.activities.HomeActivity;
import com.anadobes.yanoo.races.data.RacesData;
import com.anadobes.yanoo.races.manager.RacesManager;
import com.anadobes.yanoo.races.models.Race;

public class RacesListActivity extends SherlockListActivity {

	private ListView list;
	private ListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.races_raceslist);

		list = getListView();
		adapter = getListAdapter();

		// Mise à jour de démarage avec données de test :
		//bindListViewData();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
        getSupportMenuInflater().inflate(R.menu.races_menu_items, menu);
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

    	case R.id_raceslist_menu.refresh:
    		new DownloadRacesTask(item).execute();
            return true;
            
        default:
            return super.onOptionsItemSelected(item);
		}
    }

	/**
	 * Bind the races data model to the ListView.
	 */
	private void bindListViewData() {

		// CrÈation de la ArrayList qui nous permettra de remplir la listView
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

		// Objet MenuItem qui contiendra une ProgressBar durant le téléchargement
		private MenuItem refreshMenuItem = null;
		
		public DownloadRacesTask(MenuItem item) {
			refreshMenuItem = item;
		}

		@Override
		protected void onPreExecute() {
			// Changement du texte
			TextView emptyTV = (TextView) findViewById(android.R.id.empty);
			emptyTV.setText(getResources().getString(R.string.races_getdata));
			// Lancement animation ProgressBar indéterminée :
			if(refreshMenuItem != null)
				refreshMenuItem.setActionView(R.layout.refresh_progress_bar);
		}
		
		@Override
		protected List<Race> doInBackground(Void... params) {
			// Récupération de la liste de courses :
			return RacesData.getRaces();
		}

		@Override
		protected void onPostExecute(List<Race> result) {
			Toast.makeText(getApplicationContext(), getResources().getString(R.string.races_datareceived), Toast.LENGTH_SHORT).show();
			
			// Utilisation de la nouvelle liste de courses :
			RacesManager.instance().setRacesList(result);
			
			// Mise à jour de la vue :
			bindListViewData();
			
			// Arrêt animation ProgressBar indéterminée :
			if(refreshMenuItem != null)
				refreshMenuItem.setActionView(null);
		}
	}
}