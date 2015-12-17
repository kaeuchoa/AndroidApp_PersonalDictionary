package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.kaeuc.finalproject.Extras.Constants;

/**
 * Created by kaeuc on 11/5/2015.
 */
public class MenuActivity extends Activity{

    /*ACTIVITIES IDENTIFIERS FOR THE MANIFEST*/
    public static final String CATEGORY_MENU = "personalDictionary.CATEGORY_MENU";
    public static final String ACTION_MENU = "personalDictionary.ACTION_MENU";

    /*LAYOUT ELEMENTS*/
    private Button btnAddNewWord;
    private Button btnGoToMyWords;
    private Button btnPickLang;

    /*CONTENT AUXILIARIES*/
    private String langName = "";
    private Intent previousIntent;
    private static final Constants CONSTANTS = new Constants();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        //INITIALIZE LAYOUT ELEMENTS
        btnAddNewWord = (Button) findViewById(R.id.btn_menuNewWord);
        btnGoToMyWords = (Button) findViewById(R.id.btn_menuMyWords);
        btnPickLang = (Button) findViewById(R.id.btn_menuPickLang);


        /*GETS DATA FROM THE PREVIOUS ACTIVITY*/
        previousIntent = getIntent();
        langName = previousIntent.getStringExtra(CONSTANTS.LANG_ID);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == btnAddNewWord.getId()){

                    Intent addNewWordIntent = new Intent(AddWordActivity.ACTION_ADDWORD);
                    addNewWordIntent.addCategory(AddWordActivity.CATEGORY_ADDWORD);
                    //ADDS AN EXTRA TO BE USED IN FURTHER ACTIVITIES THAT DEAL WITH THE DATABASE
                    addNewWordIntent.putExtra(CONSTANTS.LANG_ID,langName);
                    startActivity(addNewWordIntent);

                }else if(v.getId() == btnGoToMyWords.getId()){

                    Intent myWordsIntent = new Intent(MyWordsActivity.ACTION_MYWORDS);
                    myWordsIntent.addCategory(MyWordsActivity.CATEGORY_MYWORDS);
                    //ADDS AN EXTRA TO BE USED IN FURTHER ACTIVITIES THAT DEAL WITH THE DATABASE
                    myWordsIntent.putExtra(CONSTANTS.LANG_ID, langName);
                    startActivity(myWordsIntent);

                }else{
                    finish();
                }
            }
        };
        /*ATTACHES THE CLICKLISTENER TO ALL BUTTONS*/
        btnAddNewWord.setOnClickListener(clickListener);
        btnGoToMyWords.setOnClickListener(clickListener);
        btnPickLang.setOnClickListener(clickListener);
    }
}
