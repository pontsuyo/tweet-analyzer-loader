package com.pontsuyo.tweet.analyzer.loader.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Data;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@Data
public class Tweet {

  private Long tweetId;

  private Long userId;

  private String text = "";

  private List<String> imageUrls = Collections.emptyList();

  private Integer favoriteCount;

  private Integer retweetCount;

  public Map<String, AttributeValue> toQueryMap() {
    return Map.of(
        "id", AttributeValue.builder().n(tweetId.toString()).build(),
        "text", AttributeValue.builder().s(text).build(),
        "user_id", AttributeValue.builder().n(userId.toString()).build(),
        "favorite_count", AttributeValue.builder().n(favoriteCount.toString()).build(),
        "retweet_count", AttributeValue.builder().n(retweetCount.toString()).build()
    );
  }
}
