package com.example.ilse.ghost;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class setplayers extends AppCompatActivity {
    DatabaseHelper myDB;
    AutoCompleteTextView nameplayer1,nameplayer2;
    int language;
    Button btnStartgame;
    Button btnChangeLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setplayers);
        myDB = new DatabaseHelper(this);

        nameplayer1 =(AutoCompleteTextView)findViewById(R.id.player1);
        nameplayer2 =(AutoCompleteTextView)findViewById(R.id.player2);
        btnStartgame =(Button)findViewById(R.id.btnStartgame);
        btnChangeLanguage =(Button)findViewById(R.id.changeLanguageBtn);
        Adduser();

        //make list of al usernames
        ArrayList<String> usernamelist = new ArrayList<String>();
        Cursor result = myDB.getAllData();
        while (result.moveToNext()){
            usernamelist.add(result.getString(1));
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,usernamelist);
        nameplayer1.setAdapter(adapter);
        nameplayer1.setThreshold(1);
        nameplayer1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(setplayers.this)
                        .setTitle("User")
                        .setMessage("Do you really want to play as this user?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(setplayers.this, "Using this username", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                nameplayer1.setText("");
                            }
                        })
                        .show();

            }
        });
        nameplayer2.setAdapter(adapter);
        nameplayer2.setThreshold(1);
        nameplayer2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(setplayers.this)
                        .setTitle("User")
                        .setMessage("Do you really want to play as this user?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(setplayers.this, "Using this username", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                nameplayer2.setText("");
                            }
                        })
                        .show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setplayers, menu);
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
    public void setlanguage(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.checkenglish:
                if (checked){
                    language = 0;
                    }
                break;
            case R.id.checkdutch:
                if (checked){
                    language = 1;
                    }

                }
        }

    public void Adduser(){
        btnStartgame.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //make list of al usernames
                    ArrayList<String> usernamelist = new ArrayList<String>();
                    Cursor result = myDB.getAllData();
                    while (result.moveToNext()){
                        usernamelist.add(result.getString(1));
                    }
                    final String stringPlayer1 = nameplayer1.getText().toString();
                    final String stringPlayer2 = nameplayer2.getText().toString();
                    //check if new username player1 already exists
                    if (usernamelist.contains(stringPlayer1)) {
                        Toast.makeText(setplayers.this, "Username player 1 already exists", Toast.LENGTH_LONG).show();
                        if (usernamelist.contains(stringPlayer2)){
                            Toast.makeText(setplayers.this, "Username player 2 also already exists", Toast.LENGTH_LONG).show();
                        }
                        else {
                            boolean isInsertedPlayer2 = myDB.insertData(nameplayer2.getText().toString(), 0, language);
                            if (isInsertedPlayer2) {
                                Toast.makeText(setplayers.this, "data player 2 inserted", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(setplayers.this, "data player 2 not inserted", Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                    //check if new username player2 already exists
                    else if (usernamelist.contains(stringPlayer2)) {
                        Toast.makeText(setplayers.this, "Username player 2 already exists", Toast.LENGTH_LONG).show();
                        boolean isInsertedPlayer1 = myDB.insertData(nameplayer1.getText().toString(), 0, language);
                        if (isInsertedPlayer1) {
                            Toast.makeText(setplayers.this, "data player 1 inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(setplayers.this, "data player 1 not inserted", Toast.LENGTH_LONG).show();
                        }
                    }

                    else{
                        boolean isInsertedPlayer1 = myDB.insertData(nameplayer1.getText().toString(), 0, language);
                        boolean isInsertedPlayer2 = myDB.insertData(nameplayer2.getText().toString(), 0, language);
                        if (isInsertedPlayer1 && isInsertedPlayer2) {
                            Toast.makeText(setplayers.this, "data both inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(setplayers.this, "data not inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                    Intent startGame = new Intent(getApplicationContext(), GameActivity.class);
                    startGame.putExtra("username player 1", stringPlayer1);
                    startGame.putExtra("username player 2", stringPlayer2);
                    startActivity(startGame);
                }

            }
        );
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}




