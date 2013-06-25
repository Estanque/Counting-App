package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

	public static final String CHOOSE_MODE_STRING_EXTRA = "com.dinloq.Perfect_Count.chosen_mode";
	private final int IDD_LIST_MODES = 1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
	}

	public void onClick(View v){
		Intent intent;
		switch (v.getId()){
			case R.id.btnBegin:
				intent = new Intent(MenuActivity.this, ChooseModeActivity.class);
				startActivityForResult(intent, IDD_LIST_MODES);
				break;
			case R.id.btnSettings:
				intent = new Intent(this, SettingsActivity.class);
				startActivity(intent);
				break;
			case R.id.btnStatistic:
				intent = new Intent(this, StatisticActivity.class);
				startActivity(intent);
				break;
			case R.id.btnExit:
				finish();
				break;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode,resultCode,data);
		if (requestCode == IDD_LIST_MODES) {
			if (resultCode == RESULT_OK) {
				//Toast.makeText(this, data.getIntExtra(CHOOSE_MODE_STRING_EXTRA, -1) + "", Toast.LENGTH_SHORT).show();
				int chosen_mode = data.getIntExtra(CHOOSE_MODE_STRING_EXTRA, -1);
				Intent intent = new Intent(this, MainActivity.class);
				if (chosen_mode != -1) {
					intent.putExtra(CHOOSE_MODE_STRING_EXTRA, chosen_mode);
					startActivity(intent);
				}
			}
		}
	}
}