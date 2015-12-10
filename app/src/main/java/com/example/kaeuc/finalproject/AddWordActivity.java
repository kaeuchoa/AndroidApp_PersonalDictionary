package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaeuc.finalproject.Database.LanguagesDataBaseHelper;
import com.example.kaeuc.finalproject.Database.WordDataBaseHelper;
import com.example.kaeuc.finalproject.Extras.Constants;

/**
 * Created by kaeuc on 10/22/2015.
 */
public class AddWordActivity extends Activity {

    public static final String CATEGORY_ADDWORD = "personalDictionary.CATEGORY_ADDWORD";
    public static final String ACTION_ADDWORD = "personalDictionary.ACTION_ADDWORD";
    public static int _wordLanguage;

    private Button btnBack;
    private Button btnAddWord;
    private EditText edtWordBox, edtDefinitionBox,edtExSentenceBox;
    private WordDataBaseHelper helper;
    private Intent previousIntent;
    private static final Constants CONSTANTS = new Constants();
    private String langName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_word);

        //INICIALIZANDO AS VIEWS E O BD HELPER
        btnBack= (Button) findViewById(R.id.btn_back);
        btnAddWord = (Button) findViewById(R.id.btn_addWord);
        edtDefinitionBox = (EditText) findViewById(R.id.edt_definitionBox);
        edtExSentenceBox= (EditText) findViewById(R.id.edt_exSentenceBox);
        edtWordBox = (EditText) findViewById(R.id.edt_wordBox);
        helper = new WordDataBaseHelper(this);

        //RECUPERANDO OS DADOS DA INTENT PASSADA QUE ARMAZENA O NOME DA LINGUA
        previousIntent = getIntent();
        langName = previousIntent.getStringExtra(CONSTANTS.LANG_ID);
        Toast.makeText(AddWordActivity.this, langName, Toast.LENGTH_SHORT).show();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v = (Button)v;
                if (v.getId() == R.id.btn_addWord){
                    addWord();

                }else{
                    finish();
                }
            }
        };
        btnBack.setOnClickListener(clickListener);
        btnAddWord.setOnClickListener(clickListener);
    }

    private void addWord(){
        LanguagesDataBaseHelper langHelper = new LanguagesDataBaseHelper(this);
        int langID = langHelper.getId(this,langName);

        String[] columns = {edtWordBox.getText().toString(),
                            edtDefinitionBox.getText().toString(),
                            edtExSentenceBox.getText().toString(),String.valueOf(langID)};

        helper.addWord(columns,AddWordActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }
}
