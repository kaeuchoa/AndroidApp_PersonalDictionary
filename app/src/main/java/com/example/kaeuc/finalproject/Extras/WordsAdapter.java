package com.example.kaeuc.finalproject.Extras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kaeuc.finalproject.R;
import com.example.kaeuc.finalproject.WordClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaeuc on 12/17/2015.
 */
public class WordsAdapter extends ArrayAdapter<WordClass> {
    public WordsAdapter(Context context,ArrayList<WordClass> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        WordClass word = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_words_row_layout, parent, false);
        }
        // Lookup view for data population
        TextView wordLine = (TextView) convertView.findViewById(R.id.txt_WordLine);
        TextView definitionLine = (TextView) convertView.findViewById(R.id.txt_DefinitionLine);
        TextView sentenceLine = (TextView) convertView.findViewById(R.id.txt_SentenceLine);
        // Populate the data into the template view using the data object
        wordLine.setText("Word: "+word.getWord());
        definitionLine.setText("Definition: "+word.getDefinition());
        sentenceLine.setText("Sentence: "+word.getSentence());
        // Return the completed view to render on screen
        return convertView;
    }


}
