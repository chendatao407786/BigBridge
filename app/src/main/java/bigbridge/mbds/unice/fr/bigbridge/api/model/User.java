package bigbridge.mbds.unice.fr.bigbridge.api.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String name;
    private String birthday;
    private int weight;
    private String smoking;
    private String sport;
    private String heart_disease;
    private String asthma;

    public User(String name, String birthday, int weight, String smoking, String sport, String heart_disease, String asthma) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.smoking = smoking;
        this.sport = sport;
        this.heart_disease = heart_disease;
        this.asthma = asthma;
    }
    public User(JSONObject dataSet){
        try {
            this.name = dataSet.getString("NAME");
            this.birthday = dataSet.getString("BIRTHDAY");
            this.weight = dataSet.getInt("WEIGHT");
            this.smoking = dataSet.getString("SMOKING");
            this.sport = dataSet.getString("SPORT");
            this.heart_disease = dataSet.getString("HEART DISEASE");
            this.asthma = dataSet.getString("ASTHMA");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
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
}
