package com.github.tharishs.tbr.config;

import com.github.tharishs.tbr.config.prop.WebClientProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.KeyManagerFactory;
import java.security.KeyStore;

/**
 * @author Tharish Sooruth
 */
@Configuration
@Slf4j
public class WebClientConfig {

    private final WebClientProperties webClientProperties;

    @Autowired
    public WebClientConfig(WebClientProperties webClientProperties) {
        this.webClientProperties = webClientProperties;
    }

    @Bean("Insecure")
    public WebClient insecureWebClient() {

        HttpClient httpClient = HttpClient.create()
                .secure(s -> s.sslContext(SslContextBuilder
                        .forClient()
                        .trustManager(InsecureTrustManagerFactory.INSTANCE)
                ))
                .tcpConfiguration(client ->
                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, webClientProperties.getMilliSecondTimeout())
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(10))
                                        .addHandlerLast(new WriteTimeoutHandler(10))));

        return getWebClient(httpClient);
    }

    @Bean("Secure")
    public WebClient secureWebClient() throws Exception {

        Resource resource = webClientProperties.getTruststore().getPath();
        KeyStore ks = KeyStore.getInstance(webClientProperties.getTruststore().getType());
        ks.load(resource.getInputStream(), webClientProperties.getTruststore().getS3cret().toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, webClientProperties.getTruststore().getS3cret().toCharArray());

        HttpClient httpClient = HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(SslContextBuilder
                        .forClient()
                        .keyManager(kmf)
                        .trustManager(InsecureTrustManagerFactory.INSTANCE)))
                .tcpConfiguration(client ->
                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, webClientProperties.getMilliSecondTimeout())
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(webClientProperties.getReadTimeout()))
                                        .addHandlerLast(new WriteTimeoutHandler(webClientProperties.getWriteTimeout()))));

        return getWebClient(httpClient);
    }

    private WebClient getWebClient(HttpClient httpClient) {

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filters(eff -> {
                    eff.add(logRequest());
                    eff.add(logResponse());
                })
                .codecs(c -> c.defaultCodecs().enableLoggingRequestDetails(true))
                .codecs(c -> c.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build();
    }

    ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            StringBuilder sb = new StringBuilder("Request: ");
            sb.append(clientRequest.method().name());
            sb.append(" ");
            sb.append(clientRequest.url());
            sb.append(" ");
            sb.append(clientRequest.headers());
            log.info(sb.toString());
            return Mono.just(clientRequest);
        });
    }

    ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            StringBuilder sb = new StringBuilder("Response: ");
            sb.append(clientResponse.statusCode());
            log.info(sb.toString());
            return Mono.just(clientResponse);
        });
    }
}
