package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.dinloq.Perfect_Count.framework.WidgetHelper;

public class SettingsActivity extends Activity {

	// Preferences

	// Components
	Switch swReverse;
	Spinner spinnerAdd;
	Spinner spinnerMulti;
	Spinner spinnerSub;

	//Constants
	//TODO set all string constant in to class
	//public static final String SET_REVERSE = "set_reverse";


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		initializeComponents();
		loadSettings();
	}

	private void initializeComponents() {
		swReverse = (Switch) findViewById(R.id.swReverse);
		spinnerAdd = (Spinner) findViewById(R.id.spinnerAddition);
		spinnerMulti = (Spinner) findViewById(R.id.spinnerMultiplication);
		spinnerSub = (Spinner) findViewById(R.id.spinnerSubtraction);
	}

	private void saveSettings(){
		SharedPreferences sPref = getSharedPreferences("settings", MODE_PRIVATE);
		SharedPreferences.Editor ed = sPref.edit();
		ed.putBoolean("set_reverse",swReverse.isChecked());
		ed.putInt("set_add_range", spinnerAdd.getSelectedItemPosition());
		ed.putInt("set_multi_range", spinnerMulti.getSelectedItemPosition());
		ed.putInt("set_sub_range", spinnerSub.getSelectedItemPosition());
		ed.commit();
	}

	private void loadSettings() {
		SharedPreferences sPref = getSharedPreferences("settings", MODE_PRIVATE);
		swReverse.setChecked(sPref.getBoolean("set_reverse", false));

		// Setting spinners ranges
		WidgetHelper.setSpinnerDate(spinnerAdd, getApplicationContext(),
				R.array.set_range, sPref.getInt("set_add_range", 0));
		WidgetHelper.setSpinnerDate(spinnerMulti, getApplicationContext(),
				R.array.set_range, sPref.getInt("set_multi_range", 0));
		WidgetHelper.setSpinnerDate(spinnerSub, getApplicationContext(),
				R.array.set_range, sPref.getInt("set_sub_range", 0));
	}

	public void onClick(View v){
		switch (v.getId()){
			case R.id.btnSave:
				saveSettings();
				finish();
				break;
		}
	}
}