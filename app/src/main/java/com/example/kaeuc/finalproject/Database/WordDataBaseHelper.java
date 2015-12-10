package com.example.kaeuc.finalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaeuc on 11/29/2015.
 */
public class WordDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATA_BASE = "words";
    private static int VERSION = 1;
    private static final String ID_COLUMN = "_id";
    private static final String FOREIGNKEY_COLUMN = "idLanguage";
    private static final String WORD_COLUMN = "word";
    private static final String DEFINITION_COLUMN = "definition";
    private static final String SENTENCE_COLUMN = "sentence";
    public WordDataBaseHelper(Context context) {
        super(context,DATA_BASE,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATA_BASE + " ("+ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , " +
                FOREIGNKEY_COLUMN+" INTEGER REFERENCES language (_id)" +
                ","+WORD_COLUMN+" TEXT NOT NULL,"+DEFINITION_COLUMN +" TEXT NOT NULL," + SENTENCE_COLUMN + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addWord(String[] columns,Context context){
        if(columns.length != 4){
            Toast.makeText(context, "Invalid Size of Columns", Toast.LENGTH_LONG).show();
        }else{
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(WORD_COLUMN,columns[0]);
            values.put(DEFINITION_COLUMN,columns[1]);
            values.put(SENTENCE_COLUMN, columns[2]);
            values.put(FOREIGNKEY_COLUMN,Integer.getInteger(columns[3]));


            long insert = db.insert(DATA_BASE, null, values);
            if(insert != -1){
                Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void listWords(ArrayList<Map<String,String>> words, String langName,Context context){

        SQLiteDatabase db = getReadableDatabase();
        LanguagesDataBaseHelper langHelper = new LanguagesDataBaseHelper(context);
        int langID = langHelper.getId(context, langName);
        Cursor cursor = db.rawQuery("SELECT "+WORD_COLUMN+","+DEFINITION_COLUMN+","+SENTENCE_COLUMN+
                " FROM "+ DATA_BASE + " WHERE " +FOREIGNKEY_COLUMN+" = "+ langID,null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
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



}
