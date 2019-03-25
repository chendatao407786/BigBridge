package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import bigbridge.mbds.unice.fr.bigbridge.R;
import bigbridge.mbds.unice.fr.bigbridge.api.IRisk;
import bigbridge.mbds.unice.fr.bigbridge.api.IStation;
import bigbridge.mbds.unice.fr.bigbridge.api.IUser;
import bigbridge.mbds.unice.fr.bigbridge.api.RetrofitInstance;
import bigbridge.mbds.unice.fr.bigbridge.util.PreferencesManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLocation;
    private TextView mShowResult;
    private View showAqi;
    PreferencesManager preferencesManager = PreferencesManager.getInstance(getActivity());

    int mStationId;

    private IStation stationApi = RetrofitInstance.getRetrofitInstanceForStation().create(IStation.class);
    private IRisk riskApi = RetrofitInstance.getRetrofitInstance().create(IRisk.class);
    private int aqi;
    private String hasRisk;
    String TAG = "ResultFragement";

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        mShowResult = view.findViewById(R.id.result);
        showAqi = view.findViewById(R.id.circle_aqi);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        getLastKnownLocation();
        return view;
    }

    private void getLastKnownLocation() {
        Log.i(TAG, "GET location");
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    mLocation = task.getResult();
                    double latitude = mLocation.getLatitude();
                    double longitude = mLocation.getLongitude();
                    Log.i(TAG,latitude+" and "+ longitude);
                    getStationId(mLocation);
                }
            }
        });
    }
    private void getStationId(Location location){
//        Call<ResponseBody> call = stationApi.getStation(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()),1,1);
        Call<ResponseBody> call = stationApi.getStation("1");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
//                    Log.i(TAG,response.body().toString());
                    JSONObject res = new JSONObject(response.body().string());
                    JSONObject station = res.getJSONArray("d").getJSONObject(0);
                    String stationName = station.getString("nlo");
                    aqi = station.getInt("v");
                    TextView textAqi = showAqi.findViewById(R.id.textAqi);
                    textAqi.setText(String.valueOf(aqi));
                    Log.i(TAG,String.valueOf(aqi));
                    switch (stationName){
                        case "Nice Aeroport, PACA":
                            mStationId = 1;
                            break;
                        case "Contes, PACA":
                            mStationId = 2;
                            break;
                        case "Nice Arson, PACA":
                            mStationId = 3;
                            break;
                        case "Antibes Jean Moulin, PACA":
                            mStationId = 4;
                            break;
                        case "Promenade Des Anglais, PACA":
                            mStationId = 5;
                            break;
                        case "Peillon, PACA":
                            mStationId = 6;
                            break;
                        default:
                            mStationId = -1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getRisk();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void getRisk() {
        String postcode = preferencesManager.getCodepostal();
        int drinking = preferencesManager.getDrinking().equals("Yes") ? 1 : 0;
        int smoking = preferencesManager.getSmoking().equals("Yes") ? 1 : 0;
        int gender = preferencesManager.getSex().equals("Yes") ? 1 : 0;
        int weight = preferencesManager.getweight();
        int height = preferencesManager.getHeight();
        int bmi = weight / (height * height/100/100);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        int age = Period.between(LocalDate.parse(preferencesManager.getDdn(),formatter), LocalDate.now()).getYears();
        Log.i(TAG,String.valueOf(age));
        Call<ResponseBody> call = riskApi.getRisk(bmi, mStationId, postcode, drinking, smoking, gender, weight, height, age);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                TextView resultText = showAqi.findViewById(R.id.notification);
                CircularProgressBar circularProgressBar = showAqi.findViewById(R.id.circlePB);
                try {
                    hasRisk = response.body().string();
                    if(aqi>50){
                        circularProgressBar.setColor(ContextCompat.getColor(getContext(),R.color.gold));
                        if(hasRisk.equals("true")){
                            resultText.setText("Be careful "+preferencesManager.loadUsername()+", the environment around your might be bad for your health! Avoid to go outside");
                        }else {
                            resultText.setText("Be careful "+preferencesManager.loadUsername()+", it's not a good day to do sport outside today");
                        }
                    }else {
                        circularProgressBar.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
                        resultText.setText("It's a nice day "+preferencesManager.loadUsername()+", let's enjoy it!");
                    }
                    Log.i(TAG,hasRisk);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
