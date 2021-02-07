package com.pontsuyo.tweet.analyzer.loader.domain.service;

import com.pontsuyo.tweet.analyzer.loader.config.AppProperties;
import com.pontsuyo.tweet.analyzer.loader.config.TwitterConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import twitter4j.Twitter;

@SpringBootTest(classes = {
    TweetService.class,
    TwitterConfig.class,
    Twitter.class,
    AppProperties.class
})
class TweetServiceTest {

  @Autowired
  private TweetService tweetService;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void tweetQuery() {
    tweetService.updateTweets();
  }
}
