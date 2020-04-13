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


    public static final String EXTRA_TOTAL1 = "fr.android.moi.projetandroid.extra.EXTRA_TOTAL1";
    public static final String EXTRA_TOTAL2 = "fr.android.moi.projetandroid.extra.EXTRA_TOTAL2";
    public static final String EXTRA_TECH1 = "fr.android.moi.projetandroid.extra.EXTRA_TECH1";
    public static final String EXTRA_TECH2 = "fr.android.moi.projetandroid.extra.EXTRA_TECH2";
    public static final String EXTRA_ART1 = "fr.android.moi.projetandroid.extra.EXTRA_ART1";
    public static final String EXTRA_ART2 = "fr.android.moi.projetandroid.extra.EXTRA_ART2";
    public static final String EXTRA_ESPACE1 = "fr.android.moi.projetandroid.extra.EXTRA_ESPACE1";
    public static final String EXTRA_ESPACE2 = "fr.android.moi.projetandroid.extra.EXTRA_ESPACE2";
    public static final String EXTRA_STYLE1 = "fr.android.moi.projetandroid.extra.EXTRA_STYLE1";
    public static final String EXTRA_STYLE2 = "fr.android.moi.projetandroid.extra.EXTRA_STYLE2";
    public static final String EXTRA_ORIGINAL1 = "fr.android.moi.projetandroid.extra.EXTRA_ORIGINAL1";
    public static final String EXTRA_ORIGINAL2 = "fr.android.moi.projetandroid.extra.EXTRA_ORIGINAL2";


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


                Intent intent = new Intent(Add2.this, Camera.class);
                intent.putExtra(Add.EXTRA_TEAM_NAME, nameTeam1);
                intent.putExtra(Add.EXTRA_TEAM_NAME_OTHER, nameTeam2);
                intent.putExtra(EXTRA_TOTAL1, Scal1);
                intent.putExtra(EXTRA_TOTAL2, Scal2);
                intent.putExtra(EXTRA_TECH1, Stech1);
                intent.putExtra(EXTRA_TECH2, Stech2);
                intent.putExtra(EXTRA_ESPACE1, Sespace1);
                intent.putExtra(EXTRA_ESPACE2, Sespace2);
                intent.putExtra(EXTRA_ART1,Sart1 );
                intent.putExtra(EXTRA_ART2, Sart2 );
                intent.putExtra(EXTRA_STYLE1, Sstyle1);
                intent.putExtra(EXTRA_STYLE2, Sstyle2);
                intent.putExtra(EXTRA_ORIGINAL1, Soriginal1);
                intent.putExtra(EXTRA_ORIGINAL2, Soriginal2);
            startActivity(intent);

        }
        });

    }
}


