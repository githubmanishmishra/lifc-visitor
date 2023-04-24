package com.laxmi.lifcvisitors.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VisitorsByGuard {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Data> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public static class Data {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("employee_id")
        @Expose
        private String employeeId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("mobile_number")
        @Expose
        private String mobileNumber;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("image")
        @Expose
        private Object image;
        @SerializedName("address_proof_image")
        @Expose
        private Object addressProofImage;
        @SerializedName("purpose_of_coming")
        @Expose
        private String purposeOfComing;
        @SerializedName("pincode")
        @Expose
        private String pincode;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("check_in")
        @Expose
        private String checkIn;
        @SerializedName("check_out")
        @Expose
        private String checkOut;
        @SerializedName("employee_name")
        @Expose
        private String employeeName;
        @SerializedName("employee_mobile_number")
        @Expose
        private String employeeMobileNumber;
        @SerializedName("meet_place")
        @Expose
        private Object meetPlace;
        @SerializedName("disapprove_reason")
        @Expose
        private Object disapproveReason;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public Object getAddressProofImage() {
            return addressProofImage;
        }

        public void setAddressProofImage(Object addressProofImage) {
            this.addressProofImage = addressProofImage;
        }

        public String getPurposeOfComing() {
            return purposeOfComing;
        }

        public void setPurposeOfComing(String purposeOfComing) {
            this.purposeOfComing = purposeOfComing;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCheckIn() {
            return checkIn;
        }

        public void setCheckIn(String checkIn) {
            this.checkIn = checkIn;
        }

        public String getCheckOut() {
            return checkOut;
        }

        public void setCheckOut(String checkOut) {
            this.checkOut = checkOut;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getEmployeeMobileNumber() {
            return employeeMobileNumber;
        }

        public void setEmployeeMobileNumber(String employeeMobileNumber) {
            this.employeeMobileNumber = employeeMobileNumber;
        }

        public Object getMeetPlace() {
            return meetPlace;
        }

        public void setMeetPlace(Object meetPlace) {
            this.meetPlace = meetPlace;
        }

        public Object getDisapproveReason() {
            return disapproveReason;
        }

        public void setDisapproveReason(Object disapproveReason) {
            this.disapproveReason = disapproveReason;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}
