package com.anadobes.yanoo.races.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.anadobes.net.RestClient;
import com.anadobes.net.RestClient.RequestMethod;

public class RaceData {

	public static void getRacesThread() {

		new Thread() {

			public void run() {
				getRaces();
			}
		}.start();
	}

	private static void getRaces() {

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
			//Log.d(getClass().getName(), e.getMessage());
		}
	}

}
