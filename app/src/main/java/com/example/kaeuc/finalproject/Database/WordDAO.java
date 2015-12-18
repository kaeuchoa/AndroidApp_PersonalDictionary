package com.example.kaeuc.finalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.kaeuc.finalproject.WordClass;
import com.example.kaeuc.finalproject.Extras.WordsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaeuc on 11/29/2015.
 */
public class WordDAO extends SQLiteOpenHelper {

    /*CONSTANTS WITH COLUMNS NAMES AND DB NAME*/
    private static final String DB_NAME = "words";
    private static final String ID_COLUMN = "_id";
    private static final String FOREIGNKEY_COLUMN = "idLanguage";
    private static final String WORD_COLUMN = "word";
    private static final String DEFINITION_COLUMN = "definition";
    private static final String SENTENCE_COLUMN = "sentence";
    private static int VERSION = 1;

    /*CONSTRUCTOR*/
    public WordDAO(Context context) {
        super(context, DB_NAME,null,VERSION);
    }

    /*INITIALIZE THE DATABASE*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_NAME + " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , " +
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
            long insert = writableDatabase.insert(DB_NAME, null, values);


            //TREAT IT
            if(insert != -1){

            }else{

            }
        }
    }


    /*PUBLIC METHOD TO RETRIEVE THE WORD FROM THE DATABASE*/
    public void listWordssss(ArrayList<Map<String,String>> words, String langName,Context context){

        //CREATES A LANGUAGE HELPER TO RETRIEVE THE APPROPRIATE LANGUAGE ID
        LanguagesDAO langHelper = new LanguagesDAO(context);

        //GETS THE DATABASE TO READ FROM
        SQLiteDatabase readableDatabase = getReadableDatabase();
        //RETRIEVE ID
        int langID = langHelper.getId(context, langName);

        //PIECES OF INFORMATION TO BUILD THE QUERY
        final String [] columns = {WORD_COLUMN,DEFINITION_COLUMN,SENTENCE_COLUMN};
        final String whereClause = FOREIGNKEY_COLUMN+"= ?";
        String[] whereArgs = {String.valueOf(langID)} ;
        //EXECUTE THE QUERY
        Cursor cursor = readableDatabase.query(DB_NAME,columns,whereClause,whereArgs,null,null,WORD_COLUMN);

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

    public void listWords(String langName, Context context, WordsAdapter adapter){

        LanguagesDAO langHelper = new LanguagesDAO(context);

        //GETS THE DATABASE TO READ FROM
        SQLiteDatabase readableDatabase = getReadableDatabase();
        //RETRIEVE ID
        int langID = langHelper.getId(context, langName);

        //PIECES OF INFORMATION TO BUILD THE QUERY
        final String [] columns = {WORD_COLUMN,DEFINITION_COLUMN,SENTENCE_COLUMN};
        final String whereClause = FOREIGNKEY_COLUMN+"= ?";
        String[] whereArgs = {String.valueOf(langID)} ;
        //EXECUTE THE QUERY
        Cursor cursor = readableDatabase.query(DB_NAME,columns,whereClause,whereArgs,null,null,WORD_COLUMN);

        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            WordClass newWord = new WordClass(cursor.getString(0),
                                                    cursor.getString(1),
                                                    cursor.getString(2));
            adapter.add(newWord);
            cursor.moveToNext();
        }
    }


    /*PUBLIC METHOD TO DELETE WORDS FROM THE DATABASE*/
    public void deleteWord(String word){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(DB_NAME, WORD_COLUMN + "=?", new String[]{word});

    }
    /*PUBLIC METHOD TO DELETE WORDS BY ID FROM THE DATABASE*/
    public void deleteByID(String id){
        SQLiteDatabase db = getReadableDatabase();
        db.delete(DB_NAME,FOREIGNKEY_COLUMN+"=?",new String[]{id});

    }

    public void updateWord(String word,String newWord){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(WORD_COLUMN,newWord);
        db.update(DB_NAME, values, WORD_COLUMN + "=?",
                new String[]{word});

    }

    public void updateDefinition(String definition,String newDefinition){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DEFINITION_COLUMN,newDefinition);
        db.update(DB_NAME,values, DEFINITION_COLUMN + "=?",
                new String[]{definition});

    }

    public void updateSentence(String sentence,String newSentence){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(SENTENCE_COLUMN,newSentence);
        db.update(DB_NAME,values, SENTENCE_COLUMN + "=?",
                new String[]{sentence});

    }


}
