/**
 * File: Response
 * Author: DorSey Q F TANG
 * Created: 2020/2/17 14:35
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.zero.web.model.Constants;

public class Response {

    public static final int OK = 0;
    public static final int ERROR = 1;

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("phase")
    private String phase;

    /**
     * Empty constructor of {@link Response}.
     */
    public Response() {
        // empty
    }

    public Response(int statusCode, String phase) {
        this.statusCode = statusCode;
        this.phase = phase;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public static Response ok() {
        return new Response(OK, Constants.OP_SUCCEED);
    }

    public static Response fail(final String message) {
        return new Response(ERROR, message);
    }
}
