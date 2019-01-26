package bigbridge.mbds.unice.fr.bigbridge;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import bigbridge.mbds.unice.fr.bigbridge.api.IUser;
import bigbridge.mbds.unice.fr.bigbridge.api.RetrofitInstance;
import bigbridge.mbds.unice.fr.bigbridge.api.model.User;
import bigbridge.mbds.unice.fr.bigbridge.fragment.ModifierFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.PersonInfoFragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscriptionActivity extends AppCompatActivity implements PersonInfoFragment.IPersonListener, ModifierFragment.IModifierFragmentListener {
//    private IUser userApi = RetrofitInstance.getRetrofitInstance().create(IUser.class);
    private JSONObject mDataset;
    private Button signUpButton;
    private void initDataSet(){
        ArrayMap am = new ArrayMap();
        am.put("NAME", "");
        am.put("BIRTHDAY", "");
        am.put("SEX", "");
        am.put("WEIGHT", "");
        am.put("PROFESSION", "");
        am.put("SMOKING","");
        am.put("SPORT","");
        am.put("HEART DISEASE","");
        am.put("ASTHMA","");
        mDataset = new JSONObject(am);
        Toast.makeText(this,mDataset.toString(),Toast.LENGTH_LONG).show();
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

//    private void createUser(){
//        User user = new User(mDataset);
//        Call<ResponseBody> call = userApi.createUser(user);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(InscriptionActivity.this, "Created successfully", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(InscriptionActivity.this, "CreateUser error :/\n"+response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(InscriptionActivity.this, "CreateUser error :/\n" + t, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
