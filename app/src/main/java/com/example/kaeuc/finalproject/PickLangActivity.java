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
    public static final String CATEGORY_PICKLANG = "personalDictionary.CATEGORY_PICKLANG";
    public static final String ACTION_PICKLANG = "personalDictionary.ACTION_PICKLANG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_language);
        this.englishButton = (Button) findViewById(R.id.btn_english);
        this.addLangButton = (Button) findViewById(R.id.btn_addLang);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()== R.id.btn_english) {
                    Intent menuIntent = new Intent(MenuActivity.ACTION_MENU);
                    menuIntent.addCategory(MenuActivity.CATEGORY_MENU);
                    startActivity(menuIntent);
                }else if(v.getId()== R.id.btn_addLang){
                    Intent addNewLangIntent = new Intent(AddNewLangActivity.ACTION_ADDLANG);
                    addNewLangIntent.addCategory(AddNewLangActivity.CATEGORY_ADDLANG);
                    startActivity(addNewLangIntent);
                }
            }
        };
        englishButton.setOnClickListener(clickListener);
        addLangButton.setOnClickListener(clickListener);
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
