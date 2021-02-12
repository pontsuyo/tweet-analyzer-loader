package com.pontsuyo.tweet.analyzer.loader.domain.service;

import com.pontsuyo.tweet.analyzer.loader.domain.model.Tweet;
import com.pontsuyo.tweet.analyzer.loader.domain.model.TweetSearchQuery;
import com.pontsuyo.tweet.analyzer.loader.domain.repository.TweetRepository;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Slf4j
@Service
public class TweetService {

  // note この値は仮置き
  private static final int MIN_FAVORITES = 10000;

  private final Twitter twitter;
  private final TweetRepository tweetRepository;

  public TweetService(Twitter twitter, TweetRepository tweetRepository) {
    this.twitter = twitter;
    this.tweetRepository = tweetRepository;
  }

  public String updateTweets() {

    var query = TweetSearchQuery.builder()
        .date(new Date())
        .lang(TweetSearchQuery.Language.JAPANESE)
        .minFaves(MIN_FAVORITES)
        .build()
        .generateQuery();

    QueryResult result;

    try {
      do {
        result = twitter.search(query);
        result.getTweets().stream()
            .map(Tweet::fromStatus)
            .forEach(tweetRepository::updateTweet);

      } while ((query = result.nextQuery()) != null);

    } catch (TwitterException te) {
      log.error("Tweet取得失敗", te);
    }

    // todo 返り値の型を検討
    return "update completed!";
  }
}
