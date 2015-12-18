package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaeuc.finalproject.Database.WordDAO;
import com.example.kaeuc.finalproject.Extras.AuxiliarList;
import com.example.kaeuc.finalproject.Extras.Constants;
import com.example.kaeuc.finalproject.Extras.WordDialog;
import com.example.kaeuc.finalproject.Extras.WordsAdapter;

import java.util.ArrayList;
import java.util.Map;

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
    private ListView myWordsList;

    /*CONTENT AUXILIARIES*/
    private WordDAO helper;
    private ArrayList<Map<String, String>> words;
    private Intent previousIntent;
    private MyAdapter adapter;


    String langName;

    private WordsAdapter wordsAdapter;
    private ArrayList<WordClass> words2;


    private static final Constants constants = new Constants();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_words);





        /*INITIALIZE LAYOUT ELEMENTS*/
        btn_MyWordsBack = (Button) findViewById(R.id.btn_MyWordsBack);
        //words = new ArrayList<>();
        txt_Title = (TextView) findViewById(R.id.txt_MyWordsTitle);

        /*GETS DATA FROM THE PREVIOUS ACTIVITY*/
        previousIntent = getIntent();
        txt_Title.setText(previousIntent.getStringExtra(constants.LANG_ID) + " - " + txt_Title.getText().toString());

        langName = previousIntent.getStringExtra(constants.LANG_ID);
        /*FULFILL THE ARRAYLIST WITH MAPS CONTAINING THE DATA FROM THE DATABASE*/
        helper = new WordDAO(this);
        //helper.listWords(words, langName, this);

        /*SETS THE LISTVIEW TO SHOW THEM*/
        words2 = new ArrayList<>();
        wordsAdapter = new WordsAdapter(this,words2);
        myWordsList = (ListView) findViewById(R.id.listView);
       // adapter =  new MyAdapter(this,words);
        myWordsList.setAdapter(wordsAdapter);
        helper.listWords(langName, this, wordsAdapter);


        /*ATTACHES AN ONCLICKLISTENER TO THE BACK BUTTON*/
        btn_MyWordsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
               final String[] elements = extractElements(parent, position);
               wordBundle.putString("word",elements[0] );
               wordBundle.putString("definition",elements[1] );
               wordBundle.putString("sentence",elements[2] );

               wordBundle.putString("lang", langName);
               AuxiliarList W = new AuxiliarList();
               W.wordList = myWordsList;
               wordBundle.putSerializable("list", W);
               dialog.setInternalBundle(wordBundle);
               return true;
           }
       });


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    /*PRIVATE METHOD TO GET THE WORD THAT WILL BE PASSED AS PARAMETER TO THE DIALOG OPERATIONS*/
    private String[] extractElements(AdapterView<?> parent, int position){
        //GET THE ITEM TEXT
        WordClass item = (WordClass) parent.getItemAtPosition(position);
        String[] elements = {item.getWord(),item.getDefinition(),item.getSentence()};
        return elements;
    }

}
