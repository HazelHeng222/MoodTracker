package sg.edu.ep.c346.id20029318.moodtracker;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mood.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MOOD = "Mood";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_MOOD = "mood";
    private static final String COLUMN_DATE = "date";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Song
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT,
        // singers TEXT, stars INTEGER, year INTEGER );
        String createMoodTableSql = "CREATE TABLE " + TABLE_MOOD + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_MOOD + " TEXT, "
                + COLUMN_DATE + " TEXT )";
        db.execSQL(createMoodTableSql);
        Log.i("info", createMoodTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOOD);
        onCreate(db);
    }

    public long addMood(String name, String description, String mood, String date) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_MOOD, mood);
        values.put(COLUMN_DATE, date);
        // Insert the row into the TABLE
        long result = db.insert(TABLE_MOOD, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<Mood> getAllMoods() {
        ArrayList<Mood> moodlist = new ArrayList<Mood>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_DESCRIPTION + ","
                + COLUMN_MOOD + ","
                + COLUMN_DATE + " FROM " + TABLE_MOOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String mood = cursor.getString(3);
                String date = cursor.getString(4);

                Mood newMood = new Mood(id, name, description, mood, date);
                moodlist.add(newMood);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return moodlist;
    }

    public ArrayList<Mood> getAllEntriesByMood(String moodFilter) {
        ArrayList<Mood> moodlist = new ArrayList<Mood>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_MOOD, COLUMN_MOOD};
        String condition = COLUMN_MOOD + ">= ?";
        String[] args = {String.valueOf(moodFilter)};

        //String selectQuery = "SELECT " + COLUMN_ID + ","
        //            + COLUMN_TITLE + ","
        //            + COLUMN_SINGERS + ","
        //            + COLUMN_YEAR + ","
        //            + COLUMN_STARS
        //            + " FROM " + TABLE_SONG;

        Cursor cursor;
        cursor = db.query(TABLE_MOOD, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String mood = cursor.getString(3);
                String date = cursor.getString(4);

                Mood newMood = new Mood(id, name, description, mood, date);
                moodlist.add(newMood);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return moodlist;
    }

    public int updateMood(Mood data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_DESCRIPTION, data.getDescription());
        values.put(COLUMN_MOOD, data.getMood());
        values.put(COLUMN_DATE, data.getDate());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_MOOD, values, condition, args);
        db.close();
        return result;
    }


    public int deleteMood(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOOD, condition, args);
        db.close();
        return result;
    }

}

