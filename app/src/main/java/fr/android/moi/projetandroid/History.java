package fr.android.moi.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class History extends AppCompatActivity {

    ListView mListView2;
    private String[] prenoms2 = new String[]{
            "Antoine", "Axel", "Cyril", "Ramzi", "Sarah", "Florian",
            "Nelly", "Sarub", "Lola", "Pichi", "Chipie", "Logan",
            "Matthieu", "Rossard", "Jacques", "Philippe", "Quentin", "Romain",
            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
            "Yann", "Zoé"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mListView2 = (ListView) findViewById(R.id.listView2);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(History.this,
                android.R.layout.simple_list_item_1, prenoms2);
        mListView2.setAdapter(adapter);
    }
}
