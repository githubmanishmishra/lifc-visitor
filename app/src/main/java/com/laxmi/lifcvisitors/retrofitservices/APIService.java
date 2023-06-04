package com.laxmi.lifcvisitors.retrofitservices;

import com.laxmi.lifcvisitors.model.Branches;
import com.laxmi.lifcvisitors.model.Departments;
import com.laxmi.lifcvisitors.model.EmployeeByDepartment;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.model.Profile;
import com.laxmi.lifcvisitors.model.VisitorsByGuard;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIService {

    @FormUrlEncoded
    @POST("otp/send")
    Call<MSG> getOtp(@Field("mobile_number") String mobile_number
    );

    @FormUrlEncoded
    @POST("otp/send")
    Call<MSG> getOtpVisitor(@Field("mobile_number") String mobile_number,
                            @Field("type") String visitor
    );

    @FormUrlEncoded
    @POST("otp/send")
    Call<MSG> getOtpForgotPassword(@Field("mobile_number") String mobile_number,
                                   @Field("type") String visitor
    );

    @Multipart
    @POST("visitor/request")
    Call<ResponseBody> getVisitorRequest(@Header("Token") String token,
                                         @Part("name") String name,
                                         @Part("mobile_number") String mobile_number,
                                         @Part("otp") String otp,
                                         @Part("purpose_of_coming") String purpose_of_coming,
                                         @Part("pincode") String pincode,
                                         @Part("state") String state,
                                         @Part("city") String city,
                                         @Part("check_in") String check_in,
                                         @Part("check_out") String check_out,
                                         @Part("employee_id") String employee_id,
                                         @Part("name_one") String name_one,
                                         @Part("name_two") String name_two,
                                         @Part("name_three") String name_three,
                                         @Part("employee_name") String employee_name,
                                         @Part("employee_mobile_number") String employee_mobile_number,
                                         @Part MultipartBody.Part address_proof_image,
                                         @Part MultipartBody.Part image);

    //    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("guard/signup/request")
    Call<MSG> getSignup(@Field("mobile_number") String mobile_number,
                        @Field("emp_code") String emp_code,
                        @Field("otp") String otp,
                        @Field("type") String type,
                        @Field("password") String password,
                        @Field("device_token") String deviceToken);

    @FormUrlEncoded
    @POST("employee/signup/request")
    Call<MSG> getEmployeeSignup(@Field("mobile_number") String mobile_number,
                                @Field("emp_code") String emp_code,
                                @Field("otp") String otp,
                                @Field("type") String type,
                                @Field("password") String password,
                                @Field("device_token") String deviceToken);

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

    @Multipart
    @POST("profile/update")
    Call<ResponseBody> getProfileUpdate(@Header("Token") String token,
                                   @Part("mobile_number") String mobile_number,
                                   @Part("name") String name,
                                   @Part MultipartBody.Part image,
                                   @Part("email") String email);

    @Multipart
    @POST("profile/update")
    Call<ResponseBody> getProfileUpdate1(@Header("Token") String token,
                                   @Part("mobile_number") String mobile_number,
                                   @Part("name") String name,
                                   @Part MultipartBody.Part image,
                                   @Part("email") String email);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("employees-by-department")
    Call<EmployeeByDepartment> getemployeesbydepartment(@Header("Authorization") String auth,
                                                        @Query("department") String department);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("departments")
    Call<Departments> getDepartments(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("branches")
    Call<Branches> getBranches(@Header("Authorization") String auth);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("profile")
    Call<Profile> getProfile(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("visitors-by-guard")
    Call<VisitorsByGuard> getVisitorsByGuard(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("visitors-by-employees")
    Call<VisitorsByEmployee> getVisitorsByEmployee(@Header("Authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("officeboy/assign")
    Call<MSG> getOfficeBoyAssign(@Header("Authorization") String auth,
                                 @Query("visitor_id") int visitor_id,
                                 @Query("emp_code") String emp_code,
                                 @Query("name") String name,
                                 @Query("mobile_number") String mobile_number,
                                 @Query("floor") String floor);

    @FormUrlEncoded
    @POST("feedback/update")
    Call<MSG> getFeedbackUpdate(@Header("Authorization") String token,
                                @Field("visitor_id") int visitor_id,
                                @Field("employee_id") String employee_id,
                                @Field("guard_id") String guard_id,
                                @Field("employee_feedback") String employee_feedback,
                                @Field("visitor_feedback") String visitor_feedback,
                                @Field("check_out") String check_out,
                                @Field("type") String type
    );

    @FormUrlEncoded
    @POST("visitor/status/update")
    Call<MSG> getVisitorStatusUpdate(@Header("Authorization") String token,
                                     @Field("visitor_id") String visitor_id,
                                     @Field("status") String status,
                                     @Field("meet_place") String meet_place,
                                     @Field("disapprove_reason") String disapprove_reason,
                                     @Field("meet_up_time") String meet_up_time
    );

}