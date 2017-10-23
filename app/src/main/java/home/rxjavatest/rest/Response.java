package home.rxjavatest.rest;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {

    @SerializedName("status_code")
    private Integer statusCode;

    @SerializedName("payme_status")
    private String paymeStatus;

    @SerializedName("status_error_code")
    private Integer statusErrorCode;

    @SerializedName("payme_sale_id")
    private String paymeSaleId;

    @SerializedName("payme_sale_code")
    private Integer paymeSaleCode;

    @SerializedName("sale_created")
    private String saleCreated;

    @SerializedName("payme_sale_status")
    private String paymeSaleStatus;

    @SerializedName("sale_status")
    private String saleStatus;

    @SerializedName("currency")
    private String currency;

    @SerializedName("transaction_id")
    private String transactionId;

    @SerializedName("is_token_sale")
    private Boolean isTokenSale;

    @SerializedName("price")
    private Integer price;

    @SerializedName("payme_signature")
    private String paymeSignature;

    @SerializedName("payme_transaction_id")
    private String paymeTransactionId;

    @SerializedName("payme_transaction_total")
    private String paymeTransactionTotal;

    @SerializedName("payme_transaction_card_brand")
    private String paymeTransactionCardBrand;

    @SerializedName("payme_transaction_auth_number")
    private String paymeTransactionAuthNumber;

    @SerializedName("buyer_name")
    private String buyerName;

    @SerializedName("buyer_email")
    private String buyerEmail;

    @SerializedName("buyer_phone")
    private String buyerPhone;

    @SerializedName("buyer_card_mask")
    private String buyerCardMask;

    @SerializedName("buyer_card_exp")
    private String buyerCardExp;

    @SerializedName("buyer_social_id")
    private String buyerSocialId;

    @SerializedName("installments")
    private Integer installments;

    @SerializedName("sale_paid_date")
    private String salePaidDate;

    @SerializedName("sale_release_date")
    private String saleReleaseDate;

}
