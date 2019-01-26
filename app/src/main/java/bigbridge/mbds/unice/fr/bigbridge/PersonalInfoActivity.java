package bigbridge.mbds.unice.fr.bigbridge;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import bigbridge.mbds.unice.fr.bigbridge.api.IUser;
import bigbridge.mbds.unice.fr.bigbridge.api.RetrofitInstance;
import bigbridge.mbds.unice.fr.bigbridge.fragment.ModifierFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.PersonInfoFragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalInfoActivity extends AppCompatActivity implements PersonInfoFragment.IPersonListener, ModifierFragment.IModifierFragmentListener {
    private IUser userApi = RetrofitInstance.getRetrofitInstance().create(IUser.class);
    JSONObject mDataset;

    private void initDataSet(String id) {
        Call<ResponseBody> call = userApi.getUser(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        Toast.makeText(PersonalInfoActivity.this,response.body().string(),Toast.LENGTH_LONG).show();
                        mDataset = jsonArray.getJSONObject(0);
                        personInfoFragment = new PersonInfoFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("person", mDataset.toString());
                        personInfoFragment.setArguments(bundle);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.inscription, personInfoFragment);
                        transaction.commit();

                    } catch (JSONException e) {
//                        Toast.makeText(InscriptionActivity.this, "get user error 0:/\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Toast.makeText(PersonalInfoActivity.this, "get user error 1:/\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(PersonalInfoActivity.this, "get user error 2:/\n" + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PersonalInfoActivity.this, "Connection failed"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private PersonInfoFragment personInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        initDataSet("26111990");
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
