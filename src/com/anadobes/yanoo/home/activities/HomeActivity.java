package com.anadobes.yanoo.home.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockActivity;
import com.anadobes.yanoo.R;
import com.anadobes.yanoo.news.activities.NewsListActivity;
import com.anadobes.yanoo.races.activities.RacesListActivity;

public class HomeActivity extends SherlockActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
	}

	public void clickNews(View v) {
		Intent i = new Intent(this, NewsListActivity.class);
		startActivity(i);
	}

	public void clickCourses(View v) {
		Intent i = new Intent(this, RacesListActivity.class);
		startActivity(i);
	}
}
