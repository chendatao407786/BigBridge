package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import bigbridge.mbds.unice.fr.bigbridge.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModifyDateFragment extends ModifierFragment {
    int mYear;
    int mMonth;
    int mDay;
    TextView mBirthday;
    ImageView mCalendrierBtn;

    public ModifyDateFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modify_date, container, false);
        initToolBar(view,container);
        initModifyContent(view);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mCalendrierBtn = view.findViewById(R.id.calendrier);
        mCalendrierBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), onDateSetListener, mYear, mMonth, mDay).show();
            }
        });
        return view;
    }
    private void initModifyContent(View view){
        mBirthday = view.findViewById(R.id.modify_content);
        mBirthday.setText(mValue);
        mBirthday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mValue = mBirthday.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            String date;
            String sMonth = String.valueOf(month + 1);
            String sDay = String.valueOf(dayOfMonth);
            if (mMonth + 1 < 10) {
                sMonth = "0" + sMonth;
            }
            if (mDay < 10) {
                sDay = 0 + sDay;
            }
            date = new StringBuffer().append(sDay).append("-").append(sMonth).append("-").append(mYear).toString();
            mBirthday.setText(date);
        }
    };

}
