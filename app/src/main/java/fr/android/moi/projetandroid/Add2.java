package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

import static java.sql.Types.NULL;

public class Add2 extends AppCompatActivity {

    Button btnAdd;
    TextView team1, team2, total1, total2;
    Spinner tech1, art1, espace1, style1, original1;
    String Stech1, Sart1, Sespace1, Sstyle1, Soriginal1;
    Spinner tech2, art2, espace2, style2, original2;
    String Stech2, Sart2, Sespace2, Sstyle2, Soriginal2;

    //public static SQLiteHelper sqLiteHelper;
    private SQLite.FeedReaderDbHelper DB;

    /*public void myClickHandler(View view) {

        /*String tag = view.getTag().toString();

        switch (tag) {
            case "tech1":
            case "art1":
            case "espace1":
            case "style1":
            case "original1":
                total = tech1.getSelectedItem().toString();
                break;

            default:
                total1.setText(total);
                break;
        }

        total = (int) tech1.getSelectedItem() + (int) art1.getSelectedItem() + (int) espace1.getSelectedItem() + (int) style1.getSelectedItem() + (int) original1.getSelectedItem() ;
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        btnAdd = (Button) findViewById(R.id.addBattle);
        team1 = (TextView) findViewById(R.id.team1);
        team2 = (TextView) findViewById(R.id.team2);

        total1 = (TextView) findViewById(R.id.total1);
        tech1 = (Spinner) findViewById(R.id.tech1);
        art1 = (Spinner) findViewById(R.id.art1);
        espace1 = (Spinner) findViewById(R.id.espace1);
        style1 = (Spinner) findViewById(R.id.style1);
        original1 = (Spinner) findViewById(R.id.original1);

        total2 = (TextView) findViewById(R.id.total2);
        tech2 = (Spinner) findViewById(R.id.tech2);
        art2 = (Spinner) findViewById(R.id.art2);
        espace2 = (Spinner) findViewById(R.id.espace2);
        style2 = (Spinner) findViewById(R.id.style2);
        original2 = (Spinner) findViewById(R.id.original2);


        Intent intent = getIntent();
        final String nameTeam1 = intent.getStringExtra(Add.EXTRA_TEAM_NAME);
        final String nameTeam2 = intent.getStringExtra(Add.EXTRA_TEAM_NAME_OTHER);
        Toast.makeText(this, nameTeam1, Toast.LENGTH_SHORT).show();

        team1.setText(nameTeam1);
        team2.setText(nameTeam2);

        DB = new SQLite.FeedReaderDbHelper(getApplicationContext());


        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Stech1 = tech1.getSelectedItem().toString();
                Sart1 = art1.getSelectedItem().toString();
                Sespace1 = espace1.getSelectedItem().toString();
                Sstyle1 = style1.getSelectedItem().toString();
                Soriginal1 = original1.getSelectedItem().toString();

                int cal1 = Integer.parseInt(Stech1) + Integer.parseInt(Sart1) + Integer.parseInt(Sespace1) + Integer.parseInt(Sstyle1) + Integer.parseInt(Soriginal1);
                String Scal1 = Integer.toString(cal1);
                Log.d("total1", Scal1);


                Stech2 = tech2.getSelectedItem().toString();
                Sart2 = art2.getSelectedItem().toString();
                Sespace2 = espace2.getSelectedItem().toString();
                Sstyle2 = style2.getSelectedItem().toString();
                Soriginal2 = original2.getSelectedItem().toString();

                int cal2 = Integer.parseInt(Stech2) + Integer.parseInt(Sart2) + Integer.parseInt(Sespace2) + Integer.parseInt(Sstyle2) + Integer.parseInt(Soriginal2);
                String Scal2 = Integer.toString(cal2);
                Log.d("total2", Scal2);


                // Gets the data repository in write mode
                SQLiteDatabase db = DB.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(SQLite.FeedEntry.COLUMN_NAME_MY_TEAM, nameTeam1);
                values.put(SQLite.FeedEntry.COLUMN_NAME_OTHER_TEAM, nameTeam2);
                values.put(SQLite.FeedEntry.COLUMN_NAME_TECH_1, Stech1);
                values.put(SQLite.FeedEntry.COLUMN_NAME_ART_1, Sart1);
                values.put(SQLite.FeedEntry.COLUMN_NAME_ESPACE_1, Sespace1);
                values.put(SQLite.FeedEntry.COLUMN_NAME_STYLE_1, Sstyle1);
                values.put(SQLite.FeedEntry.COLUMN_NAME_ORIGINALITE_1, Soriginal1);
                values.put(SQLite.FeedEntry.COLUMN_NAME_TOTAL_1, Scal1);
                values.put(SQLite.FeedEntry.COLUMN_NAME_TECH_2, Stech2);
                values.put(SQLite.FeedEntry.COLUMN_NAME_ART_2, Sart2);
                values.put(SQLite.FeedEntry.COLUMN_NAME_ESPACE_2, Sespace2);
                values.put(SQLite.FeedEntry.COLUMN_NAME_STYLE_2, Sstyle2);
                values.put(SQLite.FeedEntry.COLUMN_NAME_ORIGINALITE_2, Soriginal2);
                values.put(SQLite.FeedEntry.COLUMN_NAME_TOTAL_2, Scal2);
                Log.d("tech1", Stech1);
                Log.d("tech2", Stech2);

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(SQLite.FeedEntry.TABLE_NAME, null, values);
                Toast.makeText(getApplicationContext(), "Battle added successfully!", Toast.LENGTH_SHORT).show();

                Cursor cursor = DB.getData();

                while (cursor.getCount() > 5 && cursor.getCount() != 5){
                    cursor = DB.deleteData();
                    cursor = DB.getData();
                }

                Intent intent = new Intent(Add2.this, Match.class);
                intent.putExtra("myTeamName", nameTeam1);
                intent.putExtra("otherTeamName", nameTeam2);
                intent.putExtra("total1", Scal1);
                intent.putExtra("total2", Scal2);
                startActivity(intent);

            }
        });

    }
}

