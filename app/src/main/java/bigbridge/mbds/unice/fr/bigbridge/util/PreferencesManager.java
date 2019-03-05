package bigbridge.mbds.unice.fr.bigbridge.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static PreferencesManager preferencesManager;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "myPreferences";
    private static final String PREFS_USERNAME = "prefs_username";
    private static final String PREFS_EMAIL = "prefs_email";
    private static final String PREFS_PASSWORD = "prefs_password";
    private static final String PREFS_TOKEN = "prefs_token";
    private static final String HAVE_AUTHORIZATION = "accessTokenExists";
    private static final String IS_JUST_AUTHORIZED = "is_just_authorized";
    private static final String USER_ID ="clientID";
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String TOKEN_TYPE ="tokenType";
    public static final String HAVE_DEVICE_ID ="haveTrackerIds";
    public static final String MY_DEVICE_ID="deviceID";
    public static final String FULL_AUTHORIZATION= "fullAuthorizationToken";
    public static final String BASE_URL="http://192.168.1.22:8080/";
    public static final String AIRPACA_URL="http://api.airpaca.org/communes/06088/indices?date=yesterday&api_token=540b9754baf94dc4f79dcb4fba651d64";



    public static PreferencesManager getInstance(Context context) {

        //test and return instance class
        if(preferencesManager == null){
            preferencesManager = new PreferencesManager(context);
        }
        return preferencesManager;
    }

    private PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveUsername(String username){
        sharedPreferences.edit().putString(PREFS_USERNAME, username).apply();
    }

    public String loadUsername(){
        return sharedPreferences.getString(PREFS_USERNAME, null);
    }

    public void saveEmail(String email){
        sharedPreferences.edit().putString(PREFS_EMAIL, email).apply();
    }
    public String loadEmail(){
        return sharedPreferences.getString(PREFS_EMAIL,null);
    }
    public void savePwd(String password){
        sharedPreferences.edit().putString(PREFS_PASSWORD, password).apply();
    }
    public String loadPwd(){
        return sharedPreferences.getString(PREFS_PASSWORD, null);
    }
    public void saveToken(String accessToken){
        sharedPreferences.edit().putString(PREFS_TOKEN, accessToken).apply();
    }
    public String loadToken(){
        return sharedPreferences.getString(PREFS_TOKEN, null);
    }
    public void set_have_authorization(boolean have_authorization){
        sharedPreferences.edit().putBoolean(HAVE_AUTHORIZATION,have_authorization).apply();
    }
    public boolean have_authorization(){
        return sharedPreferences.getBoolean(HAVE_AUTHORIZATION,false);
    }
    public void set_is_just_authorized(boolean is_just_authorized){
        sharedPreferences.edit().putBoolean(IS_JUST_AUTHORIZED,is_just_authorized).apply();
    }
    public boolean get_is_just_authorized(){
        return sharedPreferences.getBoolean(IS_JUST_AUTHORIZED,false);
    }
    public void setClientID(String user_id){
        sharedPreferences.edit().putString(USER_ID,user_id).apply();
    }
    public String getClientID(){
        return sharedPreferences.getString(USER_ID,null);
    }
    public void setFibitToken(String fibitToken){
        sharedPreferences.edit().putString(ACCESS_TOKEN,fibitToken).apply();
    }
    public String getFibitToken(){
        return sharedPreferences.getString(ACCESS_TOKEN,null);
    }
    public void set_token_type(String token_type){
        sharedPreferences.edit().putString(TOKEN_TYPE,token_type).apply();
    }
    public String get_token_type(){
        return sharedPreferences.getString(TOKEN_TYPE,null);
    }
}
