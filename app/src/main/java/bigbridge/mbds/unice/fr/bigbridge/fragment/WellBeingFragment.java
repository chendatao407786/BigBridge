package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bigbridge.mbds.unice.fr.bigbridge.LoginActivity;
import bigbridge.mbds.unice.fr.bigbridge.R;
import bigbridge.mbds.unice.fr.bigbridge.api.IFitbit;
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
public class WellBeingFragment extends Fragment {
    private CustomTabsSession mCustomTabsSession;
    private static String TAG = "WellBeingFragment";
    private View fragment;
    private JSONObject mData;
//    private CircularProgressBar test;
    private View mCalorieProgressBar;
    private View mStepCard;
    private View mFloorCard;
    private View mDistanceCard;
    private View mActiveTimeCard;
    private TextView mDate;
    private ImageButton mLeftButton;
    private ImageButton mRightButton;
    private LocalDate now = LocalDate.now();
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.buttonLeft:
                    now = now.plusDays(-1);
                    mDate.setText(now.toString());
                    mRightButton.setVisibility(View.VISIBLE);
                    fetchData(now.toString());
                    Log.i(TAG,now.toString());
                    break;
                case R.id.buttonRight:
                    now = now.plusDays(+1);
                    if(now.toString().equals(LocalDate.now().toString())){
                        mDate.setText("today");
                    }else {
                        mDate.setText(now.toString());
                    }
                    if(now.toString().equals(LocalDate.now().toString())){
//                        ImageButton imageButton = v.findViewById(R.id.buttonRight);
                        mRightButton.setVisibility(View.INVISIBLE);
                    }
                    fetchData(now.toString());
                    Log.i(TAG,now.toString());
                    break;
            }
        }
    };
    PreferencesManager preferencesManager = PreferencesManager.getInstance(getContext());
    private IFitbit fitbitApi = RetrofitInstance.getRetrofitInstanceForFitbit().create(IFitbit.class);
    public WellBeingFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_well_being, container, false);
        mCalorieProgressBar = view.findViewById(R.id.circle);
        generateCalorieProgressBar(mCalorieProgressBar,0.0,0.0,view);
        mStepCard = view.findViewById(R.id.stepCard);
        mFloorCard = view.findViewById(R.id.floorCard);
        mDistanceCard = view.findViewById(R.id.distanceCard);
        mActiveTimeCard = view.findViewById(R.id.activityCard);
        mDate = view.findViewById(R.id.date);
        fragment = view;
        mLeftButton = view.findViewById(R.id.buttonLeft);
        mRightButton = view.findViewById(R.id.buttonRight);
        mRightButton.setVisibility(View.INVISIBLE);
        mLeftButton.setOnClickListener(onClickListener);
        mRightButton.setOnClickListener(onClickListener);
//        test = circle.findViewById(R.id.circlePB);
//        generateCircleProgressBar(CalorieProgressBar,65,"2000 Calorie",R.drawable.ic_calorie);
        generateCircleProgressBar(mStepCard,"Steps",0,R.drawable.ic_step,R.color.darkBlue,completed(0.0,0.0));
        generateCircleProgressBar(mFloorCard,"Floors",0,R.drawable.ic_floor,R.color.gold,completed(0.0,0.0));
        generateCircleProgressBar(mDistanceCard,"Distance",0,R.drawable.ic_distance,R.color.sunset,completed(0.0,0.0));
        generateCircleProgressBar(mActiveTimeCard,"Activity Time",0,R.drawable.ic_running,R.color.turquoise,completed(0.0,0.0));
//        generateCircleProgressBar(mProgressBarFloor,90,"9 floors",R.drawable.ic_floor);
//        generateCircleProgressBar(mProgressBarDistance,35,"1.3 km",R.drawable.ic_distance);
//        generateCircleProgressBar(mProgressBarActiveTime,15,"7 mins",R.drawable.ic_running);
//        generateCircleProgressBar(circularProgressBar);
        boolean is_auth = preferencesManager.have_authorization();
        if(is_auth == false){
            connectFitbit();
            fetchData("today");
        }else{
            fetchData("today");
        }
        return view;
    }
    private void connectFitbit(){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent = new CustomTabsIntent.Builder(mCustomTabsSession)
                .setToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))
                .setShowTitle(true)
                .build();
        String url =
                "https://www.fitbit.com/oauth2/authorize?" +
                "response_type=token" +
                "&client_id=22DJNL" +
                "&expires_in=2592000" +
                "&scope=activity%20nutrition%20heartrate%20location%20nutrition%20profile%20settings%20sleep%20social%20weight" +
                "&redirect_uri=fitbittester://logincallback" +
                "&prompt=login";
        customTabsIntent.launchUrl(getContext(), Uri.parse(url));
    }
    private void fetchData(String date){
        Log.i(TAG,preferencesManager.getFibitToken());
        Call<ResponseBody> call =  fitbitApi.getActivitiesSummary(preferencesManager.getClientID(),date,preferencesManager.get_token_type()+" "+preferencesManager.getFibitToken());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    mData = prepareData(jsonObject);
                    generateCalorieProgressBar(mCalorieProgressBar,mData.getJSONObject("calorie").getDouble("goal"),mData.getJSONObject("calorie").getDouble("completed"),fragment);
                    generateCircleProgressBar(mStepCard,"Steps",mData.getJSONObject("step").getDouble("completed"),R.drawable.ic_step,R.color.darkBlue,completed(mData.getJSONObject("step").getDouble("goal"),mData.getJSONObject("step").getDouble("completed")));
                    generateCircleProgressBar(mFloorCard,"Floors",mData.getJSONObject("floor").getDouble("completed"),R.drawable.ic_floor,R.color.gold,completed(mData.getJSONObject("floor").getDouble("goal"),mData.getJSONObject("floor").getDouble("completed")));
                    generateCircleProgressBar(mDistanceCard,"Distance",mData.getJSONObject("distance").getDouble("completed"),R.drawable.ic_distance,R.color.sunset,completed(mData.getJSONObject("distance").getDouble("goal"),mData.getJSONObject("distance").getDouble("completed")));
                    generateCircleProgressBar(mActiveTimeCard,"Activity Time",mData.getJSONObject("activityMinutes").getDouble("completed"),R.drawable.ic_running,R.color.turquoise,completed(mData.getJSONObject("activityMinutes").getDouble("goal"),mData.getJSONObject("activityMinutes").getDouble("completed")));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG,t.getMessage());
            }
            });
        }
//    private void progressBarAnnimate(final ProgressBar progressBar){
//        final int level = progressBar.getProgress();
//        Thread thread = new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                for(int i = 0; i<= level;i++){
//                    try{
//                        sleep(10);
//                        progressBar.setProgress(i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        thread.start();
//    }
    private void generateCircleProgressBar(View card,String name,double value,int backgroundImage,int bkColor,int missionCompleted){
//        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.progressBarColor));
//        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
//        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
//        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        View progressBar = card.findViewById(R.id.progressBar);
        CircularProgressBar circularProgressBar = progressBar.findViewById(R.id.circlePB);
        View textBlock = card.findViewById(R.id.textBlock);
        TextView text_name = textBlock.findViewById(R.id.name);
        text_name.setTextColor(ContextCompat.getColor(getContext(),bkColor));
        TextView text_value = textBlock.findViewById(R.id.value);
        text_name.setText(name);
        text_value.setText(String.valueOf(value));
        View borderLeft = textBlock.findViewById(R.id.borderLeft);
        borderLeft.setBackgroundColor(ContextCompat.getColor(getContext(),bkColor));
        ImageView imageView = progressBar.findViewById(R.id.bkImage);
        imageView.setColorFilter(ContextCompat.getColor(getContext(),bkColor));
        imageView.setImageResource(backgroundImage);
        TextView completed = card.findViewById(R.id.completedPercent);
        completed.setText(String.valueOf(missionCompleted)+"%");
        int animationDuration = 2000;
        circularProgressBar.setColor(ContextCompat.getColor(getContext(),bkColor));
        circularProgressBar.setProgressWithAnimation(missionCompleted, animationDuration); // Default duration = 1500ms
    }
    private void generateCalorieProgressBar(View progressBar,Double goal,Double out,View view){
        TextView text_goal = view.findViewById(R.id.goal);
        TextView text_out = view.findViewById(R.id.out);
        text_goal.setText(String.valueOf(completed(goal,out))+"%");
        text_out.setText(String.valueOf(out));
        CircularProgressBar circularProgressBar = progressBar.findViewById(R.id.circlePB);
        circularProgressBar.setProgressWithAnimation(completed(goal,out),2000);
    }
    private int completed(Double goal,Double completed){
        return (int) ((completed/goal)*100);
    }
    private JSONObject prepareData(JSONObject jsonObject){
        JSONObject res= new JSONObject();
        try {
            JSONObject goals = jsonObject.getJSONObject("goals");
            double goal_activeMinutes = goals.getDouble("activeMinutes");
            double goal_caloriesOut = goals.getDouble("caloriesOut");
            double goal_distance = goals.getDouble("distance");
            double goal_floors = goals.getDouble("floors");
            double goal_steps = goals.getDouble("steps");
            JSONObject summary = jsonObject.getJSONObject("summary");
            double caloriesOut = summary.getDouble("caloriesOut");
            double distance = summary.getJSONArray("distances").getJSONObject(0).getDouble("distance");
            double floors = summary.getDouble("floors");
            double steps = summary.getDouble("steps");
            double fairlyActiveMinutes = summary.getDouble("fairlyActiveMinutes");
            res.put("calorie",createObjet(goal_caloriesOut,caloriesOut));
            res.put("floor",createObjet(goal_floors,floors));
            res.put("distance",createObjet(goal_distance,distance));
            res.put("step",createObjet(goal_steps,steps));
            res.put("activityMinutes",createObjet(goal_activeMinutes,fairlyActiveMinutes));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }
    private JSONObject createObjet(Double goal,Double complete){
        JSONObject activity = new JSONObject();
        try {
            activity.put("goal",goal);
            activity.put("completed",complete);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return activity;
    }
}
