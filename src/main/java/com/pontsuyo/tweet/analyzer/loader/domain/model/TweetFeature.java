package com.pontsuyo.tweet.analyzer.loader.domain.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class TweetFeature {

  private final Long tweetId;

  private final Long userId;

  private final String text;

  private final List<String> imageUrls;

  // note ComponentクラスでAutowiredしたものを使うにはtoQueryMap()ごと移動する必要あり
  private final ObjectMapper objectMapper = new ObjectMapper();

  public static TweetFeature fromStatus(Status status) {
    return TweetFeature.builder()
        .tweetId(status.getId())
        .userId(status.getUser().getId())
        .text(status.getText())
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
   * <p>
   * AttributeValue.l()は使いにくなったので image_urlsの書き込み時は、シリアライズしてAttributeValue.s()を使用する。
   *
   * @return DynamoDB用書き込みクエリ
   */
  public Map<String, AttributeValue> convert2QueryMap() {
    return Map.of(
        "tweet_id", AttributeValue.builder().n(tweetId.toString()).build(),
        "text", AttributeValue.builder().s(text).build(),
        "user_id", AttributeValue.builder().n(userId.toString()).build(),
        "image_urls", AttributeValue.builder().s(objectToString(imageUrls)).build()
    );
  }

  private String objectToString(Object obj) {
    String jsonString;
    try {
      jsonString = objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.warn("object serialization failed.", e);
      return "";
    }
    return jsonString;
  }
}
