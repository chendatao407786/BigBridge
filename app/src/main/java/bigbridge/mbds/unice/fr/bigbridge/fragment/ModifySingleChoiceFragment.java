package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bigbridge.mbds.unice.fr.bigbridge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifySingleChoiceFragment extends ModifierFragment {
    String name1;
    String name2;
    Button mButtonA;
    Button mButtonB;

    public ModifySingleChoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_single_choice, container, false);
        initToolBar(view, container);
        Bundle bundle = getArguments();
        name1 = bundle.getString("name1");
        name2 = bundle.getString("name2");
        initModifyContent(view);
        return view;
    }
    private void initModifyContent(View view) {
        mButtonA = view.findViewById(R.id.a);
        mButtonB = view.findViewById(R.id.b);
        setButtonTitle(name1,name2);
        if(mButtonA.getText().toString().equals(mValue)){
            mButtonA.setBackgroundResource(R.color.colorAccent);
            mButtonB.setBackgroundColor(Color.GRAY);
        }else{
            mButtonB.setBackgroundResource(R.color.colorAccent);
            mButtonA.setBackgroundColor(Color.GRAY);
        }
        mButtonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue = mButtonA.getText().toString();
                mButtonA.setBackgroundResource(R.color.colorAccent);
                mButtonB.setBackgroundColor(Color.GRAY);
            }
        });
        mButtonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValue = mButtonB.getText().toString();
                mButtonB.setBackgroundResource(R.color.colorAccent);
                mButtonA.setBackgroundColor(Color.GRAY);
            }
        });
    }


    private void setButtonTitle(String name1, String name2) {
        mButtonA.setText(name1);
        mButtonB.setText(name2);
    }


}
