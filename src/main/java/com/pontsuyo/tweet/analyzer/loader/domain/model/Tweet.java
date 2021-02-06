package com.pontsuyo.tweet.analyzer.loader.domain.model;

import lombok.Data;

@Data
public class Tweet {

  private Long id;

  private String text = "";
}
