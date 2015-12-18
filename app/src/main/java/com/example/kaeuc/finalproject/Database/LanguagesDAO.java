package com.example.kaeuc.finalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * CLASS TO DEAL WITH THE LANGUAGE DATABASE OPERATIONS
 * Created by kaeuc on 11/29/2015.
 */
public class LanguagesDAO extends SQLiteOpenHelper {

    /*CONSTANTS WITH COLUMNS NAMES AND DB NAME*/
    private static final String DB_NAME = "languages";
    private static final String LANG_COLUMN = "language";
    private static final String FOREIGNKEY_COLUMN = "idusername";
    private static int VERSION = 1;



    /*CONSTRUCTOR*/
    public LanguagesDAO(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    /*INITIALIZE THE DATABASE*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , "+
                FOREIGNKEY_COLUMN + " INTEGER REFERENCES login (_id) ," +
                LANG_COLUMN +" TEXT NOT NULL);");

    }

    /*UPDATES THE DATABASE*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*PUBLIC METHOD FOR ADDING A LANGUAGE*/

    public void addLanguage(String newLanguage,String username,Context context){
        if(newLanguage.isEmpty()){
            Toast.makeText(context, "Blank Field, try again.", Toast.LENGTH_LONG).show();
        }else{
            //RETRIEVING THE DATABASE TO INSERT THE INFO
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            LoginDAO helper = new LoginDAO(context);
            //Toast.makeText(context, username, Toast.LENGTH_SHORT).show();

            final int id = helper.getId(username);
            String userID = String.valueOf(id);
            values.put(LANG_COLUMN, newLanguage);
            values.put(FOREIGNKEY_COLUMN, userID);
            long insert = db.insert(DB_NAME, null, values);

            /*DEALS WITH THE RESULT OF INSERT*/
            if(insert != -1){
                Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*PUBLIC METHOD FOR RETRIEVING THE LANGUAGES FROM THE DATABASE*/
    public String[] listLanguages(Context context,String username){
        //GETS THE DATABASE TO READ FROM
        SQLiteDatabase db = getReadableDatabase();
        LoginDAO helper = new LoginDAO(context);
        final int id = helper.getId(username);
        final String stringID = String.valueOf(id);
        //CREATES THE SELECT QUERY
        Cursor cursor = db.query(DB_NAME,new String[]{LANG_COLUMN},FOREIGNKEY_COLUMN+"=?",
                                                   new String[]{stringID},null,null, LANG_COLUMN);

        //CREATES THE ARRAY TO RETURN THAT WILL TURN INTO BUTTONS
        String [] languages = new String[cursor.getCount()];

        //IF RETURNS SOMETHING INSERT THE VALUES IN THE ARRAY
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                languages[i] = cursor.getString(0);
                cursor.moveToNext();
            }
            cursor.close();

        }
        return languages;
    }

    /*PUBLIC METHOD USED TO IDENTIFY THE FOREIGN KEY BASED ON THE LANGUAGE NAME*/
    public int getId(Context context,String lang){
        SQLiteDatabase db = getReadableDatabase();
        int langID = 0;
        String whereClause = LANG_COLUMN + "=?";
        String [] whereArgs = {lang};
        Cursor cursor = db.query(DB_NAME,null,whereClause,whereArgs,null,null,null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            langID = cursor.getInt(0);
            cursor.close();

        }
        return langID;
    }


    /*PUBLIC METHOD TO DELETE LANGUAGES AND ALL ITS REGISTERS IN THE WORD TABLE */
    public void deleteLanguage(String langName, Context context){
        SQLiteDatabase db = getReadableDatabase();
        WordDAO helper = new WordDAO(context);
        helper.deleteByID(""+getId(context,langName));
        db.delete(DB_NAME, LANG_COLUMN + "=?", new String[]{langName});
    }

    public void updateLanguage(String langName,String newName,Context context){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(LANG_COLUMN,newName);
        db.update(DB_NAME,values, LANG_COLUMN + "=?",
                                            new String[]{langName});
    }


}
