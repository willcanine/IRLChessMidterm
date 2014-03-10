package com.willcanine.midtermirlchessstart;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlayGame extends Activity implements OnClickListener {
	
	SharedPreferences gameMovesHolder;
	ArrayList<GameMove> moves;
	
	SharedPreferences whoWins;
	
	Intent shareGameDataIntent;
	
	EditText enterMoveField;
	
	TextView turnIndicator;
	
	Button enterMoveButton;
	Button whiteWinsButton;
	Button blackWinsButton;
	
	Boolean turn = true;
	int turnNum = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);
		
		shareGameDataIntent.setAction(Intent.ACTION_SEND);
		
		whoWins = getSharedPreferences("who_won", MODE_PRIVATE);
		
		enterMoveField = (EditText) this.findViewById(R.id.enter_move_field);
		
		turnIndicator = (TextView) this.findViewById(R.id.turn_indicator);
		
		enterMoveButton = (Button) this.findViewById(R.id.enter_move_button);
		enterMoveButton.setOnClickListener(this);

		whiteWinsButton = (Button) this.findViewById(R.id.white_wins_button);
		whiteWinsButton.setOnClickListener(this);
		
		blackWinsButton = (Button) this.findViewById(R.id.black_wins_button);
		blackWinsButton.setOnClickListener(this);
		
	}
		
	@Override
	public void onClick(View v) {
		
		if (v == enterMoveButton) {
			
			if (turn == true){
				//WE ARE ABOUT TO RECORD WHITE'S MOVE, SWITCH TO BLACK'S TURN
	    		turnIndicator.setText(R.string.black_to_play);
	    		
	    		//PARSE OUT THE JSON into an ARRAY LIST OF GAMEMOVES
	    		String jsonMoveString = gameMovesHolder.getString("jsonMoves", null);
	    		
	    		if (jsonMoveString != null) {
	    			Gson gson = new Gson();
	    			GameMove[] previousMoves = gson.fromJson(jsonMoveString, GameMove[].class);
	    			moves = new ArrayList(Arrays.asList(previousMoves));
	    			} else {
	    				moves = new ArrayList<GameMove>();
	    				}

	    		// ADD WHITE'S NEW MOVE TO THE ARRAYLIST OF MOVES
	    		GameMove newMove = new GameMove();
		            newMove.moveNum = turnNum+". ";
		            newMove.moveNote = enterMoveField.getText().toString();
		            
		         moves.add(newMove);
		         
		         // WRAP IT ALL BACK INTO JSON FORMAT AND PUT IT BACK INTO THE SHARED PREFERENCE
		         Gson newGson = new Gson();
		         String newJsonString = newGson.toJson(moves);
		         
		         SharedPreferences.Editor spmj = gameMovesHolder.edit();
		         spmj.putString("json", newJsonString);
		         spmj.commit();
		         
		         // SWITCH TO BLACK'S TURN
		         turn = false;
	    	
	    		
	    	}
	    	else if (turn == false){
	    		//WE ARE ABOUT TO RECORD BLACK'S MOVE, SWITCH TO WHITE'S TURN
	    		turnIndicator.setText(R.string.black_to_play);
	    		
	    		//PARSE OUT THE JSON into an ARRAY LIST OF GAMEMOVES
	    		String jsonMoveString = gameMovesHolder.getString("jsonMoves", null);
	    		
	    		if (jsonMoveString != null) {
	    			Gson gson = new Gson();
	    			GameMove[] previousMoves = gson.fromJson(jsonMoveString, GameMove[].class);
	    			moves = new ArrayList(Arrays.asList(previousMoves));
	    			} else {
	    				moves = new ArrayList<GameMove>();
	    				}

	    		// ADD BLACK'S NEW MOVE TO THE ARRAYLIST OF MOVES
	    		GameMove newMove = new GameMove();
		            newMove.moveNum = " ... ";
		            newMove.moveNote = enterMoveField.getText().toString();
		            
		         moves.add(newMove);
		         
		         // WRAP IT ALL BACK INTO JSON FORMAT AND PUT IT BACK INTO THE SHARED PREFERENCE
		         Gson newGson = new Gson();
		         String newJsonString = newGson.toJson(moves);
		         
		         SharedPreferences.Editor spmj = gameMovesHolder.edit();
		         spmj.putString("json", newJsonString);
		         spmj.commit();
		         
		         // SWITCH TO BLACK'S TURN
		         turn = false;
		         
		         //BOTH PLAYERS HAVE MOVED, INCRIMENT ONE TURN
	    		 turnNum++;
	    		
	    	}
			
		} else if (v == whiteWinsButton){
			
			//PULL EVERYTHING OUT OF JSON AND PUT IT IN AN ARRAY
			String jsonMoveString = gameMovesHolder.getString("jsonMoves", null);
			Gson gson = new Gson();
			GameMove[] allTheMoves = gson.fromJson(jsonMoveString, GameMove[].class);
			
			//PUT THE ARRAY THROUGH THE SHARE INTENET
			shareGameDataIntent.putExtra("all_the_moves", allTheMoves);
			shareGameDataIntent.setType(".pgn");
    		startActivity(Intent.createChooser(shareGameDataIntent, "Share game moves to.."));
    		
    		SharedPreferences.Editor whoWinsEditor = whoWins.edit();
    		whoWinsEditor.putString("who_won_string", "White Wins 1-0");
    		whoWinsEditor.commit();

		} else if (v == blackWinsButton){
			
			//PULL EVERYTHING OUT OF JSON AND PUT IT IN AN ARRAY
			String jsonMoveString = gameMovesHolder.getString("jsonMoves", null);
			Gson gson = new Gson();
			GameMove[] allTheMoves = gson.fromJson(jsonMoveString, GameMove[].class);
			
			//PUT THE ARRAY THROUGH THE SHARE INTENET
			shareGameDataIntent.putExtra("all_the_moves", allTheMoves);
			shareGameDataIntent.setType(".pgn");
    		startActivity(Intent.createChooser(shareGameDataIntent, "Share game moves to.."));
    		
    		SharedPreferences.Editor whoWinsEditor = whoWins.edit();
    		whoWinsEditor.putString("who_won_string", "Black Wins 0-1");
    		whoWinsEditor.commit();
			
		}
	}
	
	public class GameMove {
		String moveNum;
		String moveNote;
	}

	

}
