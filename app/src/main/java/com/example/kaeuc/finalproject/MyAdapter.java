package com.example.kaeuc.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by kaeuc on 11/30/2015.
 */
public class MyAdapter extends ArrayAdapter<Map<String,String>> {
    public MyAdapter(Context context, List<Map<String, String>> values) {
        super(context,R.layout.my_words_row_layout ,values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The LayoutInflator puts a layout into the right View

        LayoutInflater inflater = LayoutInflater.from(getContext());

        // inflate takes the resource to load, the parent that the resource may be

        // loaded into and true or false if we are loading into a parent view.

        View theView = inflater.inflate(R.layout.my_words_row_layout, parent, false);

        // MUDAR OS GETS POR CONSTANTES

        String wordText = "Word: "+ getItem(position).get("word");
        String definitionText = "Definition: "+ getItem(position).get("definition");
        String sentenceText = "Sentence: "+ getItem(position).get("sentence");
        String finalText = wordText +"\n"+ definitionText+"\n" + sentenceText;

        // Get the TextView we want to edit


        TextView theTextView = (TextView) theView.findViewById(R.id.txt_RowLayout);

        theTextView.setText(finalText);


        return theView;
    }
}
