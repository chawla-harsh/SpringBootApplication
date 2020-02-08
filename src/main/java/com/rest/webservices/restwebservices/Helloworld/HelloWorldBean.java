package com.rest.webservices.restwebservices.Helloworld;

/**
 * // TODO Comment
 */
public class HelloWorldBean {
  String message;
  public HelloWorldBean(String str) {
    this.message= str;
  }

  public String getMessage() {
    return message;
  }
  
  @Override
  public String toString() {
    return "HelloWorldBean{" +
        "message='" + message + '\'' +
        '}';
  }
}
