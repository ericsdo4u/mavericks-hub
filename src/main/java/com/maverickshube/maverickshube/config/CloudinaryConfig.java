package com.maverickshube.maverickshube.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
@Configuration
public class CloudinaryConfig {
    @Value("${cloud.api.name}")
    private String cloudName;
    @Value("${cloud.api.key}")
    private String cloudApiKey;
    @Value("${cloud.api.secret}")
    private String cloudApiSecret;


    @Bean
    public Cloudinary cloudinary(){

        Map<?, ?> map = ObjectUtils.asMap(
                "cloud_name",cloudName,
                "api_key", cloudApiKey,
                "api_secret",cloudApiSecret
        );
        return new Cloudinary(map);

    }


}
