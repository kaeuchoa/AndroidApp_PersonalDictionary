package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kaeuc on 11/5/2015.
 */
public class MyWords extends ListActivity implements AdapterView.OnItemClickListener {

    public static final String CATEGORY_MYWORDS = "personalDictionary.CATEGORY_MYWORDS";
    public static final String ACTION_MYWORDS = "personalDictionary.ACTION_MYWORDS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String [] baseArray = {"word 1", "word 2", "word 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.menu_layout,baseArray);
        ListView myWordsList = (ListView) findViewById(R.id.listView);
        setListAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
