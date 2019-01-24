package bigbridge.mbds.unice.fr.bigbridge;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import bigbridge.mbds.unice.fr.bigbridge.fragment.ModifyTextFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.PersonInfoFragment;
import bigbridge.mbds.unice.fr.bigbridge.fragment.SelectWeightFragment;

public class InscriptionActivity extends AppCompatActivity implements PersonInfoFragment.IPersonListener,ModifyTextFragment.IModifyTextFragmentListener{
//    int mYear;
//    int mMonth;
//    int mDay;
//    TextView mBirthday;
//    ImageView mCalendrierBtn;
//    TextView mWeight;

    JSONObject mDataset;

    private void initDataSet(){
        ArrayMap am = new ArrayMap();
        am.put("Name","CHEN Datao");
        am.put("Birthday","26/11/1990");
        am.put("Sex","Men");
        am.put("Weight","80");
        am.put("Profession","IT");
        mDataset=new JSONObject(am);
    }
    private PersonInfoFragment personInfoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        initDataSet();
        personInfoFragment = new PersonInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("person",mDataset.toString());
        personInfoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.inscription,personInfoFragment);
        transaction.commit();
//        Calendar ca = Calendar.getInstance();
//        mYear = ca.get(Calendar.YEAR);
//        mMonth = ca.get(Calendar.MONTH);
//        mDay = ca.get(Calendar.DAY_OF_MONTH);
//        mBirthday = findViewById(R.id.birthday);
//        mCalendrierBtn = findViewById(R.id.calendrier);
//        mCalendrierBtn.setOnClickListener(mOnclickListener());
//        mWeight = findViewById(R.id.weight);
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                getSupportFragmentManager().popBackStack();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void sendMsgs(String name,String v,Fragment fragment) {
        if(fragment instanceof ModifyTextFragment){
            ((ModifyTextFragment) fragment).update(name,v);
        }
    }

    @Override
    public void sentData(String name, String value) {
//        if(personInfoFragment instanceof PersonInfoFragment){
//            personInfoFragment.updateDataSet(name,value);
//        }
        try {
            mDataset.put(name,value);
            personInfoFragment = new PersonInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("person",mDataset.toString());
            personInfoFragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.inscription,personInfoFragment);
            transaction.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


//    private View.OnClickListener mOnclickListener() {
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.calendrier:
//                        new DatePickerDialog(InscriptionActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
//                        break;
//                    case R.id.weight:
//                        SelectWeightFragment selectWeightFragment = new SelectWeightFragment();
//                        selectWeightFragment.show(getSupportFragmentManager(),"dialog");
//                        break;
//                }
//
//            }
//        };
//        return listener;
//    }
//
//    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            mYear = year;
//            mMonth = month;
//            mDay = dayOfMonth;
//            String date;
//            String sMonth = String.valueOf(month + 1);
//            String sDay = String.valueOf(dayOfMonth);
//            if (mMonth + 1 < 10) {
//                sMonth = "0" + sMonth;
//            }
//            if (mDay < 10) {
//                sDay = 0 + sDay;
//            }
//            date = new StringBuffer().append(sDay).append("/").append(sMonth).append("/").append(mYear).toString();
//            mBirthday.setText(date);
//        }
//    };
}
