package com.willcanine.midtermirlchessstart;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StartGame extends Activity implements OnClickListener {
	
	EditText locationField;
	Button saveButton;
	
	SharedPreferences location;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_game);
		
		locationField = (EditText) this.findViewById(R.id.location_field);
		
		saveButton = (Button) this.findViewById(R.id.save_location_button);
		saveButton.setOnClickListener(this);
		
		location = getSharedPreferences("location_text", MODE_PRIVATE);
		SharedPreferences.Editor locationEditor = location.edit();
		
	}

	@Override
	public void onClick(View v) {
	
		SharedPreferences.Editor locationEditor = location.edit();
		locationEditor.putString("location_string", locationField.getText().toString());
		
		
	}

	

}
