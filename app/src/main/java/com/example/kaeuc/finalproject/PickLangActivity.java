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
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaeuc.finalproject.Database.LanguagesDataBaseHelper;

public class PickLangActivity extends Activity {
    private Button addLangButton;
    private Button langButton;
    public static final String CATEGORY_PICKLANG = "personalDictionary.CATEGORY_PICKLANG";
    public static final String ACTION_PICKLANG = "personalDictionary.ACTION_PICKLANG";
    private LinearLayout layout;
    private int previousButtonsSize = 0;
    private String [] buttons = new String[0];
    private LanguagesDataBaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_language);
        helper = new LanguagesDataBaseHelper(this);
        this.addLangButton = (Button) findViewById(R.id.btn_addLang);
        layout = (LinearLayout) findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            buttons = helper.listLanguages(this);
            if (buttons.length == 0){
                Toast.makeText(PickLangActivity.this, "You don't have any language yet", Toast.LENGTH_LONG).show();
                Intent addNewLangIntent = new Intent(AddNewLangActivity.ACTION_ADDLANG);
                addNewLangIntent.addCategory(AddNewLangActivity.CATEGORY_ADDLANG);
                startActivity(addNewLangIntent);
            }else{
                buttons = helper.listLanguages(this);
                for (int i = previousButtonsSize; i < buttons.length ; i++) {
                    createButton(i,buttons[i].toUpperCase());

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
                for (int i = previousButtonsSize; i < buttons.length; i++) {
                    langButton= (Button)layout.getChildAt(i);
                    langButton.setOnClickListener(clickListener);
                }
            }



        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();



    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onResume() {
        super.onResume();
        buttons = helper.listLanguages(this);
        previousButtonsSize = buttons.length;
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

    private void createButton(int id,String name){
        Button newBtn = new Button(this);
        newBtn.setId(id);
        newBtn.setText(name);
        layout.addView(newBtn);

    }
}
