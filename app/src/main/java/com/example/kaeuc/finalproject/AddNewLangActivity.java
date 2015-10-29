package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by kaeuc on 10/22/2015.
 */
public class AddNewLangActivity extends Activity {

    public static final String ACTION_ADDLANG = "personalDictionary.ACTION_ADDLANG";
    public static final String CATEGORY_ADDLANG = "personalDictionary.CATEGORY_ADDLANG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_language);
    }
}
