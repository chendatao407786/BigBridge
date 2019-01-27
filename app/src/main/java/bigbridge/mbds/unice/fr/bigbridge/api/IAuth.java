package bigbridge.mbds.unice.fr.bigbridge.api;

import bigbridge.mbds.unice.fr.bigbridge.api.model.Auth;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuth {
    @POST("auth")
    Call<ResponseBody> createAuth(@Body Auth auth);

    @POST("auth/login")
    Call<ResponseBody> login(@Body Auth auth);
}
