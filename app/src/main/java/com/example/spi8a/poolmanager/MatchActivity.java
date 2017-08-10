package com.example.spi8a.poolmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.view.View.GONE;

public class MatchActivity extends AppCompatActivity {
    private boolean turn = true; // true -> player1 , false -> player2
    private int counter=-1,move =0;
    Spinner ball,result,shot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_match);


        setContentView(R.layout.listview);
        //ConstraintLayout topLayout = (ConstraintLayout) findViewById(R.id.ConstraintLayout);
        ListView listView = (ListView) findViewById(R.id.card_listView);
        //load data from json
        final SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
        final String json = prefs.getString("yourStringName", "default_value_here_if_string_is_missing");
        final Gson gson = new Gson();
        Type type = new TypeToken<List<Match>>(){}.getType();
        final List<Match> matches =  gson.fromJson(json,type);

        //initialize ball list
        final ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("1");
        spinnerArray.add("2");
        spinnerArray.add("3");
        spinnerArray.add("4");
        spinnerArray.add("5");
        spinnerArray.add("6");
        spinnerArray.add("7");
        spinnerArray.add("8");
        spinnerArray.add("9");

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, simple_spinner_dropdown_item, spinnerArray);
        ball = (Spinner) findViewById(R.id.ballSpinner);
        ball.setAdapter(spinnerArrayAdapter);
        ball.setPrompt("Choose Ball");
        //ball.setSelection(spinnerArray.size()-1,true);
        Log.d("lista-prin",spinnerArray.toString());
        ArrayList<Move> player1,player2 = new ArrayList<Move> ();

        //Log.d("Test",matches.get(matches.size()-1).player1);

        final CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);
        Button button = (Button) findViewById(R.id.nextMove);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //TO DO
                //Na valw menu sto create_match gia to poios paizei prwtos
                //kai an ekane kapoio (!!den thimamai tin leksi ine 6.30 to prwi :( )
                // Na katalavw pote paizei o kathe paiktis
                //  Na katalavw poios guros ine(sunolikos kai tou paikti
                //   Na katalavw pote teliwnei to kathe paixnidi
                // extra features i periptwseis

                result = (Spinner) findViewById(R.id.resultSpinner);
                shot = (Spinner) findViewById(R.id.shotSpinner);
                if(String.valueOf(result.getSelectedItem()).equals("DONE")  ) {
                    Card card = new Card("Player1 Move1"  ,String.valueOf(ball.getSelectedItem()) + "\n" +String.valueOf(result.getSelectedItem()) + "\n" + String.valueOf(shot.getSelectedItem())  );
                    cardArrayAdapter.add(card);
                    // Log.d("selected", String.valueOf(ball.getSelectedItem()));
                    //Log.d("lista",spinnerArray.toString());
                    //Log.d("index of selection", String.valueOf(spinnerArray.indexOf(ball.getSelectedItem())));
                    spinnerArray.remove(ball.getSelectedItem());
                    ball.setSelection(0);
                    //Log.d("lista",spinnerArray.toString());

                } else {
                    Card card = new Card("Player1 Move1"  ,String.valueOf(ball.getSelectedItem()) + "\n" +String.valueOf(result.getSelectedItem()) + "\n" + String.valueOf(shot.getSelectedItem())  );
                    cardArrayAdapter.add(card);
                }


                //turn = !turn;
                //Log.d("Ball", String.valueOf(ball.getSelectedItem()));
                //Log.d("Result", String.valueOf(result.getSelectedItem()));
                //Log.d("Shot", String.valueOf(shot.getSelectedItem()));
            }
        });

        listView.setAdapter(cardArrayAdapter);

        //topLayout.addView(listView);
        //TextView text = (TextView) findViewById(R.id.textView2);
        //text.setText("test");
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    */}

}
