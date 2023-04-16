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
                       @Field("password") String password);

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