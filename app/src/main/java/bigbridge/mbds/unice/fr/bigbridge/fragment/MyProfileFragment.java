package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import bigbridge.mbds.unice.fr.bigbridge.PersonalInfoActivity;
import bigbridge.mbds.unice.fr.bigbridge.R;
import bigbridge.mbds.unice.fr.bigbridge.api.IUser;
import bigbridge.mbds.unice.fr.bigbridge.api.RetrofitInstance;
import bigbridge.mbds.unice.fr.bigbridge.util.PreferencesManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {
    private IUser userApi = RetrofitInstance.getRetrofitInstance().create(IUser.class);
    PreferencesManager preferencesManager = PreferencesManager.getInstance(getContext());
    private String username;
    private RecyclerView myProfileDetails;
    private RecyclerView.Adapter mMyProfileAdapter;
    private LinearLayoutManager mLayoutManager;
    private JSONObject mDataset;
    private ImageButton modifyButton;
    private View header;
    private TextView email;
    private TextView usernameTextView;
    private String TAG = "MyProfileFragment";
    public MyProfileFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        username = preferencesManager.loadUsername();
        header = view.findViewById(R.id.profile_header);
        usernameTextView = header.findViewById(R.id.username);
        email = header.findViewById(R.id.email);
        usernameTextView.setText(username);
        email.setText(PreferencesManager.getInstance(getContext()).loadEmail());
        myProfileDetails = view.findViewById(R.id.my_profile_details);
        initDataset();
        modifyButton = view.findViewById(R.id.modify_button);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PersonalInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initDataset(){
        Call<ResponseBody> call = userApi.getUser(username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    mDataset = jsonArray.getJSONObject(0);
                    Log.i(TAG,mDataset.toString());
                    preferencesManager.setWeight(mDataset.getInt("WEIGHT"));
                    preferencesManager.setHeight(mDataset.getInt("HEIGHT"));
                    preferencesManager.setSmoking(mDataset.getString("SMOKING"));
                    preferencesManager.setSex(mDataset.getString("SEX"));
                    preferencesManager.setCodepostal(mDataset.getString("POST CODE"));
                    preferencesManager.setDrinking(mDataset.getString("DRINKING"));
                    preferencesManager.setDdn(mDataset.getString("BIRTHDAY"));
                    Log.i(TAG,String.valueOf(mDataset.getInt("WEIGHT")));
                    Log.i(TAG,String.valueOf(mDataset.getInt("HEIGHT")));
//                    Log.i(TAG,"gender:"+preferencesManager.getSex());


                    initializeAdapter(mDataset);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {}
        });
    }

    private void initializeAdapter(JSONObject mDataset) {
        mMyProfileAdapter = new MyProfileAdapter(getContext(),mDataset);
        myProfileDetails.setAdapter(mMyProfileAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        myProfileDetails.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration =  new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        myProfileDetails.addItemDecoration(dividerItemDecoration);
    }

}
