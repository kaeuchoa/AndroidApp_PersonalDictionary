package com.example.kaeuc.finalproject.Extras;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kaeuc.finalproject.Database.WordDataBaseHelper;

/**
 * Created by kaeuc on 12/13/2015.
 */
public class WordDialog extends DialogFragment {

    private static final String [] DIALOG_ACTIONS = {"Delete Word", "Edit Word"};
    private Context parentContext;
    private Bundle internalBundle;

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
                                            WordDataBaseHelper helper = new WordDataBaseHelper(parentContext);
                                            helper.deleteWord(internalBundle.getString("word"));
                                        }
                                    });
                               builder.show();

                        }else{ // EDIT BUTTON

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
