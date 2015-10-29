package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PickLangActivity extends Activity {
    private Button englishButton;
    private Button addLangButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_language);
        this.englishButton = (Button) findViewById(R.id.btn_english);
        this.addLangButton = (Button) findViewById(R.id.btn_addLang);
        View.OnClickListener actionListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()== R.id.btn_english) {
                    Intent intent = new Intent(AddWordActivity.ACTION_ADDWORD);
                    intent.addCategory(AddWordActivity.CATEGORY_ADDWORD);
                    startActivity(intent);
                }else if(v.getId()== R.id.btn_addLang){
                    Intent intent = new Intent(AddNewLangActivity.ACTION_ADDLANG);
                    intent.addCategory(AddNewLangActivity.CATEGORY_ADDLANG);
                    startActivity(intent);
                }
            }
        };
        englishButton.setOnClickListener(actionListener);
        addLangButton.setOnClickListener(actionListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
