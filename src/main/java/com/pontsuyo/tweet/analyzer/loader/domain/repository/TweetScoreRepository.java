package com.pontsuyo.tweet.analyzer.loader.domain.repository;

import com.pontsuyo.tweet.analyzer.loader.domain.model.TweetScore;

public interface TweetScoreRepository {

  void updateTweetScore(TweetScore tweetScore);
}
