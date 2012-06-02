package com.anadobes.yanoo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.anadobes.net.RestClient;
import com.anadobes.net.RestClient.RequestMethod;

public class RacesActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.races);

		getRacesThread();

		Toast.makeText(this, "vouvou", Toast.LENGTH_SHORT).show();
		
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getRacesThread();
			};
		});
	}
	
	private void getRacesThread() {

		new Thread() {

			public void run() {
				getRaces();
			}
		}.start();
	}

	private void getRaces() {

		RestClient restClient = new RestClient("http://www.yanoo.net/index.php?load=liste_evenement");
		//RestClient restClient = new RestClient("http://www.yanoo.net");

		try {
			restClient.execute(RequestMethod.GET);

			int respCode = restClient.getResponseCode();
			String resp = restClient.getResponse();
			String errorMsg = restClient.getErrorMessage();


			//Scanner scan = new Scanner(resp);
			//scan.

	        Pattern regexToyNameColors = Pattern.compile("<table class=\"ligneTitre\">([\\w|\\s|\r\n]+)</table>");	
	        Matcher toyMatcher = regexToyNameColors.matcher(resp);
	        
			
			String s = "";
		}
		catch (Exception e) {
			Log.d(getClass().getName(), e.getMessage());
		}
	}
}