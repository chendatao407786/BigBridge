package bigbridge.mbds.unice.fr.bigbridge.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import bigbridge.mbds.unice.fr.bigbridge.R;

public class ModifierFragment extends Fragment {

    IModifierFragmentListener iModifierFragmentListener;
    String mTitle;
    String mValue;
    private TextView mTitleActionBar;
    private Toolbar mToolbar;
    protected EditText mEditText;
    private ActionBar mActionBar;

    public interface IModifierFragmentListener {
        void sentData(String name, String value);
    }
    protected void initToolBar(View view, ViewGroup container){
        mToolbar = view.findViewById(R.id.my_toolbar);
        ((AppCompatActivity) container.getContext()).setSupportActionBar(mToolbar);
        mActionBar = ((AppCompatActivity) container.getContext()).getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iModifierFragmentListener.sentData(mTitle,mEditText.getText().toString());
            }
        });
        mActionBar.setDisplayShowTitleEnabled(false);
        mTitleActionBar = view.findViewById(R.id.action_bar_title);
        mTitleActionBar.setText(mTitle);
    }

    protected void initModifyContent(View view){
        mEditText = view.findViewById(R.id.modify_content);
        mEditText.setText(mValue);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void update(String name, String value) {
        mTitle = name;
        mValue = value;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PersonInfoFragment.IPersonListener) {
            iModifierFragmentListener = (ModifierFragment.IModifierFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement method");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iModifierFragmentListener = null;
    }
}
