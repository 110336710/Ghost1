package com.example.ilse.ghost;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    Button btnChangeLanguage,btnPutLetter;
    DatabaseHelper myDB;
    TextView playersTurn,gameword;
    EditText guess;
    String guessString, guessLetter, player1, player2, playerIsTurn;
    public Lexicon lexicon;
    public Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        myDB = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        player1 = bundle.getString("username player 1");
        player2 = bundle.getString("username player 2");

        btnChangeLanguage =(Button)findViewById(R.id.changeLanguageBtn);
        btnPutLetter =(Button)findViewById(R.id.putLetter);
        playersTurn =(TextView)findViewById(R.id.showPlayersTurn);
        guess = (EditText)findViewById(R.id.playersInput);
        gameword = (TextView)findViewById(R.id.ghostword);
        lexicon = new Lexicon(getApplicationContext(), "dutch");
        game = new Game(lexicon,player1,player2);

        checkLexicon();
        putLetter();

        playersTurn.setText(game.turnStart());


    }
    private String inputLetter(){
        guessString = guess.getText().toString();
        guess.setText("");
        return guessString;
    }
    public void checkLexicon(){
        btnChangeLanguage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }

                }
        );
    }
    public void putLetter(){
        btnPutLetter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (gameword.getText().toString().equals("........")){
                            guessLetter = inputLetter();
                            gameword.setText(guessLetter);
                        }
                        else{
                            guessLetter = inputLetter();
                            gameword.setText(gameword.getText().toString()+guessLetter);
                        }
                        game.guess(gameword.getText().toString());
                        playersTurn.setText(game.turn());
                    }

                }
        );
    }
//    public void scoreCount{
//        if (game.ended()){
//
//        }
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
