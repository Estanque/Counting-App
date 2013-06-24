package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class SettingsActivity extends Activity {

	// Preferences

	// Components
	Switch swReverse;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		initializeComponents();
		loadSettings();
	}

	private void initializeComponents() {
		swReverse = (Switch) findViewById(R.id.swReverse);
	}

	private void saveSettings(){
		SharedPreferences sPref = getSharedPreferences("settings", MODE_PRIVATE);
		SharedPreferences.Editor ed = sPref.edit();
		ed.putBoolean("set_reverse",swReverse.isChecked());
		ed.commit();
	}

	private void loadSettings() {
		SharedPreferences sPref = getSharedPreferences("settings", MODE_PRIVATE);
		swReverse.setChecked(sPref.getBoolean("set_reverse", false));
	}

	public void onClick(View v){
		switch (v.getId()){
			case R.id.btnSave:
				saveSettings();
				finish();
				// TODO saving settings option
				break;
		}
	}
}