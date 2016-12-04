package com.google.engedu.ghost;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.widget.Toast;
import java.io.*;
import java.util.ArrayList;
import java.lang.*;
import java.util.*;
//import java.com.SimpleDictionary;


public class GhostActivity extends ActionBarActivity  {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    int KeyCode; KeyEvent event;
    int totalscore=0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AssetManager assetManager = getAssets();


        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new FastDictionary(inputStream);
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        //
        try {
            onStart(null);
        } catch (IOException e) {
            e.printStackTrace();
        }



        final TextView textElement;
        textElement = (TextView) findViewById(R.id.textView2);
        Button reset = (Button) findViewById(R.id.button2);
        TextView Score = (TextView) findViewById(R.id.textView4);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textElement.setText("");
                TextView label1 = (TextView) findViewById(R.id.textView3);
                label1.setText("");
                TextView w1 = (TextView) findViewById(R.id.textView);
                w1.setText("");



            }
        });
        Button Challenge = (Button) findViewById(R.id.button);

        Challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textElement;
                textElement = (TextView) findViewById(R.id.textView2);
                String t = (String) textElement.getText();
                TextView label1 = (TextView) findViewById(R.id.textView3);
                TextView w = (TextView) findViewById(R.id.textView);
                TextView Score = (TextView) findViewById(R.id.textView4);

                if(t.length()>=3 )
                    if(dictionary.getAnyWordStartingWith(t)!=null)
                  { w.setText(dictionary.getAnyWordStartingWith(t));
                    label1.setText("COMPUTER WON!! :D ");}
                else
                    {
                        label1.setText("USER WON!! ");
                        totalscore+=1;
                        Score.setText(Integer.toString(totalscore));
                    }


            }
        });

        // onKeyUp(KeyCode,event);


        try {
            onStart(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onKeyUp(int KeyCode, KeyEvent event) {
        TextView textElement;
        textElement = (TextView) findViewById(R.id.textView2);
        String t = (String) textElement.getText();
        char c = (char) event.getUnicodeChar();
        if (c <= 'z' && c >= 'a' || c <= 'Z' && c >= 'A') {
            t = t + c;
            textElement.setText(t);
            userTurn=false;
            if(dictionary.isWord(t)) {
                TextView label1 = (TextView) findViewById(R.id.textView3);
                label1.setText("COMPUTER WON!! :D ");
            }                ;
           // textElement.setText(COMPUTER_TURN);
            try {
                computerTurn();
                userTurn= false;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            super.onKeyUp(KeyCode, event);
            return false;
        }


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
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

    private void computerTurn() throws IOException {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        TextView textElement;
        textElement = (TextView) findViewById(R.id.textView2);
        String t = (String) textElement.getText();
        Random r= new Random();

        String w= dictionary.getAnyWordStartingWith(t);
        if(w!=null) {
            char c = w.charAt(t.length());
            t = t + c;
            textElement.setText(t);
            TextView Score = (TextView) findViewById(R.id.textView4);
            if (dictionary.isWord(t)) {
                TextView label1 = (TextView) findViewById(R.id.textView3);
                label1.setText("USER WON!! :D ");
                totalscore+=1;
                Score.setText(Integer.toString(totalscore));

            }
        }
        // Do computer turn stuff then make it the user's turn again
       else
        {
            TextView label1 = (TextView) findViewById(R.id.textView3);
            label1.setText("USER LOST!! :( ");

        }
        userTurn = true;
        label.setText(USER_TURN);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) throws IOException {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }
}