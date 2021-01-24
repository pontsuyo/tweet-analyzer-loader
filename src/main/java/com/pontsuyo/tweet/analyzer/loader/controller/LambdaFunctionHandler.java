package com.pontsuyo.tweet.analyzer.loader.controller;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.pontsuyo.tweet.analyzer.loader.Application;
import com.pontsuyo.tweet.analyzer.loader.config.AwsProperties;

public class LambdaFunctionHandler implements RequestHandler<Object, Object>{

  private AwsProperties awsProperties;

  @Override
  public Object handleRequest(Object input, Context context) {
    context.getLogger().log("-----start.-----");

    awsProperties = Application.getBean(AwsProperties.class);
    context.getLogger().log("dbname = " + awsProperties.getDbName());

    context.getLogger().log("-----end.-----");
    return "success.";
  }

}
