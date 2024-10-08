package com.tuannt7.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tuannt7 on 08/10/2024
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aws.s3")
public class S3Config {
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String region;
    private String endpoint;

    @Bean
    public AmazonS3 s3Client() {
        return AmazonS3Client.builder()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(region)
                .build();
    }
}
