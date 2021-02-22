package com.pontsuyo.tweet.analyzer.loader.repository;

import com.pontsuyo.tweet.analyzer.loader.domain.model.TweetFeature;
import com.pontsuyo.tweet.analyzer.loader.domain.repository.TweetFeatureRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

@Repository
public class DynamoDBFeatureRepository implements TweetFeatureRepository {

  /**
   * DynamoDBへの書き込み
   */
  public void updateTweetFeature(TweetFeature tweetFeature) {
    var request = PutItemRequest.builder()
        .tableName("tweet-analyzer-feature")
        .item(tweetFeature.convert2QueryMap())
        .build();

    var client = DynamoDbClient.create();
    client.putItem(request);
  }
}


