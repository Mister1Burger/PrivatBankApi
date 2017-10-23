package home.rxjavatest.rest;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

@SuppressWarnings("WeakerAccess")
public class Service {

    private static final String CONTENT_TYPE = "application/json; charset=UTF-8";

    private Api api;

    public Service(Api api) {
        this.api = api;
    }

    public Observable<Response> paySale() {

        Map<String, String> request = new HashMap<>();
//        request.put("buyer_email", isracardRequest.getBuyerEmail());
//        request.put("buyer_name", isracardRequest.getBuyerName());
//        request.put("payme_sale_id", isracardRequest.getPaymeSaleId());
//        request.put("credit_card_cvv", isracardRequest.getCreditCardCVV());
//        request.put("credit_card_exp", isracardRequest.getCreditCardExp());
//        request.put("credit_card_number", isracardRequest.getCreditCardNumber());
//        request.put("installments", isracardRequest.getInstallments());
//        request.put("buyer_social_id", isracardRequest.getBuyerSocialID());

        return api.paySale(CONTENT_TYPE, request);
    }
}
