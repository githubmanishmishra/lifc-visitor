package com.laxmi.lifcvisitors.retrofitservices;

import com.laxmi.lifcvisitors.model.Branches;
import com.laxmi.lifcvisitors.model.Departments;
import com.laxmi.lifcvisitors.model.EmployeeByDepartment;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.model.Profile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @FormUrlEncoded
    @POST("otp/send")
    Call<MSG> getOtp(@Query("mobile_number") String mobile_number
    );

    @FormUrlEncoded
    @POST("visitor/request")
    Call<MSG> getVisitorRequest(@Query("name") String name,
                                @Query("mobile_number") String mobile_number,
                                @Query("otp") String otp,
                                @Query("purpose_of_coming") String purpose_of_coming,
                                @Query("pincode") String pincode,
                                @Query("state") String state,
                                @Query("city") String city,
                                @Query("check_in") String check_in,
                                @Query("check_out") String check_out,
                                @Query("employee_id") String employee_id,
                                @Query("employee_name") String employee_name,
                                @Query("employee_mobile_number") String employee_mobile_number,
                                @Query("address_proof_image") String address_proof_image,
                                @Query("image") String image);

    //    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("guard/signup/request")
    Call<ResponseBody> getSignup(@Field("mobile_number") String mobile_number,
                                 @Field("emp_code") String emp_code,
                                 @Field("otp") String otp,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("signup/forget-password/request")
    Call<MSG> getForgotPassword(@Field("mobile_number") String mobile_number,
                                @Field("otp") String otp,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("login/request")
    Call<MSG> getLogin(@Field("mobile_code") String mobile_code,
                       @Field("password") String password,
                       @Field("device_token") String deviceToken
                       );

    @FormUrlEncoded
    @POST("profile/update")
    Call<Profile> getProfileUpdate(@Header("Authorization") String auth,
                                   @Field("mobile_number") String mobile_number,
                                   @Field("name") String name,
                                   @Field("email") String email);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("departments")
    Call<Departments> getDepartments(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("branches")
    Call<Branches> getBranches(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("employees-by-department")
    Call<EmployeeByDepartment> getEmployeeByDepartment(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("profile")
    Call<Profile> getProfile(@Header("Authorization") String auth);


}