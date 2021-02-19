package com.pontsuyo.tweet.analyzer.loader.repository;

import com.pontsuyo.tweet.analyzer.loader.domain.model.TweetScore;
import com.pontsuyo.tweet.analyzer.loader.domain.repository.TweetScoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

@Slf4j
@Repository
public class DynamoDBScoreRepository implements TweetScoreRepository {

  /**
   * DynamoDBへの書き込み
   */
  public void updateTweetScore(TweetScore tweetScore) {
    var request = PutItemRequest.builder()
        .tableName("tweet-analyzer-score")
        .item(tweetScore.convert2QueryMap())
        .build();

    var client = DynamoDbClient.create();
    client.putItem(request);

    log.info("DBへの書き込みに成功しました。");
  }
}