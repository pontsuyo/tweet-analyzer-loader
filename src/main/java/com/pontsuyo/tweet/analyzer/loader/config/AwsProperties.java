package com.pontsuyo.tweet.analyzer.loader.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("aws.db")
@Data
public class AwsProperties {
  private String dbName;
  private String host;
  private String userName;
  private String password;
}
