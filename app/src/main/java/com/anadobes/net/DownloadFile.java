package com.anadobes.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadFile {

	public static void download(String urlFile, String folderFile, String destinationFileName) {

		try {
			URL url = new URL(urlFile);

			//create the new connection
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			//set up some things on the connection
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);

			//and connect!
			urlConnection.connect();

			//create a new file, specifying the path, and the filename
			//which we want to save the file as.
			File file = new File(folderFile, destinationFileName);

			//this will be used to write the downloaded data into the file we created
			FileOutputStream fileOutput = new FileOutputStream(file);

			//this will be used in reading the data from the internet
			InputStream inputStream = urlConnection.getInputStream();

			//create a buffer...
			byte[] buffer = new byte[1024];
			int bufferLength = 0; //used to store a temporary size of the buffer

			//now, read through the input buffer and write the contents to the file
			while ((bufferLength = inputStream.read(buffer)) > 0) {
				//add the data in the buffer to the file in the file output stream (the file on the sd card
				fileOutput.write(buffer, 0, bufferLength);
			}
			//close the output stream when done
			fileOutput.close();

			//catch some possible errors...
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
