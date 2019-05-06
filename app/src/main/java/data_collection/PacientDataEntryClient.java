package data_collection;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PacientDataEntryClient {

    @POST("/send_data")
    Call<GenericMessage>  sendDailyData(@Body PacientDailyInfo info);

}
