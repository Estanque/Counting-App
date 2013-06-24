package com.dinloq.Perfect_Count;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChooseModeActivity extends Activity {

	ListView lvMain;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_chose_dialog);
		lvMain = (ListView) findViewById(R.id.lvMain_dialog);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this,R.array.menu_mode_array,
				android.R.layout.simple_list_item_1);
		lvMain.setAdapter(adapter);
		final Intent chooseIntent = new Intent();

		lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				chooseIntent.putExtra(MenuActivity.CHOOSE_MODE_STRING_EXTRA, position);
				setResult(RESULT_OK, chooseIntent);
				finish();
			}
		});
	}
}