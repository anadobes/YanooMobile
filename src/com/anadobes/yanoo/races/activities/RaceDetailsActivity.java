package com.anadobes.yanoo.races.activities;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.anadobes.yanoo.R;
import com.anadobes.yanoo.races.manager.RacesManager;
import com.anadobes.yanoo.races.models.Race;

public class RaceDetailsActivity extends Activity {

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
		}
	}
}