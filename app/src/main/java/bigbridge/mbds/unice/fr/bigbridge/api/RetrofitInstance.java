package bigbridge.mbds.unice.fr.bigbridge.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static Retrofit retrofitFitbit;
    private static final String BASE_URL = "http://10.0.2.2:8080/api/";
//    private static final String BASE_URL = "http://192.168.0.122:8080/api/";
//    private static final String BASE_URL = "http://10.154.97.218:8080/api/";
//    private static final String BASE_URL = "http://192.168.0.11:52423/api/";

    private static final String BASE_URL_FITBIT="https://api.fitbit.com/1/user/";

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
}
