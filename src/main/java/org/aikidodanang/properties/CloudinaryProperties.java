package org.aikidodanang.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Component
@Data
@Validated
@PropertySource(value = "classpath:cloudinary.properties")
@ConfigurationProperties(prefix = "cloudinary")
public class CloudinaryProperties {
    @NotBlank
    private String cloudName;

    @NotBlank
    private String key;

    @NotBlank
    private String secret;
}
