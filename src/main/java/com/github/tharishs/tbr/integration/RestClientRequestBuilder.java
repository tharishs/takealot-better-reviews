package com.github.tharishs.tbr.integration;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

@Builder
@Getter
public class RestClientRequestBuilder {

    /**
     * <p>The method in which the endpoint should be consumed as a {@link HttpMethod}</p>
     * <p>Example: {@code HttpMethod.GET}</p>
     */
    private final HttpMethod method;
    /**
     * <p>Preferable to use {@link #uriFunction}</p>
     * <p>Sets the {@link URI} directly</p>
     *
     * @see #uriFunction
     */
    private final URI uri;
    /**
     * <p>Sets the base URL to connect to</p>
     * <p>The {@link #uri} or {@link #uriFunction} is appended to the end of this</p>
     */
    private final String baseUrl;
    /**
     * <p>The class type of the request to be posted as set in {@link #postRequestBody}</p>
     * <p>Example: {@code String.class}</p>
     */
    private final Class<?> postRequestBodyClass;
    /**
     * The contents of the request to be posted as an {@link #Object}
     */
    private final Object postRequestBody;
    /**
     * <p>The URI to be consumed. This is appeneded to the {@link #baseUrl}</p>
     * <p>Contains builder-style methods.</p>
     *
     * @see UriBuilder
     */
    private final Function<UriBuilder, URI> uriFunction;
    /**
     * Headers to be sent off with the request as a multi-value map of {@code Strings}.
     */
    private final MultiValueMap<String, String> headers;

    public MultiValueMap<String, String> getHeaders() {
        if (MapUtils.isEmpty(headers)) {
            return new HttpHeaders();
        }
        return headers;
    }
}
