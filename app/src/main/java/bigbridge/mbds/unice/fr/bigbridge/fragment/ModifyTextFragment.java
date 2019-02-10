package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bigbridge.mbds.unice.fr.bigbridge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyTextFragment extends ModifierFragment {
    protected TextView mTextView;
    public ModifyTextFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_text, container, false);
        initToolBar(view,container);
        initModifyContent(view);
        return view;
    }

    private void initModifyContent( View view){
        mTextView = view.findViewById(R.id.modify_content);
        mTextView.setText(mValue);
        mTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mValue = mTextView.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
