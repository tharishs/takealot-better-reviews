package com.github.tharishs.tbr.config.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "web-client.truststore")
public class TruststoreProperties {

    Resource path;
    String protocol;
    String s3cret;
    String type;
}
