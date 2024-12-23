package com.assessment.trackingnumbergenerator.dto;

public class TrackingNumberGeneratorResponse {

    private String tracking_number;
    private String created_at;

    public TrackingNumberGeneratorResponse(String tracking_number, String created_at){
        this.tracking_number = tracking_number;
        this.created_at = created_at;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
