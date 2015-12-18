package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kaeuc.finalproject.Database.LoginDAO;

/**
 * Created by kaeuc on 12/16/2015.
 */
public class CreateProfileActivity extends Activity {

    /*ACTIVITIES IDENTIFIERS FOR THE MANIFEST*/
    public static final String CATEGORY_CREATEPROFILE = "personalDictionary.CATEGORY_CREATEPROFILE";
    public static final String ACTION_CREATEPROFILE = "personalDictionary.ACTION_CREATEPROFILE";

    private EditText edtCreateUsername;
    private EditText edtCreatePassword;
    private Button btnCreate;

    private LoginDAO helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        helper = new LoginDAO(this);

        edtCreateUsername = (EditText) findViewById(R.id.edt_createUsername);
        edtCreatePassword = (EditText) findViewById(R.id.edt_createPassword);
        btnCreate = (Button) findViewById(R.id.btn_confirmProfile);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.addUser(edtCreateUsername.getText().toString(),
                        edtCreatePassword.getText().toString(),
                        CreateProfileActivity.this);
                finish();
            }
        });
    }
}
