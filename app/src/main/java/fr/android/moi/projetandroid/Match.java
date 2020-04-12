package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Match extends AppCompatActivity {

    Button btnMap;
    private SQLite.FeedReaderDbHelper DB;
    TextView score, winner, teams, latitude, longitude;
    TextView team1, tech1, art1, espace1, style1, original1, cal1;
    TextView team2, tech2, art2, espace2, style2, original2, cal2;


    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        score = (TextView) findViewById(R.id.score);
        winner = (TextView) findViewById(R.id.winner);
        teams = (TextView) findViewById(R.id.teams);

        team1 = (TextView) findViewById(R.id.myTeamName);
        team2 = (TextView) findViewById(R.id.otherTeamName);

        cal1 = (TextView) findViewById(R.id.mtotal1);
        tech1 = (TextView) findViewById(R.id.mtech1);
        art1 = (TextView) findViewById(R.id.mart1);
        espace1 = (TextView) findViewById(R.id.mespace1);
        style1 = (TextView) findViewById(R.id.mstyle1);
        original1 = (TextView) findViewById(R.id.moriginal1);

        cal2 = (TextView) findViewById(R.id.mtotal2);
        tech2 = (TextView) findViewById(R.id.mtech2);
        art2 = (TextView) findViewById(R.id.mart2);
        espace2 = (TextView) findViewById(R.id.mespace2);
        style2 = (TextView) findViewById(R.id.mstyle2);
        original2 = (TextView) findViewById(R.id.moriginal2);

        latitude = (TextView) findViewById(R.id.latitude);
        longitude = (TextView) findViewById(R.id.longitude);

        imgView = (ImageView) findViewById(R.id.imgMatch);


        Intent intent = getIntent();

        DB = new SQLite.FeedReaderDbHelper(getApplicationContext());

        SQLiteDatabase db = DB.getReadableDatabase();

        Cursor cursor = DB.getDataForMatch(intent.getStringExtra(Add.EXTRA_TEAM_NAME), intent.getStringExtra(Add.EXTRA_TEAM_NAME_OTHER));

        while(cursor.moveToNext()){

            /*g.d("myteam", cursor.getString(1));
            Log.d("otherteam", cursor.getString(2));
            Log.d("tech1", cursor.getString(3));
            Log.d("total2", cursor.getString(14));*/

            team1.setText(cursor.getString(1));
            team2.setText(cursor.getString(2));
            tech1.setText(cursor.getString(3));
            art1.setText(cursor.getString(4));
            espace1.setText(cursor.getString(5));
            style1.setText(cursor.getString(6));
            original1.setText(cursor.getString(7));
            cal1.setText(cursor.getString(8));
            tech2.setText(cursor.getString(9));
            art2.setText(cursor.getString(10));
            espace2.setText(cursor.getString(11));
            style2.setText(cursor.getString(12));
            original2.setText(cursor.getString(13));
            cal2.setText(cursor.getString(14));
            latitude.setText("latitude : "+ cursor.getString(15));
            longitude.setText("longitude : "+cursor.getString(16));
            if(cursor.getBlob(17) != null){
                Bitmap img_en_tableau_de_bytes_tmp = BitmapFactory.decodeByteArray(cursor.getBlob(17), 0, cursor.getBlob(17).length); //On retransforme le tab de bytes en BITMAP
                imgView.setImageBitmap(img_en_tableau_de_bytes_tmp); //On peut maintenant set l'image view avec la bitmap
            }
            else if(cursor.getBlob(17) == null){
                imgView.setImageResource(R.drawable.ic_image_black_24dp);
            }

            String Sscore = cursor.getString(8) + " - " + cursor.getString(14);
            score.setText(Sscore);
            String Steams = cursor.getString(1) + " vs " + cursor.getString(2);
            teams.setText(Steams);

            if(Integer.parseInt(cursor.getString(8)) > Integer.parseInt(cursor.getString(14)) ){
                winner.setText(cursor.getString(1));
            }
            else if(Integer.parseInt(cursor.getString(14)) > Integer.parseInt(cursor.getString(8))){
                winner.setText(cursor.getString(2));
            }
            else if(Integer.parseInt(cursor.getString(14)) == Integer.parseInt(cursor.getString(8))){
                winner.setText("EX AEQUO");
            }

        }

    }
}
