package home.rxjavatest.rest;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface Api {

    @POST("api/pay-sale")
    Observable<Response> paySale(@Header("Content-Type") String content_type, @Body Map<String, String> body);
}
