package com.pontsuyo.tweet.analyzer.loader;

import com.pontsuyo.tweet.analyzer.loader.domain.repository.TweetRepository;
import java.util.function.Function;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FunctionConfiguration {

  private final TweetRepository tweetRepository;

  public FunctionConfiguration(TweetRepository tweetRepository) {
    this.tweetRepository = tweetRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(FunctionConfiguration.class, args);
  }

  @Bean
  public Function<Long, String> getTweet() {
    return id -> tweetRepository.getTweetById(id).getText();
  }
}
