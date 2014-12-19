package com.anadobes.yanoo.races.activities;

import java.text.SimpleDateFormat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.anadobes.yanoo.R;
import com.anadobes.yanoo.home.activities.HomeActivity;
import com.anadobes.yanoo.races.manager.RacesManager;
import com.anadobes.yanoo.races.models.Race;

public class RaceDetailsActivity extends SherlockActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.races_racedetails);

		String raceName = this.getIntent().getExtras().getString("raceName");
		
		// Récupération de la course à afficher :
		Race race = RacesManager.instance().getRace(raceName);

		if (race != null) {
			((TextView) findViewById(R.id_racedetails.name)).setText(race.getName());
			((TextView) findViewById(R.id_racedetails.date)).setText((new SimpleDateFormat("dd/MM/yyyy")).format(race.getDate()));
			((TextView) findViewById(R.id_racedetails.description)).setText(race.getDescription());
			((ImageView) findViewById(R.id_racedetails.image)).setImageURI(Uri.parse("/mnt/sdcard/tmp/" + race.getIconFileName()));
		}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
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
            
        default:
            return super.onOptionsItemSelected(item);
		}
    }
}