package com.example.spi8a.poolmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;

public class MatchActivity extends AppCompatActivity {
    private boolean turn = true; // true -> player1 , false -> player2
    private int counter=-1,move =0;
    Spinner ball,result,shot,ballPreMatch;
    String playerStr ,moveStr;
    LinearLayout linearBot,linearPreMatch;
    RelativeLayout relativeTop;
    Button button;
    ToggleButton smashBtn,dryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_match);
        setContentView(R.layout.listview);
        //load data from json
        final SharedPreferences prefs = getSharedPreferences("your_file_name", MODE_PRIVATE);
        final String json = prefs.getString("yourStringName", "default_value_here_if_string_is_missing");
        final Gson gson = new Gson();
        Type type = new TypeToken<List<Match>>(){}.getType();
        final List<Match> matches =  gson.fromJson(json,type);

        linearPreMatch = (LinearLayout) findViewById(R.id.linearPreMatch);
        button = (Button) findViewById(R.id.nextMove);
        relativeTop = (RelativeLayout) findViewById(R.id.relativeTop);
        linearBot = (LinearLayout) findViewById(R.id.linearbot);
        smashBtn = (ToggleButton) findViewById(R.id.smashToggleButton);
        smashBtn.setTextOff(matches.get(matches.size()-1).getPlayer2());
        smashBtn.setTextOn(matches.get(matches.size()-1).getPlayer1());
        smashBtn.setText(matches.get(matches.size()-1).getPlayer1());
        dryBtn = (ToggleButton) findViewById(R.id.dryToggleButton);
        dryBtn.setTextOff("NO");
        dryBtn.setTextOn("YES");
        dryBtn.setText("YES");
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
        ballPreMatch = (Spinner) findViewById(R.id.dryBallSpinner);
        //ballPreMatch.setAdapter(spinnerArrayAdapter);

        final String[] select_qualification = {
                "Choose Balls", "1", "2", "3",
                "4", "5","6","7","8","9"};

        final ArrayList<StateVO> listVOs = new ArrayList<>();

        for (int i = 0; i < select_qualification.length; i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(select_qualification[i]);
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }
        final MyAdapter myAdapter = new MyAdapter(MatchActivity.this, 0,
                listVOs);
        ballPreMatch.setAdapter(myAdapter);



        final Button button2 = (Button) findViewById(R.id.startGameButton);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                linearPreMatch.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                relativeTop.setVisibility(View.VISIBLE);
                linearBot.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                for(int i=0 ; i <listVOs.size(); i++) {
                    if(listVOs.get(i).isSelected()) {
                        Log.d("List",listVOs.get(i).toString() + "stin thesi " + i  );
                    }
                    Log.d("List",listVOs.get(i).isSelected() + "stin thesi " + i  );
                }

            }

        });
        //ConstraintLayout topLayout = (ConstraintLayout) findViewById(R.id.ConstraintLayout);
        ListView listView = (ListView) findViewById(R.id.card_listView);

        final TextView playerText = (TextView) findViewById(R.id.title),moveText = (TextView) findViewById(R.id.moveText);



        ball = (Spinner) findViewById(R.id.ballSpinner);
        ball.setAdapter(spinnerArrayAdapter);
        ball.setPrompt("Choose Ball");
        //ball.setSelection(spinnerArray.size()-1,true);
        Log.d("lista-prin",spinnerArray.toString());
        final ArrayList<Move> player1 = new ArrayList<Move> (),player2 = new ArrayList<Move> (); //listes me tis kiniseis twn paiktwn
        final Move tempMove = new Move(); //kinisi se auton ton guro

        //Log.d("Test",matches.get(matches.size()-1).player1);

        final CardArrayAdapter cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);

        if(turn) {
            playerText.setText(matches.get(matches.size()-1).getPlayer1());
            playerStr = matches.get(matches.size()-1).getPlayer1();
        } else {
            playerStr = matches.get(matches.size()-1).getPlayer2();
            playerText.setText(matches.get(matches.size()-1).getPlayer2());
        }
        moveText.setText("1");
        moveStr =  "1";



        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //TO DO

                //Na valw menu sto create_match gia to poios paizei prwtos
                //kai an ekane kapoio (!!den thimamai tin leksi ine 6.30 to prwi :( )
                //   Na katalavw pote teliwnei to kathe paixnidi
                // extra features i periptwseis

                result = (Spinner) findViewById(R.id.resultSpinner);
                shot = (Spinner) findViewById(R.id.shotSpinner);

                tempMove.setShotype(ShotType.valueOf((String) shot.getSelectedItem()));
                tempMove.setMove(MoveCompletion.valueOf((String) result.getSelectedItem()));
                tempMove.setNumber(Integer.valueOf((String) ball.getSelectedItem()));

                if(String.valueOf(result.getSelectedItem()).equals("DONE")  ) {
                    // Log.d("selected", String.valueOf(ball.getSelectedItem()));
                    //Log.d("lista",spinnerArray.toString());
                    //Log.d("index of selection", String.valueOf(spinnerArray.indexOf(ball.getSelectedItem())));
                    spinnerArray.remove(ball.getSelectedItem());
                    ball.setSelection(0);
                    if( spinnerArray.isEmpty()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                    //Log.d("lista",spinnerArray.toString());

                } else {

                    turn = !turn;
                }
                if(turn) {
                    player1.add(player1.size(),tempMove);
                    playerStr = matches.get(matches.size()-1).getPlayer1();
                    moveStr = String.valueOf(player1.size() );
                    moveText.setText(moveStr);
                    playerText.setText(playerStr);

                } else {
                    player2.add(player2.size(),tempMove);
                    playerStr = matches.get(matches.size()-1).getPlayer2();
                    moveStr = String.valueOf(player2.size() );
                    moveText.setText(moveStr);
                    playerText.setText(playerStr);
                }

                Card card = new Card(playerStr + " " + moveStr  ,String.valueOf(tempMove.number) + "\n" +String.valueOf(tempMove.move) + "\n" + String.valueOf(tempMove.shotype)  );
                cardArrayAdapter.add(card);

                Log.d("Turn", String.valueOf(turn));
                Log.d("Paiktis", playerStr);
                Log.d("MoveStr", moveStr);
                Log.d("Move",tempMove.toString());
                Log.d("Player1",player1.toString());
                Log.d("Player2",player2.toString());

                //turn = !turn;
                Log.d("Ball", String.valueOf(ball.getSelectedItem()));
                Log.d("Result", String.valueOf(result.getSelectedItem()));
                Log.d("Shot", String.valueOf(shot.getSelectedItem()));
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
