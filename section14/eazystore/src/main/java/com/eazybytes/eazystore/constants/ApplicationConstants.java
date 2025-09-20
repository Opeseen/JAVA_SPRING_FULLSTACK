package com.eazybytes.eazystore.constants;

public class ApplicationConstants {
  // restrict the usage of this class to only defining the constants
  private ApplicationConstants(){
    throw new AssertionError("Utility class can not be instantiated");
  }

  public static final String JWT_SECRET_KEY = "JWT_SECRET";
  public static final String JWT_SECRET_DEFAULT_VALUE = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
  public static final String JWT_HEADER = "Authorization";
}
