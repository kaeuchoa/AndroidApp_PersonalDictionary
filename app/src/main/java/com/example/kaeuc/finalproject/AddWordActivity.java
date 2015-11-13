package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by kaeuc on 10/22/2015.
 */
public class AddWordActivity extends Activity {

    public static final String CATEGORY_ADDWORD = "personalDictionary.CATEGORY_ADDWORD";
    public static final String ACTION_ADDWORD = "personalDictionary.ACTION_ADDWORD";
    private Button btnBack;
    private Button btnAddWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_word);
        btnBack= (Button) findViewById(R.id.btn_back);
        btnAddWord = (Button) findViewById(R.id.btn_addWord);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = (Button)v;
                if (v.getId() == R.id.btn_addWord){
                    Intent addWordIntent = new Intent(AddWordActivity.ACTION_ADDWORD);
                    addWordIntent.addCategory(AddWordActivity.CATEGORY_ADDWORD);
                    startActivity(addWordIntent);
                }else{
                    finish();
                }
            }
        };
        btnBack.setOnClickListener(clickListener);
        btnAddWord.setOnClickListener(clickListener);
    }
}
