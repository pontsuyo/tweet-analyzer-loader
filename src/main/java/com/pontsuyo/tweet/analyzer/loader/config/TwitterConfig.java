package com.pontsuyo.tweet.analyzer.loader.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "twitter.auth")
public class TwitterConfig {

  private String consumerKey;
  private String consumerSecret;
  private String accessToken;
  private String accessTokenSecret;

}
