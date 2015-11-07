package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by kaeuc on 11/5/2015.
 */
public class MyWords extends Activity {

    public static final String CATEGORY_MYWORDS = "personalDictionary.CATEGORY_MYWORDS";
    public static final String ACTION_MYWORDS = "personalDictionary.ACTION_MYWORDS";
    private ListView listView;
    private Button btn_MyWordsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_words);
        listView = (ListView) findViewById(R.id.listView);
        btn_MyWordsBack = (Button) findViewById(R.id.btn_MyWordsBack);
        String [] baseArray = {"word 1", "word 2", "word 3", "word 3", "word 3", "word 3", "word 3", "word 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,baseArray);
        ListView myWordsList = (ListView) findViewById(R.id.listView);
        myWordsList.setAdapter(adapter);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        btn_MyWordsBack.setOnClickListener(clickListener);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;

                String itemValue = (String) listView.getItemAtPosition(position);

                Toast.makeText(MyWords.this, itemValue, Toast.LENGTH_SHORT).show();
            }
        };
        listView.setOnItemClickListener(onItemClickListener);

    }


}
