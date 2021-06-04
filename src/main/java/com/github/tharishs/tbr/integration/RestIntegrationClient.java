package com.github.tharishs.tbr.integration;

import com.github.tharishs.tbr.constant.ErrorEnum;
import com.github.tharishs.tbr.exception.IntegrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author Tharish Sooruth
 */
@Slf4j
@Component
public class RestIntegrationClient {

    private WebClient webClient;

    @Autowired
    public RestIntegrationClient(@Qualifier("Insecure") WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * @param builder     Required to build the request
     * @param returnClass The return class used for the ResponseEntity
     * @param <T>         Type
     * @return this client after consuming the endpoint
     * @throws IntegrationException integration exception
     */
    public <T> ResponseEntity<T> consumeEndpoint(RestClientRequestBuilder builder, Class<T> returnClass) throws IntegrationException {

        final long startTime = System.currentTimeMillis();
        ClientResponse clientResponse = retrieveClientResponse(builder);

        log.info("Call to integration took {} ms", System.currentTimeMillis() - startTime);

        if (clientResponse == null) {
            throw new IntegrationException(ErrorEnum.UNDEFINED);
        }

        checkStatusCode(clientResponse.statusCode());
        releaseClientResponse(clientResponse);

        return clientResponse.toEntity(returnClass).block();
    }

    /**
     * <p>Perform a blocking HTTP request and return a ClientResponse with the response status and headers. </p>
     * <p>Unlike retrieve(), when using exchange(), it is the responsibility of the application to consume
     * any response content regardless of the scenario (success, error, unexpected data, etc).
     * Not doing so can cause a memory leak.</p>
     *
     * @param builder Required to build the request
     * @return Representation of an HTTP response, as returned by WebClient
     * @throws IntegrationException integration exception
     */
    private ClientResponse retrieveClientResponse(RestClientRequestBuilder builder) throws IntegrationException {

        this.webClient = webClient.mutate().baseUrl(builder.getBaseUrl()).build();

        switch (builder.getMethod()) {
            case GET:
                try {
                    return webClient
                            .get()
                            .uri(builder.getUriFunction())
                            .headers(httpHeaders -> httpHeaders.putAll(builder.getHeaders()))
                            .exchange()
                            .block();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw new IntegrationException(ErrorEnum.INTEGRATION_FAILED_ACCESS, e);
                }
            case POST:
                try {
                    return webClient.post()
                            .uri(builder.getUriFunction())
                            .body(Mono.just(builder.getPostRequestBody()), builder.getPostRequestBodyClass())
                            .headers(httpHeaders -> httpHeaders.putAll(builder.getHeaders()))
                            .exchange()
                            .block();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw new IntegrationException(ErrorEnum.INTEGRATION_FAILED_ACCESS, e);
                }
            default:
                throw new IntegrationException(ErrorEnum.INTEGRATION_FAILED_ACCESS);
        }


    }

    /**
     * When using a ClientResponse through the WebClient exchange() method,
     * you have to make sure that the body is consumed or released
     *
     * @param clientResponse Represents an HTTP response, as returned by WebClient
     * @see WebClient.RequestHeadersSpec#exchange()
     */
    private void releaseClientResponse(ClientResponse clientResponse) {

        if (Objects.isNull(clientResponse)) {
            return;
        }

        clientResponse.releaseBody();
    }

    /**
     * Checks the http status code
     * Only proceed on status 200
     * <p>
     * Do not proceed otherwise
     *
     * @param httpStatus Enumeration of HTTP status codes
     * @throws IntegrationException integration exception
     * @see HttpStatus
     * @see ClientResponse
     */
    private void checkStatusCode(HttpStatus httpStatus) throws IntegrationException {
        if (HttpStatus.OK == httpStatus) {
            return;
        }

        switch (httpStatus) {
            case NO_CONTENT:
                throw new IntegrationException(ErrorEnum.INTEGRATION_NO_CONTENT, "Code: " + httpStatus.toString());
            case FORBIDDEN:
                throw new IntegrationException(ErrorEnum.INTEGRATION_FORBIDDEN, "Code: " + httpStatus.toString());
            case NOT_FOUND:
                throw new IntegrationException(ErrorEnum.INTEGRATION_NOT_FOUND, "Code: " + httpStatus.toString());
            case UNAUTHORIZED:
                throw new IntegrationException(ErrorEnum.INTEGRATION_UNAUTHORIZED, "Code: " + httpStatus.toString());
            default:
                throw new IntegrationException(ErrorEnum.INTEGRATION_UNDEFINED_ERROR, "Code: " + httpStatus.toString());
        }
    }

}