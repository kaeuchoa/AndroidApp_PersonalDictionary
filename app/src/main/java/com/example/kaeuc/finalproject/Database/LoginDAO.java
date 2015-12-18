package com.example.kaeuc.finalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * CLASS TO DEAL WITH THE LANGUAGE DATABASE OPERATIONS
 * Created by kaeuc on 11/29/2015.
 */
public class LoginDAO extends SQLiteOpenHelper {

    /*CONSTANTS WITH COLUMNS NAMES AND DB NAME*/
    private static final String DB_NAME = "login";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static int VERSION = 1;

    /*CONSTRUCTOR*/
    public LoginDAO(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    /*INITIALIZE THE DATABASE*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , " +
                USERNAME +" TEXT NOT NULL ,"+ PASSWORD+" TEXT NOT NULL);");
    }

    /*UPDATES THE DATABASE*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*PUBLIC METHOD FOR ADDING A LANGUAGE*/

    public void addUser(String username, String password, Context context){
        if(username.isEmpty()){
            Toast.makeText(context, "Blank Field, try again.", Toast.LENGTH_LONG).show();
        }else{
            //RETRIEVING THE DATABASE TO INSERT THE INFO
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(USERNAME, username);
            //add the hash
            values.put(PASSWORD,password);
            long insert = db.insert(DB_NAME, null, values);

            /*DEALS WITH THE RESULT OF INSERT*/
            if(insert != -1){
                Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show();
            }
        }

    }
    
    public int checkLogin(String username, String password){
        String checkUserName, checkPassword;
        ArrayList<Map<String, String>> users = listUsers();
        if (users.size() == 0){
            return 0;
        }
        for (Map<String, String> map : users) {
            checkUserName = map.get(USERNAME);
            checkPassword = map.get(PASSWORD);
            if (checkUserName.equals(username) && checkPassword.equals(password)){
                return 1;
            }
        }
        

        return -1;
    }

    /*PUBLIC METHOD FOR RETRIEVING THE USERS FROM THE DATABASE*/
    private ArrayList<Map<String, String>> listUsers(){
        //GETS THE DATABASE TO READ FROM
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Map<String, String>> users = new ArrayList<>();
        
        //CREATES THE SELECT QUERY
        Cursor cursor = db.query(DB_NAME,new String[]{USERNAME,PASSWORD},null,null,null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount()!= 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                //CREATES A MAP TO KEEP THE WORDS THAT WILL BE RETRIEVED LATER
                Map<String, String> item = new HashMap<>();

                String username = cursor.getString(0);
                String password = cursor.getString(1);


                item.put(USERNAME, username);
                item.put(PASSWORD, password);


                users.add(item);
                cursor.moveToNext();

            }
        }
        cursor.close();
        return users;
    }

    /*PUBLIC METHOD USED TO IDENTIFY THE FOREIGN KEY BASED ON THE LANGUAGE NAME*/
    public int getId(String username, Context context){
        SQLiteDatabase db = getReadableDatabase();
        int userID = 0;
        final String[] selectColumn = {"_id"};
        String whereClause = USERNAME + " =?";
        String [] whereArgs = {username};
        Cursor cursor = db.query(DB_NAME,selectColumn,whereClause,whereArgs,null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            userID = cursor.getInt(0);
            cursor.close();

        }
        return userID;
    }


    /*PUBLIC METHOD TO DELETE LANGUAGES AND ALL ITS REGISTERS IN THE WORD TABLE */
    /*public void deleteLanguage(String langName, Context context){
        SQLiteDatabase db = getReadableDatabase();
        WordDAO helper = new WordDAO(context);
        helper.deleteByID(""+getId(context,langName));
        db.delete(DB_NAME, USERNAME + "=?", new String[]{langName});
    }*/


}
