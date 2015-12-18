package com.example.kaeuc.finalproject.Extras;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;


import com.example.kaeuc.finalproject.Database.WordDAO;
import com.example.kaeuc.finalproject.WordClass;

import java.util.ArrayList;

/**
 * PERSONALIZED DIALOG WINDOW TO USE WITHIN MYWORDSACTIVITY
 * Created by kaeuc on 12/13/2015.
 */
public class WordDialog extends DialogFragment {
    /*DATA NEEDED FOR INTERNAL USE*/
    private Context parentContext;
    private Bundle internalBundle;


    /*DIALOG WINDOW OPTIONS*/
    private static final String [] DIALOG_ACTIONS = {"Delete Word", "Edit Word", "Edit Definition", "Edit Sentence"};
    /*FACTORY CLASS FOR DIALOGS*/
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Options")
                .setItems(DIALOG_ACTIONS, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {  //DELETE BUTTON
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("Are you sure?")
                                    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //DO NOTHING OR CREATE A TOAST
                                        }
                                    })
                                    .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // PERFORMS DELETE ACTIONS
                                            WordDAO helper = new WordDAO(parentContext);
                                            helper.deleteWord(internalBundle.getString("word"));


                                            // New Code
                                            AuxiliarList W = (AuxiliarList)internalBundle.getSerializable("list");
                                            ListView myWordsList = W.wordList;
                                            ArrayList<WordClass> words2 = new ArrayList<>();
                                            WordsAdapter wordsAdapter = new WordsAdapter(parentContext,words2);
                                            myWordsList.setAdapter(wordsAdapter);
                                            helper.listWords(internalBundle.getString("lang"), parentContext, wordsAdapter);

                                        }
                                    });
                               builder.show();

                        }else if(which == 1){ // EDIT BUTTON
                            internalBundle.putString("title","word");
                            internalBundle.putInt("operation",1);
                            UpdateWordDialog input = new UpdateWordDialog(internalBundle);
                            input.setParentContext(parentContext);
                            input.showInputDialog();


                        }else if(which == 2){
                            internalBundle.putString("title","definition");
                            internalBundle.putInt("operation",2);
                            UpdateWordDialog input = new UpdateWordDialog(internalBundle);
                            input.setParentContext(parentContext);
                            input.showInputDialog();;
                        }else{
                            internalBundle.putString("title","sentence");
                            internalBundle.putInt("operation",3);
                            UpdateWordDialog input = new UpdateWordDialog(internalBundle);
                            input.setParentContext(parentContext);
                            input.showInputDialog();
                        }
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();

    }


    public void setParentContext(Context parentContext) {
        this.parentContext = parentContext;
    }

    public void setInternalBundle(Bundle internalBundle) {
        this.internalBundle = internalBundle;
    }
}
