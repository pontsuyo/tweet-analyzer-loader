package com.pontsuyo.tweet.analyzer.loader.domain.repository;

import com.pontsuyo.tweet.analyzer.loader.domain.model.Tweet;
import org.springframework.data.repository.CrudRepository;

public interface TweetRepository extends CrudRepository<Tweet, Long> {

  Tweet getTweetById(Long id);
}

