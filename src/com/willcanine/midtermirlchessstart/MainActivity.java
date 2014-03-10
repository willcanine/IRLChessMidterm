package com.willcanine.midtermirlchessstart;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    Button startButton;
    
    Intent toStartGameActivity;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.v("onCreate", "saved instance state, set content view");
        
        startButton = (Button) this.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		toStartGameActivity = new Intent(this, StartGame.class);
		startActivity(toStartGameActivity);
		
	}


	


	
	


	
    
}
