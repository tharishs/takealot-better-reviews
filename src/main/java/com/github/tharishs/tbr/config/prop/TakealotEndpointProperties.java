package com.github.tharishs.tbr.config.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tharish Sooruth
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "integration")
public class TakealotEndpointProperties {

    String takealotBaseUrl;
    String takealotReviewUriPart;
    String takealotProdDetailUriPart;
}
