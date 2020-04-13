package fr.android.moi.projetandroid;

import android.widget.TextView;

import org.w3c.dom.Text;

public class ListViewHistory_Adapter {

    private String mTeam1;
    private String mTeam2;
    private String mScore1;
    private String mScore2;

    public ListViewHistory_Adapter(String team1, String team2, String score1, String score2){
        this.mTeam1 = team1;
        this.mTeam2 = team2;
        this.mScore1 = score1;
        this.mScore2 = score2;
    }

    @Override
    public String toString() {
        return this.mTeam1 + " VS " + this.mTeam2 + " : " + mScore1 + " - " + mScore2;
    }


}
