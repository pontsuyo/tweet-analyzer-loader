package com.pontsuyo.tweet.analyzer.loader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class AppProperties {

  private TwitterConfig twitterConfig;

  public AppProperties(TwitterConfig twitterConfig) {
    this.twitterConfig = twitterConfig;
  }

  @Bean
  public Twitter twitter() {
    return new TwitterFactory(
        new ConfigurationBuilder()
            .setOAuthConsumerKey(twitterConfig.getConsumerKey())
            .setOAuthConsumerSecret(twitterConfig.getConsumerSecret())
            .setOAuthAccessToken(twitterConfig.getAccessToken())
            .setOAuthAccessTokenSecret(twitterConfig.getAccessTokenSecret())
            .build())
        .getInstance();
  }
}
