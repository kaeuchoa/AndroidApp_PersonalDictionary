package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaeuc.finalproject.Database.LoginDataBaseHelper;

public class LoginActivity extends Activity {


    public static final String CATEGORY_ADDWORD = "personalDictionary.CATEGORY_LOGIN";
    public static final String ACTION_ADDWORD = "personalDictionary.ACTION_LOGIN";

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnCreateProfile;
    private LoginDataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        helper = new LoginDataBaseHelper(this);
        edtUsername = (EditText) findViewById(R.id.edt_Username);
        edtPassword = (EditText) findViewById(R.id.edt_Password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnCreateProfile = (Button) findViewById(R.id.btn_createProfile);
        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId() == btnLogin.getId()){
                    String username = edtUsername.getText().toString();
                    String password = edtPassword.getText().toString();
                    final int checkLogin = helper.checkLogin(username, password);
                    if (checkLogin == 1){
                        Intent intent = new Intent(PickLangActivity.ACTION_PICKLANG);
                        intent.addCategory(PickLangActivity.CATEGORY_PICKLANG);
                        intent.putExtra("username", username);
                        edtPassword.setText("");
                        edtUsername.setText("");
                        startActivity(intent);
                    }else if(checkLogin == -1){
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "No profiles were found. Create one before continue.", Toast.LENGTH_LONG).show();
                    }
                    
                }else if(v.getId() == btnCreateProfile.getId()){
                    Intent intent = new Intent(CreateProfileActivity.ACTION_CREATEPROFILE);
                    intent.addCategory(CreateProfileActivity.CATEGORY_CREATEPROFILE);
                    edtPassword.setText("");
                    edtUsername.setText("");
                    startActivity(intent);
                }
            }
        };
        btnCreateProfile.setOnClickListener(clickListener);
        btnLogin.setOnClickListener(clickListener);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
