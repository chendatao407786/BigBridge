package bigbridge.mbds.unice.fr.bigbridge;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import bigbridge.mbds.unice.fr.bigbridge.fragment.AirMonitorFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.MyProfileFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.ResultFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.WellBeingFragment;
import bigbridge.mbds.unice.fr.bigbridge.util.PreferencesManager;

public class MainActivity extends AppCompatActivity {
    String username;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Bundle bundle = getIntent().getExtras();
//        username = bundle.getString("username");
        username = PreferencesManager.getInstance(getApplicationContext()).loadUsername();
        bottomNavigationView = findViewById(R.id.botton_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        createProfileFragement();
                        return true;
                    case R.id.well_being:
                        createWellBeingFragment();
                        return true;
                    case R.id.pollution:
                        createAirMonitorFragement();
                        return true;
                    case R.id.result:
                        createResultFragement();
                        return true;
                }
                return false;
            }
        });
        createProfileFragement();
    }

    private void createProfileFragement(){
        MyProfileFragment myProfileFragment = new MyProfileFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("username",username);
//        myProfileFragment.setArguments(bundle);
        createFragement(myProfileFragment);
    }

    private void createWellBeingFragment(){
        WellBeingFragment wellBeingFragment = new WellBeingFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("username",username);
//        wellBeingFragment.setArguments(bundle);
        createFragement(wellBeingFragment);
    }

    private void createAirMonitorFragement(){
        AirMonitorFragment airMonitorFragment = new AirMonitorFragment();
        createFragement(airMonitorFragment);
    }

    private void createResultFragement(){
        ResultFragment resultFragment = new ResultFragment();
        createFragement(resultFragment);
    }
    private void createFragement(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main,fragment);
        fragmentTransaction.commit();
    }
}
