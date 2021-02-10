package com.pontsuyo.tweet.analyzer.loader.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import twitter4j.Status;

@Builder
public class Tweet {

  private final Long tweetId;

  private final Long userId;

  private final String text;

  private final List<String> imageUrls;

  private final Integer favoriteCount;

  private final Integer retweetCount;

  public static Tweet fromStatus(Status status) {
    return Tweet.builder()
        .tweetId(status.getId())
        .userId(status.getUser().getId())
        .text(status.getText())
        .imageUrls(Collections.emptyList()) // todo 仮置き
        .favoriteCount(status.getFavoriteCount())
        .retweetCount(status.getFavoriteCount())
        .build();
  }

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
