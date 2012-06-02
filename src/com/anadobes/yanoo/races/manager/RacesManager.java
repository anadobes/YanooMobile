package com.anadobes.yanoo.races.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.anadobes.yanoo.races.models.Race;

public class RacesManager {

	List<Race> racesList;

	public RacesManager() {
		racesList = new ArrayList<Race>();
		createTestRaces();
	}

	private void createTestRaces() {
		racesList.add(new Race("Foul�e des dou�s", new Date(2012 - 1900, 9 - 1, 15), "Allez y les gars."));
		for (int i = 0; i < 10; i++) {
			racesList.add(new Race("Lorem ipsum Run #" + i, new Date(2012 - 1900, 2 + i, 3 + 2 * i), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent quis velit sed erat faucibus rhoncus ac vel velit. Praesent interdum blandit feugiat. Quisque vel nisl eros. Donec cursus lacus quis dolor iaculis facilisis fermentum dolor porttitor. Maecenas at turpis quis justo vulputate adipiscing dignissim et massa. Proin molestie fringilla erat vel rutrum. Quisque sit amet malesuada mi. Sed nec arcu nisl, vel vulputate diam. Morbi sed justo lacus. Fusce est eros, porttitor a fermentum posuere, eleifend et quam. Cras eget ligula at libero ullamcorper hendrerit. Sed feugiat venenatis mauris id dictum."));
		}
	}

	public List<Race> getRacesList() {
		return racesList;
	}
}
