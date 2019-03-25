package bigbridge.mbds.unice.fr.bigbridge.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IStation {
//    @GET("?n=1&geo=1/{latitude}/{longitude}")
    @GET("nearest")
//    Call<ResponseBody> getStation();
//    Call<ResponseBody> getStation(@Path("latitude") String latitude, @Path("longitude") String longitude, @Query("n") int nbStation, @Query("geo") int geo);
    Call<ResponseBody> getStation(@Query("n") String nbStation);
}
