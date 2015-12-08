package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kaeuc.finalproject.Database.LanguagesDataBaseHelper;

/**
 * Created by kaeuc on 10/22/2015.
 */
public class AddNewLangActivity extends Activity {

    public static final String ACTION_ADDLANG = "personalDictionary.ACTION_ADDLANG";
    public static final String CATEGORY_ADDLANG = "personalDictionary.CATEGORY_ADDLANG";
    private Button btnAddLang;
    private EditText edt_Language;
    private LanguagesDataBaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_language);
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
        helper.addLanguage(edt_Language.getText().toString(),AddNewLangActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }
}
