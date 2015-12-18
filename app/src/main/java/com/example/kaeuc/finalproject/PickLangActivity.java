package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.kaeuc.finalproject.Database.LanguagesDAO;
import com.example.kaeuc.finalproject.Extras.AuxiliarLayout;
import com.example.kaeuc.finalproject.Extras.AuxiliarView;
import com.example.kaeuc.finalproject.Extras.Constants;
import com.example.kaeuc.finalproject.Extras.LangDialog;


/*CLASS TO DEAL WITH THE LANGUAGE CHOICE MENU*/

public class PickLangActivity extends Activity {
   //ACTIVITIES IDENTIFIERS FOR THE MANIFEST
    public static final String CATEGORY_PICKLANG = "personalDictionary.CATEGORY_PICKLANG";
    public static final String ACTION_PICKLANG = "personalDictionary.ACTION_PICKLANG";

    //LAYOUT ELEMENTS
    private LinearLayout layout;
    private Button addLangButton;
    private Button langButton;

    //AUXILIAR ELEMENTS TO THE ACTIVITY FUNCTIONALITIES
    private int previousButtonsSize = 0;
    private String [] buttons = new String[0];
    private LanguagesDAO helper;
    private Intent previousIntent;
    private String username;

    //CLASS THAT HOLDS CONSTANTS
    private static final Constants CONSTANTS = new Constants();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_language);

        //INITIALIZING BASICS LAYOUT ELEMENTS
        helper = new LanguagesDAO(this);
        this.addLangButton = (Button) findViewById(R.id.btn_addLang);
        layout = (LinearLayout) findViewById(R.id.linearLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        previousIntent = getIntent();
        username = previousIntent.getStringExtra("username");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //EVERY TIME ONSTART IS CALLED, IT WILL CHECK IF THE NUMBER OF BUTTONS HAS CHANGED
        //AND IT WILL CREATE THE BUTTONS DYNAMICALLY WITH THE DATA FROM THE DB

        try {
            //RETRIEVE THE LANGUAGES TO CREATE THE BUTTONS

            buttons = helper.listLanguages(this,username);
            if (buttons.length == 0){

                //IF NO DATA WAS RETRIEVED, CALL ADD LANGUAGE ACTIVITY
                Toast.makeText(PickLangActivity.this, "You don't have any language yet.", Toast.LENGTH_LONG).show();

                //START THE INTENT
                Intent addNewLangIntent = new Intent(AddNewLangActivity.ACTION_ADDLANG);
                addNewLangIntent.addCategory(AddNewLangActivity.CATEGORY_ADDLANG);
                addNewLangIntent.putExtra("username", username);
                startActivity(addNewLangIntent);

            }else{

                //IF ANY DATA WAS RETRIEVED CREATE BUTTONS AND ADD THEM TO THE LAYOUT
                buttons = helper.listLanguages(this,username);
                for (int i = previousButtonsSize; i < buttons.length ; i++) {
                    createButton(i,buttons[i]);
                }

                //CREATE THE CLICK LISTENER TO ATTACH TO ALL BUTTONS IN THIS ACITIVITY
                View.OnClickListener clickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(v.getId()== R.id.btn_addLang) {

                            //IF THE BUTTOM CLICKED WAS "ADD LANGUAGE" STARTS ADD LANGUAGE ACTIVITY
                            Intent addNewLangIntent = new Intent(AddNewLangActivity.ACTION_ADDLANG);
                            addNewLangIntent.addCategory(AddNewLangActivity.CATEGORY_ADDLANG);
                            addNewLangIntent.putExtra("username", username);

                            startActivity(addNewLangIntent);

                        }else{
                            //IF THE BUTTON CLICKED WAS ANY OF THE LANGUAGES STARTS THE MENU ACTIVITY
                            Intent menuIntent = new Intent(MenuActivity.ACTION_MENU);
                            menuIntent.addCategory(MenuActivity.CATEGORY_MENU);

                            //ADDING AN EXTRA NEEDED TO RETRIEVE THE RIGHT DATA FROM THE DB
                            String languageName = ((Button) v).getText().toString();
                            menuIntent.putExtra(CONSTANTS.LANG_ID, languageName);

                            startActivity(menuIntent);
                        }
                    }
                };

                //ATTACHING THE CLICK LISTENER TO EVERY BUTTON
                addLangButton.setOnClickListener(clickListener);
                    for (int i = previousButtonsSize; i < buttons.length; i++) {
                    langButton= (Button)layout.getChildAt(i);
                    langButton.setOnClickListener(clickListener);
                }

                //CREATE A LONG CLICK LISTENER TO LANGUAGE BUTTONS
                View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        //SETTING THE DIALOG WINDOWS TO SHOW UP ON LONG CLICKS
                        LangDialog dialog = new LangDialog();
                        dialog.show(getFragmentManager(),"");
                        dialog.setParentContext(PickLangActivity.this);

                        //CREATING A BUNDLE TO MAKE USE ON DELETE AND EDIT METHODS
                        String langName = ((Button)v).getText().toString();
                        Bundle wordBundle = new Bundle();


                        AuxiliarLayout auxiliarLayout = new AuxiliarLayout();
                        auxiliarLayout.layout = layout;

                        AuxiliarView auxiliarView = new AuxiliarView();
                        auxiliarView.view = v;

                        wordBundle.putSerializable("view",auxiliarView);
                        wordBundle.putSerializable("layout", auxiliarLayout);
                        wordBundle.putString("language", langName);
                        dialog.setInternalBundle(wordBundle);

                        return true;
                    }
                };

                //ATTACHING LONGCLICK LISTENER TO EVERY LANGUAGE BUTTON
                for (int i = previousButtonsSize; i < buttons.length; i++) {
                    langButton= (Button)layout.getChildAt(i);
                    langButton.setOnLongClickListener(onLongClickListener);
                }

            }



        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //EVERY ONRESUME CALL WILL REFRESH THE AMOUNT OF BUTTONS ON THE SCREEN
        buttons = helper.listLanguages(this,username);
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

    //METHOD TO CREATE BUTTONS DYNAMICALLY
    private void createButton(int id,String name){
        Button newBtn = new Button(this);
        newBtn.setId(id);
        newBtn.setText(name);
        layout.addView(newBtn);
    }



}
