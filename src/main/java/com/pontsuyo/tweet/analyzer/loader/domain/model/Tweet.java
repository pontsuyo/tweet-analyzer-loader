package com.pontsuyo.tweet.analyzer.loader.domain.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import twitter4j.MediaEntity;
import twitter4j.Status;

@Slf4j
@Builder
public class Tweet {

  private final Long tweetId;

  private final Long userId;

  private final String text;

  private final Integer favoriteCount;

  private final Integer retweetCount;

  private final List<String> imageUrls;

  public static Tweet fromStatus(Status status) {
    return Tweet.builder()
        .tweetId(status.getId())
        .userId(status.getUser().getId())
        .text(status.getText())
        .favoriteCount(status.getFavoriteCount())
        .retweetCount(status.getRetweetCount())
        .imageUrls(getMediaUrlList(status))
        .build();
  }

  static private List<String> getMediaUrlList(Status status) {
    return Arrays.stream(status.getMediaEntities()).sequential()
        .map(MediaEntity::getMediaURL)
        .collect(Collectors.toList());
  }

  /**
   * DynamoDBのRepositoryに渡すクエリに変換する
   *
   * @return DynamoDB用書き込みクエリ
   */
  public Map<String, AttributeValue> convert2QueryMap() {
    return Map.of(
        "tweet_id", AttributeValue.builder().n(tweetId.toString()).build(),
        "text", AttributeValue.builder().s(text).build(),
        "user_id", AttributeValue.builder().n(userId.toString()).build(),
        "favorite_count", AttributeValue.builder().n(favoriteCount.toString()).build(),
        "retweet_count", AttributeValue.builder().n(retweetCount.toString()).build(),
        "image_urls", AttributeValue.builder().l(stringList2AttributeValueList(imageUrls)).build()
    );
  }

  private List<AttributeValue> stringList2AttributeValueList(List<String> stringList) {
    return stringList.stream()
        .map(string -> AttributeValue.builder().s(string).build())
        .collect(Collectors.toList());
  }
}
