package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
	}

	public void onClick(View v){
		switch (v.getId()){
			case R.id.btnBegin:

				break;
			case R.id.btnSettings:

				break;
			case R.id.btnExit:
				finish();
				break;
		}
	}
}