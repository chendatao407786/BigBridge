package bigbridge.mbds.unice.fr.bigbridge.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRisk {
//    @GET("risk?bmi={bmi}&station={station}&postalcode={postalcode}&drinking={drinking}&smoking={smoking}&gender={gender}&weight={weight}&height={height}&age={age}")
    @GET("risk")
    Call<ResponseBody> getRisk(
            @Query("bmi") int bmi,
            @Query("station") int station,
            @Query("postalcode") String postalcode,
            @Query("drinking") int drinking,
            @Query("smoking") int smoking,
            @Query("gender") int gender,
            @Query("weight") int weight,
            @Query("height") int height,
            @Query("age") int age
            );
//    Call<ResponseBody> getRisk(
//            @Path("bmi") Integer bmi,
//            @Path("station") String station,
//            @Path("postalcode") String postalcode,
//            @Path("drinking") String drinking,
//            @Path("smoking") String smoking,
//            @Path("gender") String gender,
//            @Path("weight") String weight,
//            @Path("height") String height,
//            @Path("age") String age
//            );
}
