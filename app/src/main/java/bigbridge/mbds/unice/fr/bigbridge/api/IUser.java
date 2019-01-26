package bigbridge.mbds.unice.fr.bigbridge.api;
import bigbridge.mbds.unice.fr.bigbridge.api.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IUser {
    @GET("user/{id}")
    Call<ResponseBody> getUser(@Path("id") String userid);

    @POST("user")
    Call<ResponseBody> createUser(@Body User user);
}
