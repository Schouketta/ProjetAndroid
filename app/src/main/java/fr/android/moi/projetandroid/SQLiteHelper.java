package fr.android.moi.projetandroid;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String myTeamName, String otherTeamName, String nbRounds, String tech1, String art1, String espace1, String style1, String originalite1, String total1,
                           String tech2, String art2, String espace2, String style2, String originalite2, String total2){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO BATTLE VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, myTeamName);
        statement.bindString(2, otherTeamName);
        statement.bindString(3, nbRounds);
        statement.bindString(4, tech1);
        statement.bindString(5, art1);
        statement.bindString(6, espace1);
        statement.bindString(7, style1);
        statement.bindString(8, originalite1);
        statement.bindString(9, total1);
        statement.bindString(10, tech2);
        statement.bindString(11, art2);
        statement.bindString(12, espace2);
        statement.bindString(13, style2);
        statement.bindString(14, originalite2);
        statement.bindString(15, total2);

        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j){

    }
}
