package com.example.kaeuc.finalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import com.example.kaeuc.finalproject.Extras.MD5Hashing;

import java.security.SecureRandom;
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
    private static final String USERNAME_COLUMN = "username";
    private static final String PASSWORD_COLUMN = "password";
    private static final String SALT_COLUMN = "salt";
    private final int KEY_LENGHT=128;
    private final int SALT_LENGHT= KEY_LENGHT/8;
    private byte[] salt;
    private static int VERSION = 1;

    /*CONSTRUCTOR*/
    public LoginDAO(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    /*INITIALIZE THE DATABASE*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , " +
                USERNAME_COLUMN +" TEXT NOT NULL ,"+ PASSWORD_COLUMN +" TEXT NOT NULL ,"+ SALT_COLUMN +" TEXT NOT NULL );");
    }

    /*UPDATES THE DATABASE*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*PUBLIC METHOD FOR ADDING A USER*/

    public void addUser(String username, String password, Context context){
        if(username.isEmpty()){
            Toast.makeText(context, "Blank Field, try again.", Toast.LENGTH_LONG).show();
        }else{
            //RETRIEVING THE DATABASE TO INSERT THE INFO
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            SecureRandom random = new SecureRandom();

            salt = new byte[SALT_LENGHT];
            random.nextBytes(salt);
            String stringSalt = salt.toString();

            values.put(SALT_COLUMN,stringSalt);
            values.put(USERNAME_COLUMN, username);
            //add the hash

            password = MD5Hashing.applyHash(password+salt);
            values.put(PASSWORD_COLUMN,password);
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
        String checkUserName, checkPassword, checkSalt;
        ArrayList<Map<String, String>> users = listUsers();

        if (users.size() == 0){
            return 0;
        }
        for (Map<String, String> map : users) {
            checkUserName = map.get(USERNAME_COLUMN);
            checkPassword = map.get(PASSWORD_COLUMN);
            checkSalt = map.get(SALT_COLUMN);

            final String hash = MD5Hashing.applyHash(password+checkSalt);
            if (checkUserName.equals(username) && checkPassword.equals(hash)){
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
        Cursor cursor = db.query(DB_NAME,new String[]{USERNAME_COLUMN, PASSWORD_COLUMN,SALT_COLUMN},null,null,null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount()!= 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                //CREATES A MAP TO KEEP THE WORDS THAT WILL BE RETRIEVED LATER
                Map<String, String> item = new HashMap<>();

                String username = cursor.getString(0);
                String password = cursor.getString(1);
                String salt = cursor.getString(2);
              //  Toast.makeText(context, salt, Toast.LENGTH_SHORT).show();

                item.put(USERNAME_COLUMN, username);
                item.put(PASSWORD_COLUMN, password);
                item.put(SALT_COLUMN,salt);


                users.add(item);
                cursor.moveToNext();

            }
        }
        cursor.close();
        return users;
    }

    /*PUBLIC METHOD USED TO IDENTIFY THE FOREIGN KEY BASED ON THE LANGUAGE NAME*/
    public int getId(String username){
        SQLiteDatabase db = getReadableDatabase();
        int userID = 0;
        final String[] selectColumn = {"_id"};
        String whereClause = USERNAME_COLUMN + " =?";
        String [] whereArgs = {username};
        Cursor cursor = db.query(DB_NAME,selectColumn,whereClause,whereArgs,null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            userID = cursor.getInt(0);
            cursor.close();

        }
        return userID;
    }




}
