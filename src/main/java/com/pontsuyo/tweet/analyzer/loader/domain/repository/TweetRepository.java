package com.pontsuyo.tweet.analyzer.loader.domain.repository;

import com.pontsuyo.tweet.analyzer.loader.domain.model.Tweet;

public interface TweetRepository {
  void updateTweet(Tweet tweet);
}
