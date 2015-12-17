package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaeuc.finalproject.Database.LanguagesDataBaseHelper;
import com.example.kaeuc.finalproject.Database.LoginDataBaseHelper;

/**
 * Created by kaeuc on 10/22/2015.
 */
public class AddNewLangActivity extends Activity {

    /*ACTIVITIES IDENTIFIERS FOR THE MANIFEST*/
    public static final String ACTION_ADDLANG = "personalDictionary.ACTION_ADDLANG";
    public static final String CATEGORY_ADDLANG = "personalDictionary.CATEGORY_ADDLANG";

    /*LAYOUT ELEMENTS*/
    private Button btnAddLang;
    private EditText edt_Language;

    /*DATABASE HELPER*/
    private LanguagesDataBaseHelper helper;
    private Intent previousIntent;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_language);
        previousIntent = getIntent();
        username = previousIntent.getStringExtra("username");
        //INITIALIZE LAYOUT ELEMENTS
        edt_Language = (EditText) findViewById(R.id.edt_newLanguage);
        btnAddLang = (Button) findViewById(R.id.btn_addLanguage);
        helper = new LanguagesDataBaseHelper(this);
        btnAddLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLanguage();
                finish();
            }
        });


    }


    private void addLanguage(){

        Toast.makeText(AddNewLangActivity.this, username, Toast.LENGTH_SHORT).show();
        helper.addLanguage(edt_Language.getText().toString(),
                                            username,AddNewLangActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }
}
