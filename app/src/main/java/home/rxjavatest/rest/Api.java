package home.rxjavatest.rest;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface Api {

    @Headers({"Accept: application/json"})
    @GET("/p24api/pboffice")
    Observable<List<PBBransches>> pbiffice(@Query("address") String address, @Query("city") String city );
}
