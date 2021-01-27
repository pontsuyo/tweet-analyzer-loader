package com.pontsuyo.tweet.analyzer.loader.domain.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "tweet")
public class Tweet implements Serializable {

  @Id
  private Long id;

  private String text;
}
