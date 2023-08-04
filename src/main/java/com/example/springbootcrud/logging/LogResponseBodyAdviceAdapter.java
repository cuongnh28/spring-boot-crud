package com.example.springbootcrud.logging;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author HuyHV
 *
 * LogResponseBodyAdviceAdapter
 */
@Slf4j
@ControllerAdvice
public class LogResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

    @Value("${management.endpoints.web.base-path:x}")
    private String managementEndpoint;

    @Value("${application.logging.response:true}")
    private boolean appLogResponse;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        String uri = httpServletRequest.getRequestURI();
        return !uri.startsWith(managementEndpoint);
    }

    @Override
    public Object beforeBodyWrite(Object response,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        if (serverHttpRequest instanceof ServletServerHttpRequest
                && serverHttpResponse instanceof ServletServerHttpResponse) {
            Map<String, Object> mapCustomizeLog = new HashMap();
            mapCustomizeLog.put("response", response);
            HttpServletResponse servletResponse = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();
            if(appLogResponse || HttpStatus.OK.value() != servletResponse.getStatus()) {
                log.info("response", StructuredArguments.entries(mapCustomizeLog));
            }
        }
        return response;
    }
}