package com.willcanine.midtermirlchessstart;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartGame extends Activity implements OnClickListener {
	
	EditText eventField;
	EditText locationField;
	EditText playingWhiteField;
	EditText playingBlackField;
	
	Intent toPlayGameActivity;
	
	Button saveButton;
	
	SharedPreferences event;
	SharedPreferences location;
	SharedPreferences playingWhite;
	SharedPreferences playingBlack;
	SharedPreferences date;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_game);
		
		eventField = (EditText) this.findViewById(R.id.event_field);
		locationField = (EditText) this.findViewById(R.id.location_field);
		playingWhiteField = (EditText) this.findViewById(R.id.playing_white_field);
		playingBlackField = (EditText) this.findViewById(R.id.playing_black_field);
		
		
		saveButton = (Button) this.findViewById(R.id.save_button);
		saveButton.setOnClickListener(this);
		
		event = getSharedPreferences("event_text", MODE_PRIVATE);
		location = getSharedPreferences("location_text", MODE_PRIVATE);
		playingWhite = getSharedPreferences("playing_white_text", MODE_PRIVATE);
		playingBlack = getSharedPreferences("playing_black_text", MODE_PRIVATE);
		date = getSharedPreferences("date_text", MODE_PRIVATE);
		
	}

	public void onClick(View v) {
	
		SharedPreferences.Editor eventEditor = event.edit();
		eventEditor.putString("location_string", eventField.getText().toString());
		eventEditor.commit();
		
		SharedPreferences.Editor locationEditor = location.edit();
		locationEditor.putString("location_string", locationField.getText().toString());
		locationEditor.commit();
		
		SharedPreferences.Editor playingWhiteEditor = playingWhite.edit();
		playingWhiteEditor.putString("location_string", playingWhiteField.getText().toString());
		playingWhiteEditor.commit();
		
		SharedPreferences.Editor playingBlackEditor = playingBlack.edit();
		playingBlackEditor.putString("location_string", playingBlackField.getText().toString());
		playingBlackEditor.commit();
		
		SharedPreferences.Editor dateEditor = date.edit();
		dateEditor.putString("location_string", locationField.getText().toString());
		dateEditor.commit();

		Toast.makeText(this, eventField.getText().toString(), Toast.LENGTH_LONG);
		
		toPlayGameActivity = new Intent(this, PlayGame.class);
		startActivity(toPlayGameActivity);
		
	}

	

}
