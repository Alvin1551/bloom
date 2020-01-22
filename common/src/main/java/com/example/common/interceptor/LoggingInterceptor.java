package com.example.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Alvin
 * This class is used to log the correlation_id
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    private static final String TRACING_INFO = "tracingInfo";
    /**
     * This method is used to log the correlation_id in every log
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String correlationId = request.getParameter("correlation_id");
        MDC.put(TRACING_INFO, String.format(" correlationID=%s", correlationId));
        return true;
    }
    /**
     * This method is used to remove the correlation_id from {@link MDC}
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MDC.remove(TRACING_INFO);
    }
}
