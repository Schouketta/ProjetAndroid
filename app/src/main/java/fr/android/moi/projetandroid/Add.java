package fr.android.moi.projetandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add extends AppCompatActivity {

    Button btnAdd;
    EditText myTeam, otherTeam;
    String nameTeam, nameOTeam;

    public static final String EXTRA_TEAM_NAME =
            "fr.android.moi.projetandroid.extra.TEAM_NAME";

    public static final String EXTRA_TEAM_NAME_OTHER =
            "fr.android.moi.projetandroid.extra.TEAM_NAME_OTHER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myTeam = (EditText) findViewById(R.id.myTeam);
        otherTeam = (EditText) findViewById(R.id.otherTeam);
        btnAdd = (Button) findViewById(R.id.addBattle);


        final Intent intent = new Intent(this, Add2.class);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                nameTeam = myTeam.getText().toString();
                nameOTeam = otherTeam.getText().toString();
                intent.putExtra(EXTRA_TEAM_NAME, nameTeam);
                intent.putExtra(EXTRA_TEAM_NAME_OTHER, nameOTeam);
                startActivity(intent);
            }
        });


    }

}
