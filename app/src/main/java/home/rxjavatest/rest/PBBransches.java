package home.rxjavatest.rest;


import android.os.Parcel;
import android.os.Parcelable;

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
public class PBBransches implements Parcelable {

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
     public static Parcelable.ClassLoaderCreator<PBBransches> CREATOR = new ClassLoaderCreator<PBBransches>() {
         @Override
         public PBBransches createFromParcel(Parcel parcel, ClassLoader classLoader) {
             return null;
         }

         @Override
         public PBBransches createFromParcel(Parcel parcel) {
             return null;
         }

         @Override
         public PBBransches[] newArray(int i) {
             return new PBBransches[0];
         }
     };
}
