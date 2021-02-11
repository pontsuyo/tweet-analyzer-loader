package com.pontsuyo.tweet.analyzer.loader.domain.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import twitter4j.Query;

@Builder
public class TweetSearchQuery {

  private Language lang;
  private Date date;
  private int minFaves;

  /**
   * repositoryに渡すqueryを生成する
   *
   * @return
   */
  public Query generateQuery() {
    var sb = new StringBuilder();

    // HACK 必須要素の設定ロジックもっと綺麗にかけそう
    sb.append("filter:");
    sb.append("images");

    if (!lang.equals(Language.NONE)) {
      // HACK 先頭にスペース挟んでるのあまりに不格好
      sb.append(" lang:");
      sb.append(lang.string);
    }

    if (!(Objects.isNull(date))) {
      // HACK 先頭にスペース挟んでるのあまりに不格好
      sb.append(" since:");

      var df = new SimpleDateFormat("yyyy-MM-dd");
      sb.append(df.format(new Date()));
    }

    // HACK 先頭にスペース挟んでるのあまりに不格好
    sb.append(" min_faves:");
    sb.append(minFaves);

    return new Query(sb.toString());
  }

  @Getter
  public enum Language {
    NONE(""),
    JAPANESE("ja");

    private String string;

    Language(String string) {
      this.string = string;
    }
  }
}
