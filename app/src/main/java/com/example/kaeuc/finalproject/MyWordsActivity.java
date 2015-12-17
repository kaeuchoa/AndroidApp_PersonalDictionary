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
 * CLASS THAT DEALS WITH PRESENTING THE WORDS
 * Created by kaeuc on 11/5/2015.
 */
public class MyWordsActivity extends Activity {

    /*ACTIVITIES IDENTIFIERS FOR THE MANIFEST*/
    public static final String CATEGORY_MYWORDS = "personalDictionary.CATEGORY_MYWORDS";
    public static final String ACTION_MYWORDS = "personalDictionary.ACTION_MYWORDS";

    /*LAYOUT ELEMENTS*/
    private Button btn_MyWordsBack;
    private TextView txt_Title;

    /*CONTENT AUXILIARIES*/
    private WordDataBaseHelper helper;
    private ArrayList<Map<String, String>> words;
    private Intent previousIntent;

    private static final Constants constants = new Constants();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_words);

        /*INITIALIZE LAYOUT ELEMENTS*/
        btn_MyWordsBack = (Button) findViewById(R.id.btn_MyWordsBack);
        words = new ArrayList<>();
        txt_Title = (TextView) findViewById(R.id.txt_MyWordsTitle);

        /*GETS DATA FROM THE PREVIOUS ACTIVITY*/
        previousIntent = getIntent();
        txt_Title.setText(previousIntent.getStringExtra(constants.LANG_ID) + " - " + txt_Title.getText().toString());

        String langName = previousIntent.getStringExtra(constants.LANG_ID);
        /*FULFILL THE ARRAYLIST WITH MAPS CONTAINING THE DATA FROM THE DATABASE*/
        helper = new WordDataBaseHelper(this);
        helper.listWords(words, langName, this);

        /*SETS THE LISTVIEW TO SHOW THEM*/
        ListView myWordsList = (ListView) findViewById(R.id.listView);
        final MyAdapter adapter =  new MyAdapter(this,words);
        myWordsList.setAdapter(adapter);

        /*ATTACHES AN ONCLICKLISTENER TO THE BACK BUTTON*/
        btn_MyWordsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       /*SETS AN ONCLICKLISTENER TO THE LISTVIEW ITEMS */
       myWordsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               //CREATES A DIALOG TO SHOW OPTIONS
               WordDialog dialog = new WordDialog();
               dialog.setParentContext(MyWordsActivity.this);
               dialog.show(getFragmentManager(), "");

               //PASSES INFORMATION THAT WILL BE USED ON THE DIALOG
               Bundle wordBundle = new Bundle();
               wordBundle.putString("word", extractWord(parent,position));
               dialog.setInternalBundle(wordBundle);
               return true;
           }
       });


    }

    /*PRIVATE METHOD TO GET THE WORD THAT WILL BE PASSED AS PARAMETER TO THE DIALOG OPERATIONS*/
    private String extractWord(AdapterView<?> parent,int position){
        //GET THE ITEM TEXT
        String text = parent.getItemAtPosition(position).toString();
        //EXTRACTS USING REGEX AND RETURN ONLY THE WORD
        final Pattern pattern = Pattern.compile("(?s)(?<=word=).*?(?=, definition)");
        final Matcher matcher = pattern.matcher(text);
        matcher.find();
        return matcher.group(0);
    }

}
