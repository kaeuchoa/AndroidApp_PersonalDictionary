package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kaeuc.finalproject.Extras.Constants;

/**
 * Created by kaeuc on 11/5/2015.
 */
public class MenuActivity extends Activity{

    public static final String CATEGORY_MENU = "personalDictionary.CATEGORY_MENU";
    public static final String ACTION_MENU = "personalDictionary.ACTION_MENU";
    private Button btnAddNewWord;
    private Button btnGoToMyWords;
    private Button btnPickLang;
    private String langName = "";
    //Pega a intent passada pra poder recuperar o extra que é a LANGID que armazena o nome da lingua
    private final Intent previousIntent = getIntent();
    private static final Constants CONSTANTS = new Constants();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        //INICIALIZAÇÃO DAS VIEWS
        btnAddNewWord = (Button) findViewById(R.id.btn_menuNewWord);
        btnGoToMyWords = (Button) findViewById(R.id.btn_menuMyWords);
        btnPickLang = (Button) findViewById(R.id.btn_menuPickLang);
        //RECUPERANDO E NORMALIZANDO O NOME DA LINGUA
        langName = previousIntent.getStringExtra(CONSTANTS.LANG_ID).toUpperCase();


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == btnAddNewWord.getId()){
                    Intent addNewWordIntent = new Intent(AddWordActivity.ACTION_ADDWORD);
                    addNewWordIntent.addCategory(AddWordActivity.CATEGORY_ADDWORD);

                    //Passando pra baixo na hierarquia a LANGID que armazena o nome da lingua pra usar no banco
                    addNewWordIntent.putExtra(CONSTANTS.LANG_ID,langName);

                    startActivity(addNewWordIntent);
                }else if(v.getId() == btnGoToMyWords.getId()){
                    Intent myWordsIntent = new Intent(MyWords.ACTION_MYWORDS);
                    myWordsIntent.addCategory(MyWords.CATEGORY_MYWORDS);

                    //Passando pra baixo na hierarquia a LANGID que armazena o nome da lingua pra usar no banco
                    myWordsIntent.putExtra(CONSTANTS.LANG_ID, langName);

                    startActivity(myWordsIntent);
                }else{ // pick another language
                    finish();
                }
            }
        };
        btnAddNewWord.setOnClickListener(clickListener);
        btnGoToMyWords.setOnClickListener(clickListener);
        btnPickLang.setOnClickListener(clickListener);
    }
}
