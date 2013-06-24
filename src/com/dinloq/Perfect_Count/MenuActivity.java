package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
	}

	public void onClick(View v){
		Intent intent;
		switch (v.getId()){
			case R.id.btnBegin:
				intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				break;
			case R.id.btnSettings:
				intent = new Intent(this, SettingsActivity.class);
				startActivity(intent);
				break;
			case R.id.btnStatistic:
				//TODO Statistic Activity
				break;
			case R.id.btnExit:
				finish();
				break;
		}
	}
}