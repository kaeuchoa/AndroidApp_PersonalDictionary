package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by kaeuc on 11/5/2015.
 */
public class MenuActivity extends Activity{

    public static final String CATEGORY_MENU = "personalDictionary.CATEGORY_MENU";
    public static final String ACTION_MENU = "personalDictionary.ACTION_MENU";
    private Button btnAddNewWord;
    private Button btnGoToMyWords;
    private Button btnPickLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        btnAddNewWord = (Button) findViewById(R.id.btn_menuNewWord);
        btnGoToMyWords = (Button) findViewById(R.id.btn_menuMyWords);
        btnPickLang = (Button) findViewById(R.id.btn_menuPickLang);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == btnAddNewWord.getId()){
                    Intent addNewWordIntent = new Intent(AddWordActivity.ACTION_ADDWORD);
                    addNewWordIntent.addCategory(AddWordActivity.CATEGORY_ADDWORD);
                    startActivity(addNewWordIntent);
                }else if(v.getId() == btnGoToMyWords.getId()){
                    Intent myWordsIntent = new Intent(MyWords.ACTION_MYWORDS);
                    myWordsIntent.addCategory(MyWords.CATEGORY_MYWORDS);
                    startActivity(myWordsIntent);
                }else{ // pick another language
                    finish();
                }
            }
        };
        btnAddNewWord.setOnClickListener(clickListener);
        btnGoToMyWords.setOnClickListener(clickListener);
        btnPickLang.setOnClickListener(clickListener);
    }
}
