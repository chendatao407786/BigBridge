package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import bigbridge.mbds.unice.fr.bigbridge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonInfoFragment extends Fragment {

    private View view;
    private RecyclerView mInformationList;
    private RecyclerView.Adapter mPersonInfoAdapter;
    private JSONObject mDataset;
    private LinearLayoutManager mLayoutManager;
    public PersonInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_info, container, false);
        initDataSet();
        return view;
    }
    private void initDataSet(){
        ArrayMap am = new ArrayMap();
        am.put("Name","CHEN Datao");
        am.put("Birthday","26/11/1988");
        am.put("Sex","Men");
        am.put("Weight","80");
        mDataset=new JSONObject(am);
        initializeAdapter();
    }
    private void initializeAdapter() {
        mInformationList = view.findViewById(R.id.informationList);
        mPersonInfoAdapter = new PersonInfoAdapter(mDataset);
        mInformationList.setAdapter(mPersonInfoAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mInformationList.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration =  new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
//        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.divider));
        mInformationList.addItemDecoration(dividerItemDecoration);
    }

}
