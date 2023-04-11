package com.laxmi.lifcvisitors.retrofitservices;

import com.laxmi.lifcvisitors.model.MSG;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

//    http://lifc.shailsoft.com/api/otp/send

    @FormUrlEncoded
    @POST("otp/send")
    Call<MSG> getOtp(@Query("mobile_number") String mobile_number
    );

//    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("guard/signup/request")
    Call<ResponseBody> getSignup(@Field("mobile_number") String mobile_number,
                                 @Field("emp_code") String emp_code,
                                 @Field("otp") String otp,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("guard/login/request")
    Call<MSG> getLogin(@Field("mobile_code") String mobile_code,
                                 @Field("password") String password);


   /* @GET("booking-service.php")
    Call<BookingServiceData> getBookingService();

*/
}