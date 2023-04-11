package com.laxmi.lifcvisitors.model;

public class MSG {

    private String status;
    private String message;
    private String otp;

    private String token;

    public MSG() {
    }

    public String getStatus() {
        return status;
    }

    public void setSuccess(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
