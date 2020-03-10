package fr.android.moi.dancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Add2 extends AppCompatActivity {

    Button btnAdd;
    String nameTeam, nameOTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            nameTeam = extras.getString("myTeam");
            nameOTeam = extras.getString("otherTeam");
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == btnAdd) {
                    Intent intent = new Intent(Add2.this, Match.class);
                    startActivity(intent);
                }
            }
        });
    }
}
