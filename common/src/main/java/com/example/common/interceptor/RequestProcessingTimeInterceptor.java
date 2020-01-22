package com.example.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Alvin
 * This class is used to log the total processing time for a request
 */
@Component
@Slf4j
public class RequestProcessingTimeInterceptor implements HandlerInterceptor {

    private static final String REQUEST_PROCESSING_TIME = "request-processing-time";
    /**
     * This method is used to start the timer for each request to find out the duration
     */
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        StopWatch stopWatch = new StopWatch(REQUEST_PROCESSING_TIME);
        stopWatch.start();
        request.setAttribute(REQUEST_PROCESSING_TIME, stopWatch);
        return true;
    }
    /**
     * This method is used to log the total processing time for a request
     */
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) {
        StopWatch stopWatch = (StopWatch) request.getAttribute(REQUEST_PROCESSING_TIME);
        stopWatch.stop();
        log.info("It took {} seconds to process the request to {}", stopWatch.getTotalTimeSeconds(), request.getRequestURI());
    }
}
