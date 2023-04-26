package com.laxmi.lifcvisitors.retrofitservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedBackByVisitor {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("visitor_id")
        @Expose
        private Integer visitorId;
        @SerializedName("employee_id")
        @Expose
        private Integer employeeId;
        @SerializedName("guard_id")
        @Expose
        private Integer guardId;
        @SerializedName("employee_feedback")
        @Expose
        private String employeeFeedback;
        @SerializedName("visitor_feedback")
        @Expose
        private String visitorFeedback;
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

        public Integer getVisitorId() {
            return visitorId;
        }

        public void setVisitorId(Integer visitorId) {
            this.visitorId = visitorId;
        }

        public Integer getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Integer employeeId) {
            this.employeeId = employeeId;
        }

        public Integer getGuardId() {
            return guardId;
        }

        public void setGuardId(Integer guardId) {
            this.guardId = guardId;
        }

        public String getEmployeeFeedback() {
            return employeeFeedback;
        }

        public void setEmployeeFeedback(String employeeFeedback) {
            this.employeeFeedback = employeeFeedback;
        }

        public String getVisitorFeedback() {
            return visitorFeedback;
        }

        public void setVisitorFeedback(String visitorFeedback) {
            this.visitorFeedback = visitorFeedback;
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