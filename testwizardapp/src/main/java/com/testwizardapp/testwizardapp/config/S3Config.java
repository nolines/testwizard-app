package com.testwizardapp.testwizardapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    @Bean(destroyMethod = "close")
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of("eu-central-1"))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("xxxxxx", "xxxxx")))
                .build();
    }
}