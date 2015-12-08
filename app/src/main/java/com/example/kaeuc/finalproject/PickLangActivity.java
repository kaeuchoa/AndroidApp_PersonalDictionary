package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kaeuc.finalproject.Database.LanguagesDataBaseHelper;

public class PickLangActivity extends Activity {
    private Button addLangButton;
    private Button langButton;
    public static final String CATEGORY_PICKLANG = "personalDictionary.CATEGORY_PICKLANG";
    public static final String ACTION_PICKLANG = "personalDictionary.ACTION_PICKLANG";
    LinearLayout layout;
    private String [] buttons;
    private LanguagesDataBaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_language);

        this.addLangButton = (Button) findViewById(R.id.btn_addLang);
        layout = (LinearLayout) findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        helper = new LanguagesDataBaseHelper(this);
        helper.listLanguages(buttons,PickLangActivity.this);

        if (buttons == null){
            Toast.makeText(PickLangActivity.this, "You don't have any language yet", Toast.LENGTH_LONG).show();
            Intent addNewLangIntent = new Intent(AddNewLangActivity.ACTION_ADDLANG);
            addNewLangIntent.addCategory(AddNewLangActivity.CATEGORY_ADDLANG);
            startActivity(addNewLangIntent);
        }else{
            helper.listLanguages(buttons,PickLangActivity.this);
            Toast.makeText(PickLangActivity.this, "length "+buttons.length+"text "+buttons[0], Toast.LENGTH_LONG).show();
            for (int i = 0; i < buttons.length ; i++) {
                Button newBtn = new Button(this);
                newBtn.setId(i + 1);
                newBtn.setText(buttons[i].toUpperCase());
                layout.addView(newBtn);
            }
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId()== R.id.btn_addLang) {
                        Intent addNewLangIntent = new Intent(AddNewLangActivity.ACTION_ADDLANG);
                        addNewLangIntent.addCategory(AddNewLangActivity.CATEGORY_ADDLANG);
                        startActivity(addNewLangIntent);
                    }else{
                        Intent menuIntent = new Intent(MenuActivity.ACTION_MENU);
                        menuIntent.addCategory(MenuActivity.CATEGORY_MENU);
                        startActivity(menuIntent);
                    }
                }
            };
            addLangButton.setOnClickListener(clickListener);
            for (int i = 0; i < buttons.length; i++) {
                langButton= (Button)layout.getChildAt(i);
                langButton.setOnClickListener(clickListener);
            }
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (buttons == null){
            Toast.makeText(PickLangActivity.this, "You don't have any language yet", Toast.LENGTH_LONG).show();
            Intent addNewLangIntent = new Intent(AddNewLangActivity.ACTION_ADDLANG);
            addNewLangIntent.addCategory(AddNewLangActivity.CATEGORY_ADDLANG);
            startActivity(addNewLangIntent);
        }else{
            helper.listLanguages(buttons,PickLangActivity.this);
            Toast.makeText(PickLangActivity.this, "length "+buttons.length+"text "+buttons[0], Toast.LENGTH_LONG).show();
            for (int i = 0; i < buttons.length ; i++) {
                Button newBtn = new Button(this);
                newBtn.setId(i + 1);
                newBtn.setText(buttons[i].toUpperCase());
                layout.addView(newBtn);
            }
            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId()== R.id.btn_addLang) {
                        Intent addNewLangIntent = new Intent(AddNewLangActivity.ACTION_ADDLANG);
                        addNewLangIntent.addCategory(AddNewLangActivity.CATEGORY_ADDLANG);
                        startActivity(addNewLangIntent);
                    }else{
                        Intent menuIntent = new Intent(MenuActivity.ACTION_MENU);
                        menuIntent.addCategory(MenuActivity.CATEGORY_MENU);
                        startActivity(menuIntent);
                    }
                }
            };
            addLangButton.setOnClickListener(clickListener);
            for (int i = 0; i < buttons.length; i++) {
                langButton= (Button)layout.getChildAt(i);
                langButton.setOnClickListener(clickListener);
            }
        }


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
