package com.ra.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        // nếu muốn bảo mật thì đẩy vào properties/env thay vì hardcode
        config.put("cloud_name", "dubrp0g8y");
        config.put("api_key", "857891378375941");
        config.put("api_secret", "_RlYgghmtAvolHaX_pE8JaUTxik");
        return new Cloudinary(config);
    }
}
