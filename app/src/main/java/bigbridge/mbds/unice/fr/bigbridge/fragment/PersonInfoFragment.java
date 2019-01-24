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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import bigbridge.mbds.unice.fr.bigbridge.R;

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
    public PersonInfoFragment() {
        // Required empty public constructor
    }
    public interface IPersonListener{
        void sendMsgs(String value,String v,Fragment fragment);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_info, container, false);
        initDataSet();
        initializeAdapter(mDataset);
        Toast.makeText(getContext(),mDataset.toString(),Toast.LENGTH_LONG).show();
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
}
