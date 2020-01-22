package com.example.common.interceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Alvin
 *This class is used to log the response time while calling the downstream systems
 */
@Component
@Slf4j
public class APIInvTimeInterceptor implements ClientHttpRequestInterceptor {

    /**
     * This method is used to log the URI, response time,response code  time while calling the downstream systems
     */
    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {

        Stopwatch stopwatch = Stopwatch.createStarted();
        ClientHttpResponse response = execution.execute(request, body);
        stopwatch.stop();
        log.info("[ API Invocation Time for ] Method = {}, URI = {}, response time = {}, response code = {}", request.getMethod(), request.getURI(),
                stopwatch.elapsed(TimeUnit.MILLISECONDS), response.getRawStatusCode());
        return response;
    }
}
