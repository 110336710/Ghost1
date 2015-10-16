package com.example.ilse.ghost;


import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Lexicon {
    private HashSet<String> baseLexicon;
    private HashSet<String> filteredLexicon;
    private String fileName;


    // get lexicon from chosen language
    public Lexicon(Context context,String language) {

        // set language
        if (language.equals("dutch")) {
            fileName = "test.txt";
        } else {
            fileName = "english.txt";
        }

        // load the lexicon
        baseLexicon = new HashSet<>();
        try {
            InputStream input = context.getAssets().open(fileName);
            Scanner scan = new Scanner(input);
            while(scan.hasNextLine()) {
                baseLexicon.add(scan.nextLine().trim().toLowerCase());
            }
            scan.close();
            filteredLexicon = new HashSet<>(baseLexicon);
            Log.d("test filterLex",""+filteredLexicon.size());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filter(String guess){
        // this method takes a string as input and filters the word list using this string.
        // Because loading the lexicon takes quite a bit of time,
        // this method should not destroy the base lexicon and thus allows it to be re-used.
        for (Iterator<String> i = filteredLexicon.iterator(); i.hasNext();) {
            String word = i.next();
            if (!word.startsWith(guess)){
                i.remove();
            }
        }
        Log.d("Final Filter lex", "" + filteredLexicon);
    }



    public int count(){
    //this method returns the length of the words remaining in the filtered list.
        Log.d("SIZE",""+filteredLexicon.size());
        return filteredLexicon.size();
    }
    public String result(){
        // this method returns the single remaining word in the list.
        // Obviously, this method can only be called if count returns the number 1.
        if (count() == 1){
            String resultword = filteredLexicon.iterator().next();
            return resultword;
        }
        else{
            Log.d("READ","There was more than 1 word left");
            return null;
        }
    }
    public HashSet<String> reset(){
        // to remove the filter and re-start with the original lexicon
        filteredLexicon = new HashSet<>(baseLexicon);
        return filteredLexicon;
    }

}



