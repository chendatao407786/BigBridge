package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import bigbridge.mbds.unice.fr.bigbridge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyWeightFragment extends ModifierFragment {

    NumberPicker mNumberPicker;
    public ModifyWeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_weight, container, false);
        initToolBar(view,container);
        initModifyContent(view);
        return view;
    }
    private void initModifyContent(View view){
        mNumberPicker = view.findViewById(R.id.numberPicker);
        mNumberPicker.setMinValue(10);
        mNumberPicker.setMaxValue(100);
        mNumberPicker.setValue(Integer.parseInt(mValue));
        mNumberPicker.setWrapSelectorWheel(false);
        mNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mValue = String.valueOf(newVal);
            }
        });
    }

}
