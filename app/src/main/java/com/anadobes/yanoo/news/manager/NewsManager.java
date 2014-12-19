package com.anadobes.yanoo.news.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.anadobes.yanoo.news.models.News;

public class NewsManager {

	private static NewsManager instance;
	private List<News> newsList;

	private NewsManager() {
		newsList = new ArrayList<News>();
		createTestNews();
	}

	public static NewsManager instance() {
		if (instance == null) {
			instance = new NewsManager();
		}
		return instance;
	}

	private void createTestNews() {
		newsList.add(new News("6 Jours d'Antibes (03-09 Juin) : Classement final", new Date(2012 - 1900, 6 - 1, 6), "Jaouen, Jean-Beno”t (pseudo : jbj)", "Classement 6 Jours (03-09 juin)1 Olivier Chaigne 881 km (record personnel)12 Franck Derrien¤ 660 km (record personnel)16 Christian Efflam!¤@ 627 km"));
		for (int i = 0; i < 10; i++) {
			newsList.add(new News("Lorem ipsum News #" + i, new Date(2012 - 1900, 2 + i, 3 + 2 * i), "wrtier " + i, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent quis velit sed erat faucibus rhoncus ac vel velit. Praesent interdum blandit feugiat. Quisque vel nisl eros. Donec cursus lacus quis dolor iaculis facilisis fermentum dolor porttitor. Maecenas at turpis quis justo vulputate adipiscing dignissim et massa. Proin molestie fringilla erat vel rutrum. Quisque sit amet malesuada mi. Sed nec arcu nisl, vel vulputate diam. Morbi sed justo lacus. Fusce est eros, porttitor a fermentum posuere, eleifend et quam. Cras eget ligula at libero ullamcorper hendrerit. Sed feugiat venenatis mauris id dictum."));
		}
	}

	public List<News> getNewsList() {
		return newsList;
	}
	
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
}
