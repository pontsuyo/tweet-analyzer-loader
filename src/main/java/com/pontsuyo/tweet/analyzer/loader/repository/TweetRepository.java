package com.pontsuyo.tweet.analyzer.loader.repository;

import com.pontsuyo.tweet.analyzer.loader.domain.model.Tweet;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

@Repository
public class TweetRepository {

  /**
   * DynamoDBへの書き込み
   */
  public String updateTweet(Tweet tweet) {
    var request = PutItemRequest.builder()
        .tableName("tweet-analyzer")
        .item(tweet.toQueryMap())
        .build();

    var client = DynamoDbClient.create();
    client.putItem(request);

    // TODO クラス間のIF調整
    return "OK";
  }
}


