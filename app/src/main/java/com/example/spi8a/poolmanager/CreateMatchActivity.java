package com.example.spi8a.poolmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static android.R.attr.button;

public class CreateMatchActivity extends AppCompatActivity {


    //final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    //Match match=(mSharedPreference.getClass("Match");


    //Match match = new Match();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);

        //load data from json
        final SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
        final String json = prefs.getString("yourStringName", "default_value_here_if_string_is_missing");
        final Gson gson = new Gson();
        Type type = new TypeToken<List<Match>>(){}.getType();
        final List<Match> matches =  gson.fromJson(json,type);

        final Match match = new Match();
        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                EditText temp = (EditText) findViewById(R.id.player1Text) ;
                match.setPlayer1(temp.getText().toString());
                Log.d("Player1" , match.player1.toString());

                EditText temp1 = (EditText) findViewById(R.id.player2Text) ;
                match.setPlayer2(temp1.getText().toString());
                Log.d("Player2" , match.player2.toString());

                EditText temp2 = (EditText) findViewById(R.id.placeEditText) ;
                match.setPlace(temp2.getText().toString());
                Log.d("Place" , match.place.toString());

                EditText temp3 = (EditText) findViewById(R.id.dateEditText) ;
                match.setDate(temp3.getText().toString());
                Log.d("Date" , match.date.toString());

                EditText temp4 = (EditText) findViewById(R.id.timeEditText) ;
                match.setTime(temp4.getText().toString());
                Log.d("Time" , match.time.toString());

                Log.d("Size" , String.valueOf(matches.size()));
                matches.add(matches.size(),match);
                Log.d("Matches", matches.get(matches.size()-1).player1.toString());
                //save data
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("yourStringName", gson.toJson(matches));
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), MatchActivity.class);
                startActivity(intent);

            }
        });
    }
}
