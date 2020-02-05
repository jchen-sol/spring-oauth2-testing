package com.example.demo;

import lombok.Builder;
import lombok.Data;

@Builder
public class AccessTokenResponse {
    String access_token;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public AccessTokenResponse()
    {}

    public AccessTokenResponse(String access_token) {
        this.access_token = access_token;
    }
}
