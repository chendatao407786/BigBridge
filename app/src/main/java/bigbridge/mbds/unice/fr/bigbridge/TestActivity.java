package bigbridge.mbds.unice.fr.bigbridge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import bigbridge.mbds.unice.fr.bigbridge.util.PreferencesManager;

public class TestActivity extends AppCompatActivity {
    String TAG = "TestActivity";
    String string;
    PreferencesManager preferencesManager;
    @Override
    protected void onNewIntent(Intent intent) {
        string = intent.getDataString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        preferencesManager = PreferencesManager.getInstance(this);
        TextView test = findViewById(R.id.test);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //     setSupportActionBar(toolbar);
        onNewIntent(getIntent());
        String accessToken = string.substring(string.indexOf("#access_token") + 14,string.indexOf("&user_id"));
        String userId = string.substring(string.indexOf("&user_id")+9, string.indexOf("&scope"));
        String tokenType = string.substring(string.indexOf("&token_type")+12,string.indexOf("&expires_in"));

        Log.i(TAG, string);
        Log.i(TAG, accessToken);
        Log.i(TAG, userId);
        Log.i(TAG, tokenType);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferencesManager.set_have_authorization(true);
        preferencesManager.set_is_just_authorized(true);
        preferencesManager.setClientID(userId);
        preferencesManager.setFibitToken(accessToken);
        preferencesManager.set_token_type(tokenType);
//        sharedPreferences.edit().putString(PreferencesManager.ACCESS_TOKEN, accessToken).apply();
//        sharedPreferences.edit().putString(PreferencesManager.TOKEN_TYPE,tokenType).apply();
//        sharedPreferences.edit().putString(PreferencesManager.FULL_AUTHORIZATION, accessToken).apply();

        Intent intent = new Intent(TestActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
