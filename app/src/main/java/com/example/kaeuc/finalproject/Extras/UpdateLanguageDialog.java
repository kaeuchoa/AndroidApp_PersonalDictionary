package com.example.kaeuc.finalproject.Extras;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.kaeuc.finalproject.Database.LanguagesDAO;
import com.example.kaeuc.finalproject.R;

/**
 * Created by kaeuc on 12/17/2015.
 */
public class UpdateLanguageDialog {

    private Bundle internalBundle;
    private String newName;


    public UpdateLanguageDialog(Bundle internalBundle) {
            this.internalBundle = internalBundle;
    }

    public void showInputDialog(final Context context) {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.update_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edt_UpdateText);
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
                                // PERFORMS DELETE ACTIONS
                                newName = editText.getText().toString();
                                LanguagesDAO helper = new LanguagesDAO(context);
                                helper.updateLanguage(internalBundle.getString("language"), newName, context);

                                // New Code
                                AuxiliarLayout auxiliarLayout = (AuxiliarLayout) internalBundle.getSerializable("layout");
                                LinearLayout layout = auxiliarLayout.layout;

                                AuxiliarView auxiliarView = (AuxiliarView) internalBundle.getSerializable("view");
                                View view = auxiliarView.view;
                                Button button = (Button) view;
                                button.setText(newName);

                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }


    public void setInternalBundle(Bundle internalBundle) {
        this.internalBundle = internalBundle;
    }
}
