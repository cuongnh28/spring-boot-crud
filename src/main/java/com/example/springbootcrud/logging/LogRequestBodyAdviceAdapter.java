package com.example.springbootcrud.logging;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HuyHV
 *
 * LogRequestBodyAdviceAdapter
 */
@Slf4j
@ControllerAdvice
public class LogRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    @Value("${management.endpoints.web.base-path:x}")
    private String managementEndpoint;

    @Value("${application.logging.request:true}")
    private boolean appLogRequest;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        String uri = httpServletRequest.getRequestURI();
        return !uri.startsWith(managementEndpoint);
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
                                MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        if(appLogRequest) {
            Map<String, Object> mapCustomizeLog = new HashMap();
            if(httpServletRequest.getQueryString() != null) {
                mapCustomizeLog.put("parameters", httpServletRequest.getQueryString());
            }
            mapCustomizeLog.put("payload", body);
            log.info("payload", StructuredArguments.entries(mapCustomizeLog));
        }
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    @Override
    public Object handleEmptyBody(@Nullable Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                  Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        Map<String, Object> mapCustomizeLog = new HashMap();
        if(httpServletRequest.getQueryString() != null) {
            mapCustomizeLog.put("parameters", httpServletRequest.getQueryString());
            log.info("payload", StructuredArguments.entries(mapCustomizeLog));
        }
        return body;
    }
}
