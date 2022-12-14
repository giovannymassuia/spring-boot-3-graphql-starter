package io.giovannymassuia.splitappapi.graphql.model;

public record Author(Integer id, String name, String lastName) {

  public String fullName() {
    return name + " " + lastName;
  }

}
