package com.anadobes.yanoo.races;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.anadobes.yanoo.R;
import com.anadobes.yanoo.races.data.RaceData;

public class RacesActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.races);

		Toast.makeText(this, "vouvou", Toast.LENGTH_SHORT).show();

		// Test recup data : 
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RaceData.getRacesThread();
			};
		});
	}
}