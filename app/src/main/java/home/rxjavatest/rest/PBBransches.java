package home.rxjavatest.rest;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PBBransches {

    @SerializedName("name")
    String name;
    @SerializedName("state")
    String state;
    @SerializedName("id")
    int id;
    @SerializedName("country")
    String country;
    @SerializedName("city")
    String city;
    @SerializedName("index")
    int index;
    @SerializedName("phone")
    String phone;
    @SerializedName("email")
    String email;
    @SerializedName("address")
    String address;






}
