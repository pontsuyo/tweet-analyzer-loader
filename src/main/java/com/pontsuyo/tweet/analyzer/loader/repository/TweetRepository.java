package com.pontsuyo.tweet.analyzer.loader.repository;

import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

@Repository
public class TweetRepository {

  /**
   * DynamoDBへの書き込み
   */
  public String updateTweet(Long id) {
    var request = PutItemRequest.builder()
        .tableName("tweet-analyzer")
        .item(Map.of(
            "id", AttributeValue.builder().n("100").build(),
            "text", AttributeValue.builder().n(new Date().toInstant().toString()).build())
        )
        .build();

    var client = DynamoDbClient.create();
    client.putItem(request);

    // TODO クラス間のIF調整
    return "OK";
  }
}


