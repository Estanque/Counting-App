package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
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
	public static final String SETTINGS = "settings";
	public static final String SET_REVERSE = "set_reverse";
	public static final String SET_ADD_RANGE = "set_add_range";
	public static final String SET_MULTI_RANGE = "set_multi_range";
	public static final String SET_SUB_RANGE = "set_sub_range";


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
		SharedPreferences sPref = getSharedPreferences(SETTINGS, MODE_PRIVATE);
		SharedPreferences.Editor ed = sPref.edit();
		ed.putBoolean(SET_REVERSE, swReverse.isChecked());
		ed.putInt(SET_ADD_RANGE, spinnerAdd.getSelectedItemPosition());
		ed.putInt(SET_MULTI_RANGE, spinnerMulti.getSelectedItemPosition());
		ed.putInt(SET_SUB_RANGE, spinnerSub.getSelectedItemPosition());
		ed.commit();
	}

	private void loadSettings() {
		SharedPreferences sPref = getSharedPreferences(SETTINGS, MODE_PRIVATE);
		swReverse.setChecked(sPref.getBoolean(SET_REVERSE, false));

		// Setting spinners ranges
		WidgetHelper.setSpinnerDate(spinnerAdd, getApplicationContext(),
				R.array.set_range, sPref.getInt(SET_ADD_RANGE, 0));
		WidgetHelper.setSpinnerDate(spinnerMulti, getApplicationContext(),
				R.array.set_range, sPref.getInt(SET_MULTI_RANGE, 0));
		WidgetHelper.setSpinnerDate(spinnerSub, getApplicationContext(),
				R.array.set_range, sPref.getInt(SET_SUB_RANGE, 0));
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