package bigbridge.mbds.unice.fr.bigbridge.api;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static Retrofit retrofitFitbit;
    private static Retrofit retrofitStation;
    private static final String BASE_URL = "http://192.168.0.122:8080/api/";
//    private static final String BASE_URL = "http://10.154.97.218:55555/api/";
//    private static final String BASE_URL = "http://172.20.10.14:8080/api/";
//    private static final String BASE_URL = "http://192.168.0.10:8080/api/";
    private static final String BASE_URL_FITBIT="https://api.fitbit.com/1/user/";
    private static final String BASE_URL_STATION="https://api.waqi.info/mapq/";
    private static final String TAG = "RestrofitInstance";
    /**
     * Create an instance of Retrofit object
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstanceForFitbit(){
        if (retrofitFitbit == null) {
            retrofitFitbit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_FITBIT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitFitbit;
    }
    public static Retrofit getRetrofitInstanceForStation(){
        if (retrofitStation == null) {
            retrofitStation = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_STATION)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        Log.i(TAG,retrofitStation.baseUrl().toString());
        return retrofitStation;
    }
}
