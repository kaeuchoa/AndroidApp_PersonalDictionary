package com.example.kaeuc.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends Activity {


    private EditText edtEmail;
    private EditText edtPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        edtEmail = (EditText) findViewById(R.id.textArea_email);
        edtPassword = (EditText) findViewById(R.id.textArea_password);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.btn_login);

        View.OnClickListener clickListenerlogin = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                  edtEmail.getText().toString();
//                edtPassword.getText().toString();

                Intent pickLangIntent = new Intent(login.this,PickLangActivity.class);
                startActivity(pickLangIntent);


            }
        };

        btnLogin.setOnClickListener(clickListenerlogin);


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
