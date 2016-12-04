package com.google.engedu.ghost;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;


public class SimpleDictionary implements GhostDictionary {
    public ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
                words.add(line.trim());
        }
        // Log.v("tag",words.toString());

    }


    @Override
    public boolean isWord(String word) {

        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        int lb=0,ub= words.size()-1;
        String w=null;
        while(lb<=ub)
        {
            int mid=(lb+ub)/2;
            if((words.get(mid)).startsWith(prefix))
            {   w=words.get(mid);
                //if( (int)(words.get(mid).length()-prefix.length())%2==0)
                {
                    return w;
                }

            }
            else if (((words.get(mid)).compareTo(prefix))>0)
            {
                ub=mid-1;
            }
            else
            {
                lb=mid+1;
            }

        }
       // if(w!=null) return w;

        return w;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}