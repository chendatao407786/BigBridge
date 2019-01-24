package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import bigbridge.mbds.unice.fr.bigbridge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyTextFragment extends Fragment {
    String mTitle;
    String mValue;
    private TextView mTitleActionBar;
    private Toolbar mToolbar;
    private EditText mEditText;
    private ActionBar mActionBar;
    private IModifyTextFragmentListener iModifyTextFragmentListener;

    public ModifyTextFragment() {
    }

    public interface IModifyTextFragmentListener {
        void sentData(String name, String value);
    }
//    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.homeAsUp:
//                    getActivity().getSupportFragmentManager().popBackStack();
//                    iModifyTextFragmentListener.sentData(mTitle,mEditText.getText().toString());
//                    Toast.makeText(getActivity(),"B back to ins",Toast.LENGTH_LONG);
//                    break;
//                default:
//                    return;
//            }
//        }
//    };

    public void update(String name, String value) {
        mTitle = name;
        mValue = value;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_text, container, false);
        mToolbar = view.findViewById(R.id.my_toolbar);
        ((AppCompatActivity) container.getContext()).setSupportActionBar(mToolbar);
        mActionBar = ((AppCompatActivity) container.getContext()).getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().popBackStack();
                iModifyTextFragmentListener.sentData(mTitle,mEditText.getText().toString());
            }
        });
        mActionBar.setDisplayShowTitleEnabled(false);
        mTitleActionBar = view.findViewById(R.id.action_bar_title);
        mTitleActionBar.setText(mTitle);
        mEditText = view.findViewById(R.id.modify_content);
        mEditText.setText(mValue);
        return view;
    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.homeAsUp:
//                getActivity().getSupportFragmentManager().popBackStack();
//                Toast.makeText(getActivity(),"B back to ins",Toast.LENGTH_LONG);
//                iModifyTextFragmentListener.sentData(mTitle, mEditText.getText().toString());
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PersonInfoFragment.IPersonListener) {
            iModifyTextFragmentListener = (ModifyTextFragment.IModifyTextFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement method");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iModifyTextFragmentListener = null;
    }

}
