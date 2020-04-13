package fr.android.moi.projetandroid;

public class Battle {
    private int id;
    private String myTameName;
    private String otherTeamName;
    private String nbRounds;
    private String tech1;
    private String art1;
    private String espace1;
    private String style1;
    private String originalite1;
    private String total1;
    private String tech2;
    private String art2;
    private String espace2;
    private String style2;
    private String originalite2;
    private String total2;
    private String latitude;
    private String longitude;


    public Battle(int id, String myTameName, String otherTeamName, String nbRounds, String tech1, String art1, String espace1, String style1, String originalite1, String total1, String tech2, String art2, String espace2, String style2, String originalite2, String total2, String latitude, String longitude) {
        this.id = id;
        this.myTameName = myTameName;
        this.otherTeamName = otherTeamName;
        this.nbRounds = nbRounds;
        this.tech1 = tech1;
        this.art1 = art1;
        this.espace1 = espace1;
        this.style1 = style1;
        this.originalite1 = originalite1;
        this.total1 = total1;
        this.tech2 = tech2;
        this.art2 = art2;
        this.espace2 = espace2;
        this.style2 = style2;
        this.originalite2 = originalite2;
        this.total2 = total2;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMyTameName() {
        return myTameName;
    }

    public void setMyTameName(String myTameName) {
        this.myTameName = myTameName;
    }

    public String getOtherTeamName() {
        return otherTeamName;
    }

    public void setOtherTeamName(String otherTeamName) {
        this.otherTeamName = otherTeamName;
    }

    public String getNbRounds() {
        return nbRounds;
    }

    public void setNbRounds(String nbRounds) {
        this.nbRounds = nbRounds;
    }

    public String getTech1() {
        return tech1;
    }

    public void setTech1(String tech1) {
        this.tech1 = tech1;
    }

    public String getArt1() {
        return art1;
    }

    public void setArt1(String art1) {
        this.art1 = art1;
    }

    public String getEspace1() {
        return espace1;
    }

    public void setEspace1(String espace1) {
        this.espace1 = espace1;
    }

    public String getStyle1() {
        return style1;
    }

    public void setStyle1(String style1) {
        this.style1 = style1;
    }

    public String getOriginalite1() {
        return originalite1;
    }

    public void setOriginalite1(String originalite1) {
        this.originalite1 = originalite1;
    }

    public String getTotal1() {
        return total1;
    }

    public void setTotal1(String total1) {
        this.total1 = total1;
    }

    public String getTech2() {
        return tech2;
    }

    public void setTech2(String tech2) {
        this.tech2 = tech2;
    }

    public String getArt2() {
        return art2;
    }

    public void setArt2(String art2) {
        this.art2 = art2;
    }

    public String getEspace2() {
        return espace2;
    }

    public void setEspace2(String espace2) {
        this.espace2 = espace2;
    }

    public String getStyle2() {
        return style2;
    }

    public void setStyle2(String style2) {
        this.style2 = style2;
    }

    public String getOriginalite2() {
        return originalite2;
    }

    public void setOriginalite2(String originalite2) {
        this.originalite2 = originalite2;
    }

    public String getTotal2() {
        return total2;
    }

    public void setTotal2(String total2) {
        this.total2 = total2;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return this.longitude;
    }


}
