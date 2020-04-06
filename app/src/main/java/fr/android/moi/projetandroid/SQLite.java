package fr.android.moi.projetandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class SQLite {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private SQLite() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "battleDB";
        public static final String COLUMN_NAME_MY_TEAM = "myTeamName";
        public static final String COLUMN_NAME_OTHER_TEAM = "otherTeamName";
        public static final String COLUMN_NAME_TECH_1 = "tech1";
        public static final String COLUMN_NAME_ART_1 = "art1";
        public static final String COLUMN_NAME_ESPACE_1 = "espace1";
        public static final String COLUMN_NAME_STYLE_1 = "style1";
        public static final String COLUMN_NAME_ORIGINALITE_1 = "originalite1";
        public static final String COLUMN_NAME_TOTAL_1 = "total1";
        public static final String COLUMN_NAME_TECH_2 = "tech2";
        public static final String COLUMN_NAME_ART_2 = "art2";
        public static final String COLUMN_NAME_ESPACE_2 = "espace2";
        public static final String COLUMN_NAME_STYLE_2 = "style2";
        public static final String COLUMN_NAME_ORIGINALITE_2 = "originalite2";
        public static final String COLUMN_NAME_TOTAL_2 = "total2";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_MY_TEAM + " TEXT," +
                    FeedEntry.COLUMN_NAME_OTHER_TEAM + " TEXT," +
                    FeedEntry.COLUMN_NAME_TECH_1 + " TEXT," +
                    FeedEntry.COLUMN_NAME_ART_1 + " TEXT," +
                    FeedEntry.COLUMN_NAME_ESPACE_1 + " TEXT," +
                    FeedEntry.COLUMN_NAME_STYLE_1+ " TEXT," +
                    FeedEntry.COLUMN_NAME_ORIGINALITE_1 + " TEXT," +
                    FeedEntry.COLUMN_NAME_TOTAL_1 + " TEXT," +
                    FeedEntry.COLUMN_NAME_TECH_2 + " TEXT," +
                    FeedEntry.COLUMN_NAME_ART_2+ " TEXT," +
                    FeedEntry.COLUMN_NAME_ESPACE_2 + " TEXT," +
                    FeedEntry.COLUMN_NAME_STYLE_2 + " TEXT," +
                    FeedEntry.COLUMN_NAME_ORIGINALITE_2 + " TEXT," +
                    FeedEntry.COLUMN_NAME_TOTAL_2 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    public static class FeedReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public FeedReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        public Cursor getData(){
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM battleDB";
            Cursor data = db.rawQuery(query, null);
            return data;
        }

        public Cursor getDataForMatch(String myTeamName1, String otherTeamName1){
            SQLiteDatabase db = this.getWritableDatabase();
            //String query = "SELECT * FROM battleDB WHERE myTeamName = '" + myTeamName1 + "' AND otherTeamName = '" + otherTeamName1 + "' AND total1 = '" + total11 + "' AND total2 = '" + total21 + "'";
            String query = "SELECT * FROM battleDB WHERE myTeamName = '" + myTeamName1 + "' AND otherTeamName = '" + otherTeamName1 + "'";
            Cursor data = db.rawQuery(query, null);
            return data;
        }

        public Cursor deleteData(){
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM battleDB WHERE rowid IN (SELECT rowid FROM battleDB LIMIT 1)";
            Cursor data = db.rawQuery(query, null);
            db.execSQL(query);
            return data;
        }

        public Cursor getLeo(){
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT tech1 FROM battleDB WHERE myTeamName = 'lea' AND otherTeamName = 'leo'";
            Cursor leolea = db.rawQuery(query, null);
            return leolea;

        }

        /*cursor = DB.getLeo();

        while(cursor.moveToNext()){

            Log.d("techTest", cursor.getString(0));
            //Log.d("techTest1", cursor.getString(1));

        }*/

    }

}
