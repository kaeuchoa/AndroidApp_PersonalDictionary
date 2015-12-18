package com.example.kaeuc.finalproject.Extras;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaeuc.finalproject.Database.WordDAO;
import com.example.kaeuc.finalproject.R;
import com.example.kaeuc.finalproject.WordClass;

import java.util.ArrayList;

/**
 * Created by kaeuc on 12/17/2015.
 */
public class UpdateWordDialog  {


    private Bundle internalBundle;
    private Context parentContext;


    private String newString;


    public UpdateWordDialog(Bundle internalBundle) {
        this.internalBundle = internalBundle;
    }

    public void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(parentContext);
        View promptView = layoutInflater.inflate(R.layout.update_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(parentContext);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edt_UpdateText);
        TextView title = (TextView) promptView.findViewById(R.id.txt_UpdateLanguageTitle);
        title.setText("Enter the new "+ internalBundle.getString("title"));
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int op = internalBundle.getInt("operation");
                                if ( op== 1) {
                                    newString = editText.getText().toString();
                                    WordDAO helper = new WordDAO(parentContext);
                                    helper.updateWord(internalBundle.getString("word"), newString);

                                    // New Code
                                    AuxiliarList W = (AuxiliarList) internalBundle.getSerializable("list");
                                    ListView myWordsList = W.wordList;
                                    ArrayList<WordClass> words2 = new ArrayList<>();
                                    WordsAdapter wordsAdapter = new WordsAdapter(parentContext, words2);
                                    myWordsList.setAdapter(wordsAdapter);
                                    helper.listWords(internalBundle.getString("lang"), parentContext, wordsAdapter);
                                }else if(op == 2){
                                    newString = editText.getText().toString();
                                    WordDAO helper = new WordDAO(parentContext);
                                    helper.updateDefinition(internalBundle.getString("definition"), newString);

                                    // New Code
                                    AuxiliarList W = (AuxiliarList) internalBundle.getSerializable("list");
                                    ListView myWordsList = W.wordList;
                                    ArrayList<WordClass> words2 = new ArrayList<>();
                                    WordsAdapter wordsAdapter = new WordsAdapter(parentContext, words2);
                                    myWordsList.setAdapter(wordsAdapter);
                                    helper.listWords(internalBundle.getString("lang"), parentContext, wordsAdapter);
                                }else{
                                    newString = editText.getText().toString();
                                    WordDAO helper = new WordDAO(parentContext);
                                    helper.updateSentence(internalBundle.getString("sentence"), newString);

                                    // New Code
                                    AuxiliarList W = (AuxiliarList) internalBundle.getSerializable("list");
                                    ListView myWordsList = W.wordList;
                                    ArrayList<WordClass> words2 = new ArrayList<>();
                                    WordsAdapter wordsAdapter = new WordsAdapter(parentContext, words2);
                                    myWordsList.setAdapter(wordsAdapter);
                                    helper.listWords(internalBundle.getString("lang"), parentContext, wordsAdapter);
                                }
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }


    public void setInternalBundle(Bundle internalBundle) {
        this.internalBundle = internalBundle;
    }


    public void setParentContext(Context parentContext) {
        this.parentContext = parentContext;
    }
}
