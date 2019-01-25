package bigbridge.mbds.unice.fr.bigbridge;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;

import org.json.JSONException;
import org.json.JSONObject;

import bigbridge.mbds.unice.fr.bigbridge.fragment.ModifierFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.PersonInfoFragment;

public class InscriptionActivity extends AppCompatActivity implements PersonInfoFragment.IPersonListener, ModifierFragment.IModifierFragmentListener {

    JSONObject mDataset;

    private void initDataSet() {
        ArrayMap am = new ArrayMap();
        am.put("Name", "CHEN Datao");
        am.put("Birthday", "26/11/1990");
        am.put("Sex", "Men");
        am.put("Weight", "80");
        am.put("Profession", "IT");
        mDataset = new JSONObject(am);
    }

    private PersonInfoFragment personInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        initDataSet();
        personInfoFragment = new PersonInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("person", mDataset.toString());
        personInfoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.inscription, personInfoFragment);
        transaction.commit();
    }

    @Override
    public void sendMsgs(String name, String v, ModifierFragment modifierFragment) {
        modifierFragment.update(name, v);
    }

    @Override
    public void sentData(String name, String value) {
        try {
            mDataset.put(name, value);
            personInfoFragment = new PersonInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("person", mDataset.toString());
            personInfoFragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.inscription, personInfoFragment);
            transaction.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
