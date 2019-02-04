package bigbridge.mbds.unice.fr.bigbridge;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.facebook.react.ReactApplication;
//import bigbridge.mbds.unice.fr.bigbridge.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

import bigbridge.mbds.unice.fr.bigbridge.fragment.MyProfileFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.ResultFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.TestFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.WellBeingFragment;

public class MainActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler,PermissionAwareActivity {

    String username;
    BottomNavigationView bottomNavigationView;
    private ReactInstanceManager mReactInstanceManager;
    private PermissionListener mPermissionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReactInstanceManager = ((ReactApplication) getApplication()).getReactNativeHost().getReactInstanceManager();
        setContentView(R.layout.activity_main);
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
                        TestFragment testFragment = new TestFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, testFragment).commit();
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

//    @Override
//    public void onBackPressed() {
//        Toast.makeText(this,"Where am I?",Toast.LENGTH_LONG).show();
//    }
    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
    }
    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }


    private void createProfileFragement(){
        MyProfileFragment myProfileFragment = new MyProfileFragment();
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

//        Fragment testFragment = new TestFragment();
//        createFragement(testFragment);
//        AirMonitorFragment airMonitorFragment = new AirMonitorFragment();
//        createFragement(airMonitorFragment);
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

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        mPermissionListener = listener;
        requestPermissions(permissions, requestCode);
    }
}
