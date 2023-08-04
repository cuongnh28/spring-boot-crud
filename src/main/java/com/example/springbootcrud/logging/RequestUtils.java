package com.example.springbootcrud.logging;

import org.slf4j.MDC;

import java.util.UUID;

public class RequestUtils {

    private RequestUtils() {
    }

    public static final String HEADER_REQUEST_ID = "X-Request-ID";
    public static final String CONTEXT_REQUEST_ID = "request_id";
    public static final String CONTEXT_REQUEST_URL = "url";
    public static final String CONTEXT_REQUEST_METHOD = "request_method";
    public static final String CONTEXT_REQUEST_TYPE = "request_type";
    public static final String CONTEXT_RESPONSE_TIME= "response_time";
    public static final String CONTEXT_RESPONSE_STATUS= "http_status";

    public static String generateRequestId(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String currentRequestId(){
        return  MDC.get(CONTEXT_REQUEST_ID);
    }
}
