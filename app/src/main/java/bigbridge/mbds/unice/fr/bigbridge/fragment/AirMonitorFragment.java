package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bigbridge.mbds.unice.fr.bigbridge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AirMonitorFragment extends Fragment {


    public AirMonitorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_air_monitor, container, false);
    }

}
