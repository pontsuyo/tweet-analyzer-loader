package com.pontsuyo.tweet.analyzer.loader.domain.service;

import com.pontsuyo.tweet.analyzer.loader.domain.model.TweetFeature;
import com.pontsuyo.tweet.analyzer.loader.domain.model.TweetScore;
import com.pontsuyo.tweet.analyzer.loader.domain.model.TweetSearchQuery;
import com.pontsuyo.tweet.analyzer.loader.domain.repository.TweetFeatureRepository;
import com.pontsuyo.tweet.analyzer.loader.domain.repository.TweetScoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.Date;

@Slf4j
@Service
public class TweetService {

	// note この値は仮置き
	private static final int MIN_FAVORITES = 10000;

	private final Twitter twitter;
	private final TweetFeatureRepository tweetFeatureRepository;
	private final TweetScoreRepository tweetScoreRepository;

	public TweetService(Twitter twitter,
	                    TweetFeatureRepository tweetFeatureRepository,
	                    TweetScoreRepository tweetScoreRepository) {
		this.twitter = twitter;
		this.tweetFeatureRepository = tweetFeatureRepository;
		this.tweetScoreRepository = tweetScoreRepository;
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
				result.getTweets()
						.forEach(status -> {
							// update tweet feature
							var feature = TweetFeature.fromStatus(status);
							tweetFeatureRepository.updateTweetFeature(feature);

							// update tweet score
							var score = TweetScore.fromStatus(status);
							tweetScoreRepository.updateTweetScore(score);
						});

			} while ((query = result.nextQuery()) != null);

		} catch (TwitterException te) {
			log.error("Tweet取得失敗", te);
		}

		// todo 返り値の型を検討
		return "update completed!";
	}
}
