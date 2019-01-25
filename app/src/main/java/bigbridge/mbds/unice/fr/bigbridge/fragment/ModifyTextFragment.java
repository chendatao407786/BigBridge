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
public class ModifyTextFragment extends ModifierFragment {

    public ModifyTextFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_text, container, false);
        initToolBar(view,container);
        initModifyContent(view);
        return view;
    }
}
