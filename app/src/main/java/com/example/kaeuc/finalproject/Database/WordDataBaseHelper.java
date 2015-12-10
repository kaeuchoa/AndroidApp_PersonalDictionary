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
    public WordDataBaseHelper(Context context) {
        super(context,DATA_BASE,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATA_BASE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , " +
                "FOREIGN KEY (idLanguage) REFERENCES language (_id)" +
                "word TEXT NOT NULL, definition TEXT NOT NULL," + "sentence TEXT NOT NULL);");
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
            values.put("word",columns[0]);
            values.put("definition",columns[1]);
            values.put("sentence", columns[2]);
            values.put("idLanguage",columns[3]);


            long insert = db.insert(DATA_BASE, null, values);
            if(insert != -1){
                Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void listWords(ArrayList<Map<String,String>> words){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT word,definition,sentence "+
                "FROM words",null);

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Map<String, String> item = new HashMap<>();
//            int id = cursor.getInt(0);
            String word = cursor.getString(0);
            String definition = cursor.getString(1);
            String sentence = cursor.getString(2);
//            item.put("id",Integer.toString(id));
            item.put("word",word);
            item.put("definition",definition);
            item.put("sentence",sentence);

            words.add(item);
            cursor.moveToNext();

        }
        cursor.close();
    }



}
