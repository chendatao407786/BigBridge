package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import bigbridge.mbds.unice.fr.bigbridge.InscriptionActivity;
import bigbridge.mbds.unice.fr.bigbridge.R;
import bigbridge.mbds.unice.fr.bigbridge.api.IUser;
import bigbridge.mbds.unice.fr.bigbridge.api.RetrofitInstance;
import bigbridge.mbds.unice.fr.bigbridge.api.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonInfoFragment extends Fragment {
    private IPersonListener iPersonListener;
    private View view;
    private RecyclerView mInformationList;
    private RecyclerView.Adapter mPersonInfoAdapter;
    private JSONObject mDataset;
    private LinearLayoutManager mLayoutManager;
    private Button signUpButton;
    private IUser userApi = RetrofitInstance.getRetrofitInstance().create(IUser.class);
    public PersonInfoFragment() {
        // Required empty public constructor
    }
    public interface IPersonListener{
        void sendMsgs(String value,String v,ModifierFragment modifierFragment);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_info, container, false);
        initDataSet();
        initializeAdapter(mDataset);
        signUp(view);
//        Toast.makeText(getContext(),mDataset.toString(),Toast.LENGTH_LONG).show();
        return view;
    }
    private void initDataSet(){
        Bundle bundle = getArguments();
        try {
            mDataset = new JSONObject(bundle.getString("person"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initializeAdapter(JSONObject mDataset) {
        mInformationList = view.findViewById(R.id.informationList);
        mPersonInfoAdapter = new PersonInfoAdapter(getContext(),mDataset,iPersonListener);
        mInformationList.setAdapter(mPersonInfoAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mInformationList.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration =  new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        mInformationList.addItemDecoration(dividerItemDecoration);
    }

    private void signUp(View view){
        signUpButton = view.findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IPersonListener){
            iPersonListener = (IPersonListener)context;
        }else{
            throw new RuntimeException(context.toString()+"must implement method");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iPersonListener = null;
    }

    private void createUser(){
        User user = new User(mDataset);
        Call<ResponseBody> call = userApi.createUser(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Created successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "CreateUser error :/\n"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "CreateUser error :/\n" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
