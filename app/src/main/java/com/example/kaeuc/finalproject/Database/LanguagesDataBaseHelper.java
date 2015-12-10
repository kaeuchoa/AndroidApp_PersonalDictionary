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
 * Created by kaeuc on 11/29/2015.
 */
public class LanguagesDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATA_BASE = "languages";
    private static int VERSION = 1;
    public LanguagesDataBaseHelper(Context context) {
        super(context,DATA_BASE,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATA_BASE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , " +
                "language TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addLanguage(String newLanguage,Context context){
        if(newLanguage.isEmpty()){
            Toast.makeText(context, "Blank Field, try again.", Toast.LENGTH_LONG).show();
        }else{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("language",newLanguage);
            long insert = db.insert(DATA_BASE, null, values);
            if(insert != -1){
                Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public String[] listLanguages(Context context){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT language " +
                "FROM " + DATA_BASE, null);
        String [] languages = new String[cursor.getCount()];
       if(cursor.getCount() == 0){

        }else {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                languages[i] = cursor.getString(0);
                cursor.moveToNext();
            }
            cursor.close();

        }
        return languages;
    }

}
