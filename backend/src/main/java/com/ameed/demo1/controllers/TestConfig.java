package com.ameed.demo1.controllers;

import com.azure.storage.file.share.ShareClient;
import com.azure.storage.file.share.ShareServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class TestConfig {

    private final ShareServiceClient shareServiceClient;
    private final Environment environment;

    @Bean
    public ShareClient shareClient() {
        return shareServiceClient.getShareClient(environment.getProperty("spring.cloud.azure.storage.fileshare.share-name"));
    }
}
