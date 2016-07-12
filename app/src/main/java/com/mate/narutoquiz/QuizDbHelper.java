package com.mate.narutoquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by omkar_000 on 6/26/2016.
 */
public class QuizDbHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.mate.narutoquiz/databases/";

    private static String DB_NAME = "NarutoQuiz.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public QuizDbHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? false : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists classA (id integer primary key autoincrement, question text, option1 text, option2 text, option3 text, option4 text,correct_answer text)");
        db.execSQL("create table if not exists classB (id integer primary key autoincrement, question text, option1 text, option2 text, option3 text, option4 text,correct_answer text)");
        db.execSQL("create table if not exists classC (id integer primary key autoincrement, question text, option1 text, option2 text, option3 text, option4 text,correct_answer text)");
        db.execSQL("create table if not exists classD (id integer primary key autoincrement, question text, option1 text, option2 text, option3 text, option4 text,correct_answer text)");
        db.execSQL("create table if not exists classS (id integer primary key autoincrement, question text, option1 text, option2 text, option3 text, option4 text,correct_answer text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists classA");
        db.execSQL("drop table if exists classB");
        db.execSQL("drop table if exists classC");
        db.execSQL("drop table if exists classD");
        db.execSQL("drop table if exists classS");
        onCreate(db);
    }

    public boolean addQuestionS(String question, String option1, String option2, String option3, String option4, String answer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("question", question);
        contentValues.put("option1", option1);
        contentValues.put("option2", option2);
        contentValues.put("option3", option3);
        contentValues.put("option4", option4);
        contentValues.put("correct_answer", answer);
        db.insert("classS", null, contentValues);
        return true;
    }

    public boolean addQuestionA(String question, String option1, String option2, String option3, String option4, String answer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("question", question);
        contentValues.put("option1", option1);
        contentValues.put("option2", option2);
        contentValues.put("option3", option3);
        contentValues.put("option4", option4);
        contentValues.put("correct_answer", answer);
        db.insert("classA", null, contentValues);
        return true;
    }

    public boolean addQuestionB(String question, String option1, String option2, String option3, String option4, String answer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("question", question);
        contentValues.put("option1", option1);
        contentValues.put("option2", option2);
        contentValues.put("option3", option3);
        contentValues.put("option4", option4);
        contentValues.put("correct_answer", answer);
        db.insert("classB", null, contentValues);
        return true;
    }

    public boolean addQuestionC(String question, String option1, String option2, String option3, String option4, String answer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("question", question);
        contentValues.put("option1", option1);
        contentValues.put("option2", option2);
        contentValues.put("option3", option3);
        contentValues.put("option4", option4);
        contentValues.put("correct_answer", answer);
        db.insert("classC", null, contentValues);
        return true;
    }

    public boolean addQuestionD(String question, String option1, String option2, String option3, String option4, String answer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("question", question);
        contentValues.put("option1", option1);
        contentValues.put("option2", option2);
        contentValues.put("option3", option3);
        contentValues.put("option4", option4);
        contentValues.put("correct_answer", answer);
        db.insert("classD", null, contentValues);
        return true;
    }

    public String[] retrieveQuestionS(int pos)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[]={"question","option1","option2","option3","option4","correct_answer"};

        Cursor cursor = db.query("classS",projection,null,null,null,null,null);
        cursor.moveToPosition(pos);
        String[] row = {cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)};
        //cursor.moveToNext();
        return row;

    }
    public String[] retrieveQuestionA(int pos)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[]={"question","option1","option2","option3","option4","correct_answer"};

        Cursor cursor = db.query("classA",projection,null,null,null,null,null);
        cursor.moveToPosition(pos);
        String[] row = {cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)};
        //cursor.moveToNext();
        return row;

    }
    public String[] retrieveQuestionB(int pos)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[]={"question","option1","option2","option3","option4","correct_answer"};

        Cursor cursor = db.query("classB",projection,null,null,null,null,null);
        cursor.moveToPosition(pos);
        String[] row = {cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)};
        return row;

    }
    public String[] retrieveQuestionC(int pos)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[]={"question","option1","option2","option3","option4","correct_answer"};
        Cursor cursor = db.query("classC",projection,null,null,null,null,null);
        cursor.moveToPosition(pos);
        String[] row = {cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)};
        //cursor.moveToNext();
        return row;

    }
    public String[] retrieveQuestionD(int pos)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String projection[]={"question","option1","option2","option3","option4","correct_answer"};

        Cursor cursor = db.query("classD",projection,null,null,null,null,null);
        cursor.moveToPosition(pos);
        String[] row = {cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)};
        //cursor.moveToNext();
        return row;

    }

    public int getQuestionCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery="SELECT * FROM classA";
        Cursor cursor = db.rawQuery(countQuery,null);
        int count = cursor.getCount();
        return count;
    }


}
