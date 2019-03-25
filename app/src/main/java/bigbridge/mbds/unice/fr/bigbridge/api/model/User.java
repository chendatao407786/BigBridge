package bigbridge.mbds.unice.fr.bigbridge.api.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String username;
    private String name;
    private String postalcode;
    private String birthday;
    private String sex;
    private int weight;
    private int height;
    private String smoking;
    private String drinking;
    private String sport;
    private String heart_disease;
    private String asthma;

    public User(String username, String name,String postalcode, String sex,String drinking, String birthday, int weight, String smoking, String sport, String heart_disease, String asthma) {
        this.username = username;
        this.name = name;
        this.postalcode = postalcode;
        this.sex = sex;
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.smoking = smoking;
        this.drinking = drinking;
        this.sport = sport;
        this.heart_disease = heart_disease;
        this.asthma = asthma;
    }
    public User(JSONObject dataSet){
        try {
            this.sex = dataSet.getString("SEX");
            this.postalcode = dataSet.getString("POST CODE");
            this.username = dataSet.getString("USERNAME");
            this.name = dataSet.getString("NAME");
            this.birthday = dataSet.getString("BIRTHDAY");
            this.weight = dataSet.getInt("WEIGHT");
            this.height = dataSet.getInt("HEIGHT");
            this.smoking = dataSet.getString("SMOKING");
            this.drinking = dataSet.getString("DRINKING");
            this.sport = dataSet.getString("SPORT");
            this.heart_disease = dataSet.getString("HEART DISEASE");
            this.asthma = dataSet.getString("ASTHMA");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public User(String username){
        this.username = username;
        this.name = "";
        this.postalcode = "";
        this.birthday = "";
        this.weight = 0;
        this.height = 0;
        this.sex="";
        this.smoking = "";
        this.drinking = "";
        this.sport = "";
        this.heart_disease = "";
        this.asthma = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }
    public String getDrinking() {
        return smoking;
    }

    public void setDrinking(String smoking) {
        this.smoking = smoking;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getHeart_disease() {
        return heart_disease;
    }

    public void setHeart_disease(String heart_disease) {
        this.heart_disease = heart_disease;
    }

    public String getAsthma() {
        return asthma;
    }

    public void setAsthma(String asthma) {
        this.asthma = asthma;
    }

    public String getPostalcode() {
        return postalcode;
    }
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }


}
