package home.rxjavatest.rest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

@SuppressWarnings("WeakerAccess")
public class Service {

    private static final String CONTENT_TYPE = "application/json; charset=UTF-8";

    private Api api;

    public Service(Api api) {
        this.api = api;
    }

    public void getAddress(){

    }

    public Observable<List<PBBransches>> pboffice(String address, String city) {



        return api.pbiffice(address,city);
    }
}
