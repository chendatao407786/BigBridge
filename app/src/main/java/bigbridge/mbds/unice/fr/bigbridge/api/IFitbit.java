package bigbridge.mbds.unice.fr.bigbridge.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

//public interface IFitbit  {
//    @GET("{client_id}/activities/heart/date/today/1d.json")
//    Call<ResponseBody> getHeartRate(@Path("client_id") String client_id);
//}
public interface IFitbit  {
    @GET("{client_id}/activities/heart/date/today/1d/1min.json")
    Call<ResponseBody> getHeartRate(@Path("client_id") String client_id,@Header("Authorization") String authorization);
}
//public interface IFitbit  {
//    @GET("-/activities/heart/date/2019-03-04/1d/1min/time/12:00/12:10.json")
//    Call<ResponseBody> getHeartRate(@Header("Authorization") String authorization);
//}
