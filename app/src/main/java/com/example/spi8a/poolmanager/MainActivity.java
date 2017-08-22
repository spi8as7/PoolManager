package com.example.spi8a.poolmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Array of strings...(matches loaded from file
    public static final String MyPREFERENCES = "MyPrefs" ;
    String string = "hello world!";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.matchList);


        //load stored matches
        SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
        String json = prefs.getString("yourStringName", "default_value_here_if_string_is_missing");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Match>>(){}.getType();
         //final List<Match> matches =  gson.fromJson(json,type);
        final List<Match> matches = new ArrayList<Match>();
        //create list for listview based on stored matches
        List<String> mylist = new ArrayList<String>();
        for(int i=0; i < matches.size(); i++) {
            mylist.add(i,matches.get(i).player1 + " VS " + matches.get(i).player2);
        }

        SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mylist);
        listView.setAdapter(adapter);


        //save data to json
        SharedPreferences.Editor editor = prefs.edit();
        json = gson.toJson(matches);
        editor.putString("yourStringName", json);
        editor.commit();



       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button_fab_with_listview);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CreateMatchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
