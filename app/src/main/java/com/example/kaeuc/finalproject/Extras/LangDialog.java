package com.example.kaeuc.finalproject.Extras;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.kaeuc.finalproject.Database.LanguagesDAO;

/**
 * PERSONALIZED DIALOG WINDOW TO USE WITHIN PICKLANGACTIVITY
 * Created by kaeuc on 12/13/2015.
 */
public class LangDialog extends DialogFragment {
    /*DATA NEEDED FOR INTERNAL USE*/
    private Context parentContext;
    private Bundle internalBundle;

    /*DIALOG WINDOW OPTIONS*/
    private static final String [] DIALOG_ACTIONS = {"Delete Language", "Edit Language"};

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        /*FACTORY CLASS FOR DIALOGS*/
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
                                            LanguagesDAO helper = new LanguagesDAO(parentContext);
                                            helper.deleteLanguage(internalBundle.getString("language"),parentContext);

                                            // New Code
                                            AuxiliarLayout auxiliarLayout = (AuxiliarLayout) internalBundle.getSerializable("layout");
                                            LinearLayout layout = auxiliarLayout.layout;

                                            AuxiliarView auxiliarView = (AuxiliarView) internalBundle.getSerializable("view");
                                            View view = auxiliarView.view;
                                            layout.removeView(view);

                                        }
                                    });
                               builder.show();

                        }else{
                            UpdateLanguageDialog input = new UpdateLanguageDialog(internalBundle);
                            input.showInputDialog(parentContext);

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
