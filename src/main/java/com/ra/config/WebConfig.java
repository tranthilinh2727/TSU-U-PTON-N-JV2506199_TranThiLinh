package com.ra.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // cấu hình toàn cục (DataSource, JPA, service, repository) — dùng AppConfig
        return new Class<?>[] { AppConfig.class, CloudinaryConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // nếu có config riêng cho web (thường đặt trong same AppConfig). Nếu muốn tách, có thể tạo WebMvcConfig.
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
