package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kaeuc.finalproject.Database.WordDataBaseHelper;
import com.example.kaeuc.finalproject.Extras.Constants;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by kaeuc on 11/5/2015.
 */
public class MyWordsActivity extends Activity {

    public static final String CATEGORY_MYWORDS = "personalDictionary.CATEGORY_MYWORDS";
    public static final String ACTION_MYWORDS = "personalDictionary.ACTION_MYWORDS";
    private ListView listView;
    private Button btn_MyWordsBack;
    private WordDataBaseHelper helper;
    private ArrayList<Map<String, String>> words;
    private Intent previousIntent;
    private static final Constants constants = new Constants();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_words);
        listView = (ListView) findViewById(R.id.listView);
        btn_MyWordsBack = (Button) findViewById(R.id.btn_MyWordsBack);
        words = new ArrayList<>();

        helper = new WordDataBaseHelper(this);
        previousIntent = getIntent();

        String langName = previousIntent.getStringExtra(constants.LANG_ID);
        helper.listWords(words,langName,this);
        Toast.makeText(MyWordsActivity.this, ""+ words.size(), Toast.LENGTH_SHORT).show();

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
  /*      AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;

                String itemValue = (String) listView.getItemAtPosition(position);

                Toast.makeText(MyWordsActivity.this, itemValue, Toast.LENGTH_SHORT).show();
            }
        };
        listView.setOnItemClickListener(onItemClickListener);*/

    }




}
