package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaeuc.finalproject.Database.WordDataBaseHelper;
import com.example.kaeuc.finalproject.Extras.Constants;
import com.example.kaeuc.finalproject.Extras.WordDialog;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaeuc on 11/5/2015.
 */
public class MyWordsActivity extends Activity {

    public static final String CATEGORY_MYWORDS = "personalDictionary.CATEGORY_MYWORDS";
    public static final String ACTION_MYWORDS = "personalDictionary.ACTION_MYWORDS";
    private Button btn_MyWordsBack;
    private WordDataBaseHelper helper;
    private ArrayList<Map<String, String>> words;
    private Intent previousIntent;
    private static final Constants constants = new Constants();
    private TextView txt_Title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_words);

        btn_MyWordsBack = (Button) findViewById(R.id.btn_MyWordsBack);
        words = new ArrayList<>();
        txt_Title = (TextView) findViewById(R.id.txt_MyWordsTitle);
        helper = new WordDataBaseHelper(this);
        previousIntent = getIntent();

        txt_Title.setText(previousIntent.getStringExtra(constants.LANG_ID) + " - " + txt_Title.getText().toString());

        String langName = previousIntent.getStringExtra(constants.LANG_ID);
        helper.listWords(words, langName, this);
        ListView myWordsList = (ListView) findViewById(R.id.listView);
        final MyAdapter adapter =  new MyAdapter(this,words);
        myWordsList.setAdapter(adapter);



        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        btn_MyWordsBack.setOnClickListener(clickListener);

       myWordsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               WordDialog dialog = new WordDialog();
               dialog.setParentContext(MyWordsActivity.this);
               dialog.show(getFragmentManager(), "");
               Bundle wordBundle = new Bundle();
               wordBundle.putString("word", extractWord(parent,position));
               dialog.setInternalBundle(wordBundle);
               return true;
           }
       });


    }


    private String extractWord(AdapterView<?> parent,int position){

        String text = parent.getItemAtPosition(position).toString();
        final Pattern pattern = Pattern.compile("(?s)(?<=word=).*?(?=, definition)");
        final Matcher matcher = pattern.matcher(text);
        matcher.find();
        return matcher.group(0);
    }

}
