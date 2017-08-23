package com.example.spi8a.poolmanager;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ViewMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_match2);
        String s = getIntent().getExtras().getString("position");
        int pos = Integer.valueOf(s);
        //load data from json
        final SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
        final String json = prefs.getString("yourStringName", "default_value_here_if_string_is_missing");
        final Gson gson = new Gson();
        Type type = new TypeToken<List<Match>>(){}.getType();
        final List<Match> matches =  gson.fromJson(json,type);

        Log.d("Player1",matches.get(pos).getPlayer1());
        Log.d("Player2",matches.get(pos).getPlayer2());
        Log.d("Date",matches.get(pos).getDate());
        Log.d("Time",matches.get(pos).getTime());

        //Log.d("Test",matches.get(pos).getSetType().toString());
    }
}
