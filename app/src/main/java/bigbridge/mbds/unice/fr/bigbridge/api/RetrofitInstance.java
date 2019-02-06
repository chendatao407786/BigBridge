package bigbridge.mbds.unice.fr.bigbridge.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
//    private static final String BASE_URL = "http://10.0.2.2:52423/api/";
//    private static final String BASE_URL = "http://192.168.0.122:52423/api/";
//    private static final String BASE_URL = "http://172.20.10.14:52423/api/";
    private static final String BASE_URL = "http://192.168.0.13:52423/api/";

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
}
