package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.kaeuc.finalproject.Database.WordDataBaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaeuc on 11/5/2015.
 */
public class MyWords extends Activity {

    public static final String CATEGORY_MYWORDS = "personalDictionary.CATEGORY_MYWORDS";
    public static final String ACTION_MYWORDS = "personalDictionary.ACTION_MYWORDS";
    private ListView listView;
    private Button btn_MyWordsBack;
    private WordDataBaseHelper helper;
    private ArrayList<Map<String, String>> words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_words);
        listView = (ListView) findViewById(R.id.listView);
        btn_MyWordsBack = (Button) findViewById(R.id.btn_MyWordsBack);


        helper = new WordDataBaseHelper(this);
        helper.listWords(words);
        ListView myWordsList = (ListView) findViewById(R.id.listView);
        MyAdapter adapter =  new MyAdapter(this,words);
        myWordsList.setAdapter(adapter);



        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        btn_MyWordsBack.setOnClickListener(clickListener);
        /*AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;

                String itemValue = (String) listView.getItemAtPosition(position);

                Toast.makeText(MyWords.this, itemValue, Toast.LENGTH_SHORT).show();
            }
        };
        listView.setOnItemClickListener(onItemClickListener);*/

    }




}
