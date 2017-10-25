package home.rxjavatest.rest;


import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("WeakerAccess")
public class Manager {

    private static final String REAL_URL = "https://api.privatbank.ua";

    private Retrofit retrofitInstance;

    private Service payService;

    public Manager() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.d("TAG", message));

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        retrofitInstance = new Retrofit.Builder()
                .baseUrl(REAL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    Retrofit getRetrofitInstance() {
        return retrofitInstance;
    }

    public Service getPayService() {

        if (payService == null) {
            payService = new Service(retrofitInstance.create(Api.class));
        }
        return payService;
    }
}
