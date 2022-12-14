package io.giovannymassuia.splitappapi.graphql.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Rating {
  FIVE_STARTS("⭐⭐⭐⭐⭐"),
  FOUR_STARTS("⭐⭐⭐⭐"),
  THREE_STARTS("⭐⭐⭐"),
  TWO_STARTS("⭐⭐"),
  ONE_START("⭐");

  private final String stars;

  Rating(String stars) {
    this.stars = stars;
  }

  @JsonValue
  public String getStars() {
    return stars;
  }

}
