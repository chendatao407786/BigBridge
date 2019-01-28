package bigbridge.mbds.unice.fr.bigbridge;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import bigbridge.mbds.unice.fr.bigbridge.fragment.MyProfileFragment;

public class MainActivity extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        createProfileFragement();
    }

    private void createProfileFragement(){
        MyProfileFragment myProfileFragment = new MyProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        myProfileFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main,myProfileFragment);
        fragmentTransaction.commit();
    }
}
