package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by kaeuc on 10/22/2015.
 */
public class AddWordActivity extends Activity {

    public static final String CATEGORY_ADDWORD = "personalDictionary.CATEGORY_ADDWORD";
    public static final String ACTION_ADDWORD = "personalDictionary.ACTION_ADDWORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_word);
    }
}
