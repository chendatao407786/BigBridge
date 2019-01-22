package bigbridge.mbds.unice.fr.bigbridge;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import bigbridge.mbds.unice.fr.bigbridge.fragment.SelectWeightFragment;

public class InscriptionActivity extends AppCompatActivity {
    int mYear;
    int mMonth;
    int mDay;
    TextView mBirthday;
    ImageView mCalendrierBtn;
    TextView mWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mBirthday = findViewById(R.id.birthday);
        mCalendrierBtn = findViewById(R.id.calendrier);
        mCalendrierBtn.setOnClickListener(mOnclickListener());
        mWeight = findViewById(R.id.weight);
    }


    private View.OnClickListener mOnclickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.calendrier:
                        new DatePickerDialog(InscriptionActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
                        break;
                    case R.id.weight:
                        SelectWeightFragment selectWeightFragment = new SelectWeightFragment();
                        selectWeightFragment.show(getSupportFragmentManager(),"dialog");
                        break;
                }

            }
        };
        return listener;
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
            date = new StringBuffer().append(sDay).append("/").append(sMonth).append("/").append(mYear).toString();
            mBirthday.setText(date);
        }
    };
}
