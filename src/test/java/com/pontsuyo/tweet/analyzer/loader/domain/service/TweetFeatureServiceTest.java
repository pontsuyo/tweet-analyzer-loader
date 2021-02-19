package com.pontsuyo.tweet.analyzer.loader.domain.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TweetFeatureServiceTest {

  @Autowired
  private TweetService tweetService;

  @Test
  void tweetQuery() {
    tweetService.updateTweets();
  }
}
