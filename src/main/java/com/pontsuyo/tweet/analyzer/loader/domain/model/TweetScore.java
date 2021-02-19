package com.pontsuyo.tweet.analyzer.loader.domain.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.Map;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import twitter4j.Status;

@Slf4j
@Builder
public class TweetScore {

  private final Long tweetId;

  private final Integer favoriteCount;

  private final Integer retweetCount;

  // note ComponentクラスでAutowiredしたものを使うにはtoQueryMap()ごと移動する必要あり
  private final ObjectMapper objectMapper = new ObjectMapper();

  public static TweetScore fromStatus(Status status) {
    return TweetScore.builder()
        .tweetId(status.getId())
        .favoriteCount(status.getFavoriteCount())
        .retweetCount(status.getRetweetCount())
        .build();
  }

  /**
   * DynamoDBのRepositoryに渡すクエリに変換する
   * <p>
   * AttributeValue.l()は使いにくなったので image_urlsの書き込み時は、シリアライズしてAttributeValue.s()を使用する。
   *
   * @return DynamoDB用書き込みクエリ
   */
  public Map<String, AttributeValue> convert2QueryMap() {
    return Map.of(
        "tweet_id", AttributeValue.builder().n(tweetId.toString()).build(),
        "time",
        AttributeValue.builder().n(String.valueOf(new Date().toInstant().getEpochSecond())).build(),
        "favorite_count", AttributeValue.builder().n(favoriteCount.toString()).build(),
        "retweet_count", AttributeValue.builder().n(retweetCount.toString()).build()
    );
  }
}
