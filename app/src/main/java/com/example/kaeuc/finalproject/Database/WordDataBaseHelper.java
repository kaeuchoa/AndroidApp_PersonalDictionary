package com.example.kaeuc.finalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaeuc on 11/29/2015.
 */
public class WordDataBaseHelper extends SQLiteOpenHelper {

    /*CONSTANTS WITH COLUMNS NAMES AND DB NAME*/
    private static final String DATA_BASE = "words";
    private static final String ID_COLUMN = "_id";
    private static final String FOREIGNKEY_COLUMN = "idLanguage";
    private static final String WORD_COLUMN = "word";
    private static final String DEFINITION_COLUMN = "definition";
    private static final String SENTENCE_COLUMN = "sentence";
    private static int VERSION = 1;

    /*CONSTRUCTOR*/
    public WordDataBaseHelper(Context context) {
        super(context,DATA_BASE,null,VERSION);
    }

    /*INITIALIZE THE DATABASE*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATA_BASE + " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , " +
                FOREIGNKEY_COLUMN + " INTEGER REFERENCES language (_id)" +
                "," + WORD_COLUMN + " TEXT NOT NULL," + DEFINITION_COLUMN + " TEXT NOT NULL," + SENTENCE_COLUMN + " TEXT NOT NULL);");
    }

    /*UPDATES THE DATABASE*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*PUBLIC METHOD FOR ADDING A WORD*/
    public void addWord(String[] columns,Context context){
        if (columns.length == 4) {
            //RETRIEVING THE DATABASE TO INSERT THE INFO
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(WORD_COLUMN,columns[0]);
            values.put(DEFINITION_COLUMN,columns[1]);
            values.put(SENTENCE_COLUMN, columns[2]);
            values.put(FOREIGNKEY_COLUMN, columns[3]);
            long insert = writableDatabase.insert(DATA_BASE, null, values);


            //TREAT IT
            if(insert != -1){

            }else{

            }
        }
    }


    /*PUBLIC METHOD TO RETRIEVE THE WORD FROM THE DATABASE*/
    public void listWords(ArrayList<Map<String,String>> words, String langName,Context context){

        //CREATES A LANGUAGE HELPER TO RETRIEVE THE APPROPRIATE LANGUAGE ID
        LanguagesDataBaseHelper langHelper = new LanguagesDataBaseHelper(context);

        //GETS THE DATABASE TO READ FROM
        SQLiteDatabase readableDatabase = getReadableDatabase();
        //RETRIEVE ID
        int langID = langHelper.getId(context, langName);

        //PIECES OF INFORMATION TO BUILD THE QUERY
        final String [] columns = {WORD_COLUMN,DEFINITION_COLUMN,SENTENCE_COLUMN};
        final String whereClause = FOREIGNKEY_COLUMN+"= ?";
        String[] whereArgs = {String.valueOf(langID)} ;
        //EXECUTE THE QUERY
        Cursor cursor = readableDatabase.query(DATA_BASE,columns,whereClause,whereArgs,null,null,WORD_COLUMN);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            //CREATES A MAP TO KEEP THE WORDS THAT WILL BE RETRIEVED LATER
            Map<String, String> item = new HashMap<>();

            String word = cursor.getString(0);
            String definition = cursor.getString(1);
            String sentence = cursor.getString(2);

            item.put(WORD_COLUMN,word);
            item.put(DEFINITION_COLUMN,definition);
            item.put(SENTENCE_COLUMN,sentence);

            words.add(item);
            cursor.moveToNext();

        }
        cursor.close();
    }


    /*PUBLIC METHOD TO DELETE WORDS FROM THE DATABASE*/
    public void deleteWord(String word){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(DATA_BASE, WORD_COLUMN + "=?", new String[]{word});

    }
    /*PUBLIC METHOD TO DELETE WORDS BY ID FROM THE DATABASE*/
    public void deleteByID(String id){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(DATA_BASE,FOREIGNKEY_COLUMN+"=?",new String[]{id});

    }


}
