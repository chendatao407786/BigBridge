package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.content.SharedPreferences;
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
import android.widget.ProgressBar;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

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
    private ProgressBar mProgressBar;
    private CircularProgressBar circularProgressBar;
//    PreferencesManager preferencesManager = PreferencesManager.getInstance(getContext());
//    private IFitbit fitbitApi = RetrofitInstance.getRetrofitInstanceForFitbit().create(IFitbit.class);
    public WellBeingFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_well_being, container, false);
        circularProgressBar = view.findViewById(R.id.circularProgressBar);
        test(circularProgressBar);
//        boolean is_auth = preferencesManager.have_authorization();
//        if(is_auth == false){
//            connectFitbit();
//            fetchData();
//        }else{
//            fetchData();
//        }
        return view;
    }
//    private void connectFitbit(){
//        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//        CustomTabsIntent customTabsIntent = builder.build();
//        customTabsIntent = new CustomTabsIntent.Builder(mCustomTabsSession)
//                .setToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))
//                .setShowTitle(true)
//                .build();
//
//        String url =
//                "https://www.fitbit.com/oauth2/authorize?" +
//                "response_type=token" +
//                "&client_id=22DJNL" +
//                "&expires_in=2592000" +
//                "&scope=activity%20nutrition%20heartrate%20location%20nutrition%20profile%20settings%20sleep%20social%20weight" +
//                "&redirect_uri=fitbittester://logincallback" +
//                "&prompt=login";
//        customTabsIntent.launchUrl(getContext(), Uri.parse(url));
//    }
//    private void fetchData(){
//        Log.i(TAG,preferencesManager.getFibitToken());
//        Call<ResponseBody> call =  fitbitApi.getHeartRate(preferencesManager.getClientID(),preferencesManager.get_token_type()+" "+preferencesManager.getFibitToken());
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try{
//                    Log.i(TAG,response.body().string());
//                    JSONArray jsonArray = new JSONArray(response.body().string());
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.i(TAG,t.getMessage());
//            }
//            });
//        }
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
    private void test(CircularProgressBar circularProgressBar){
//        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.progressBarColor));
//        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.backgroundProgressBarColor));
//        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.progressBarWidth));
//        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.backgroundProgressBarWidth));
        int animationDuration = 2000; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(65, animationDuration); // Default duration = 1500ms
    }
}
