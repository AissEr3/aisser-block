package com.aisser.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "upload.local")
public class LocalProperties {

    private String url;

    private String dir;

}
