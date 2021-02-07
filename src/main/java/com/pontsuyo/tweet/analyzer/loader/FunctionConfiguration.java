package com.pontsuyo.tweet.analyzer.loader;

import com.pontsuyo.tweet.analyzer.loader.domain.service.TweetService;
import java.util.function.Function;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FunctionConfiguration {

  private final TweetService tweetService;

  public FunctionConfiguration(TweetService tweetService) {
    this.tweetService = tweetService;
  }

  public static void main(String[] args) {
    SpringApplication.run(FunctionConfiguration.class, args);
  }

  @Bean
  public Function<Long, String> updateTweet() {
    return id -> tweetService.updateTweets();
  }
}
