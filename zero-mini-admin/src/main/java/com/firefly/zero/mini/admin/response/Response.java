/**
 * File: Response
 * Author: DORSEy Q F TANG
 * Created: 2020/3/11
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Response<T> {

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;

    @JsonProperty("statusCode")
    private int statusCode;

    @JsonProperty("data")
    private List<T> data;

    public Response() {}

    public Response<T> status(final int statusCode) {
        setStatusCode(statusCode);
        return this;
    }

    public Response<T> data(final List<T> data) {
        setData(data);
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
