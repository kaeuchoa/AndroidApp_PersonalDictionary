package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.kaeuc.finalproject.Database.LanguagesDataBaseHelper;
import com.example.kaeuc.finalproject.Database.WordDataBaseHelper;
import com.example.kaeuc.finalproject.Extras.Constants;

/**
 * Created by kaeuc on 10/22/2015.
 */
public class AddWordActivity extends Activity {

    /*ACTIVITIES IDENTIFIERS FOR THE MANIFEST*/
    public static final String CATEGORY_ADDWORD = "personalDictionary.CATEGORY_ADDWORD";
    public static final String ACTION_ADDWORD = "personalDictionary.ACTION_ADDWORD";

    /*LAYOUT ELEMENTS*/
    private Button btnBack;
    private Button btnAddWord;
    private EditText edtWordBox, edtDefinitionBox,edtExSentenceBox;

    /*CONTENT AUXILIARIES*/
    private WordDataBaseHelper wordDataBaseHelper;
    private Intent previousIntent;
    private String langName = "";

    private static final Constants CONSTANTS = new Constants();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_word);

        //INITIALIZE LAYOUT ELEMENTS
        btnBack= (Button) findViewById(R.id.btn_back);
        btnAddWord = (Button) findViewById(R.id.btn_addWord);
        edtDefinitionBox = (EditText) findViewById(R.id.edt_definitionBox);
        edtExSentenceBox= (EditText) findViewById(R.id.edt_exSentenceBox);
        edtWordBox = (EditText) findViewById(R.id.edt_wordBox);

        //INITIALIZE THE DATABASE HELPER
        wordDataBaseHelper = new WordDataBaseHelper(this);

        //RETRIEVING THE CONTENT FROM THE LAST ACTIVITY THAT HOLDS THE LANGUAGE NAME
        previousIntent = getIntent();
        langName = previousIntent.getStringExtra(CONSTANTS.LANG_ID);

        //CREATES ONCLICKLISTENER TO THE BUTTONS
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = (Button)v;
                if (v.getId() == R.id.btn_addWord){
                    addWord();
                    finish();
                }else{
                    finish();
                }
            }
        };
        btnBack.setOnClickListener(clickListener);
        btnAddWord.setOnClickListener(clickListener);
    }

    /*PRIVATE METHOD THAT USE THE DATABASEHELPERS OPERATIONS*/
    private void addWord(){
        //USES THE LANGUAGE HELPER TO FIND THE LANG_ID THAT WILL BE USED AS FOREIGN KEY IN THE DATABASE
        LanguagesDataBaseHelper languagesDataBaseHelper = new LanguagesDataBaseHelper(this);
        int langID = languagesDataBaseHelper.getId(this,langName);

        //ARRAY CONTAINING THE INFORMATION THAT WILL BE PASSED TO THE DATABSE
        String[] columns = {edtWordBox.getText().toString(),
                            edtDefinitionBox.getText().toString(),
                            edtExSentenceBox.getText().toString(),String.valueOf(langID)};

        wordDataBaseHelper.addWord(columns, AddWordActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wordDataBaseHelper.close();
    }
}
