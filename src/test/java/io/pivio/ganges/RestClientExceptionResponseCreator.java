package io.pivio.ganges;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.web.client.ResponseCreator;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

public class RestClientExceptionResponseCreator implements ResponseCreator {

    public static RestClientExceptionResponseCreator withRestClientException() {
        return new RestClientExceptionResponseCreator();
    }

    @Override
    public ClientHttpResponse createResponse(ClientHttpRequest request) throws IOException {
        throw new RestClientException("All sorts of RestClientExceptions");
    }
}