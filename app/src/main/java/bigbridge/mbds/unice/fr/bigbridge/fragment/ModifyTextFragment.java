package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import bigbridge.mbds.unice.fr.bigbridge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyTextFragment extends Fragment {


    public ModifyTextFragment() {
        // Required empty public constructor
    }

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.homeAsUp:
                    getActivity().getSupportFragmentManager().popBackStack();
                    break;
                default:
                    return;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_text, container, false);
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) container.getContext()).getSupportActionBar();
        actionBar.setTitle("Name");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        return view;
    }



}
