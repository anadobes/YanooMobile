package com.anadobes.yanoo.races.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.anadobes.net.DownloadFile;
import com.anadobes.yanoo.races.models.Race;

public class RacesData {

	public static List<Race> getRaces() {

		List<Race> list = new ArrayList<Race>();

		try {
			
			Document doc = Jsoup.connect("http://www.yanoo.net/index.php?load=liste_evenement&am=201208").get();

			Elements resultTables = doc.select("table[style=height:100%;]");

			for (Element table : resultTables) {
				Elements tableTitre = table.getElementsByAttributeValue("class", "ligneTitre");
				Elements tablePresentation = table.getElementsByAttributeValue("class", "lignePresentation");
				Elements tableListeCourse = table.getElementsByAttributeValue("class", "listeCourse");

				Elements asTitre = tableTitre.first().getElementsByTag("a");
				String titre = asTitre.first().text();

				Elements tdsPresentation = tablePresentation.first().select("td.presentation");
				String organisation = tdsPresentation.first().text();

				Elements srcsAffiche = tablePresentation.first().getElementsByTag("img");
				String iconUrl = srcsAffiche.first().attr("src");

				String destFolder = "/mnt/sdcard/tmp";
				String iconFilename = (new File(iconUrl)).getName();
				if (!new File(destFolder, iconFilename).exists()) {
					// télécharge l'image (si besoin) :
					DownloadFile.download("http://www.yanoo.net/" + iconUrl, destFolder, iconFilename);
				}

				// Ajout de la nouvelle course :
				list.add(new Race(titre, new Date(2012 - 1900, 9 - 1, 15), organisation, iconFilename));
			}
		}
		catch (Exception e) {
			//Log.d(getClass().getName(), e.getMessage());
		}

		return list;
	}
}
