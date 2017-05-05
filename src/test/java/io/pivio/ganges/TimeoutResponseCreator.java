package io.pivio.ganges;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.web.client.ResponseCreator;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class TimeoutResponseCreator implements ResponseCreator {

    public static TimeoutResponseCreator withTimeout() {
        return new TimeoutResponseCreator();
    }

    @Override
    public ClientHttpResponse createResponse(ClientHttpRequest request) throws IOException {
        throw new SocketTimeoutException("Testing timeout exception");
    }
}