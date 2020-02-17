/**
 * File: TagManipulationException
 * Author: DorSey Q F TANG
 * Created: 2020/2/14 15:59
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.exception;

public class ApiInvocationException extends Exception {
    private final int errorCode;

    public ApiInvocationException(final int errorCode) {
        this(errorCode, null);
    }

    public ApiInvocationException(final int errorCode, final String message) {
        this(errorCode, null, null);
    }

    public ApiInvocationException(final int errorCode, final String message, final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "error code: " + errorCode + ", message: " + getLocalizedMessage() + ", cause: " + getCause();
    }
}
