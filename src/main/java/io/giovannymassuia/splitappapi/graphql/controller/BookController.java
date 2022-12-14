package io.giovannymassuia.splitappapi.graphql.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;

import io.giovannymassuia.splitappapi.graphql.model.Author;
import io.giovannymassuia.splitappapi.graphql.model.Book;
import io.giovannymassuia.splitappapi.graphql.model.Rating;

@Controller
public class BookController {

  // @PreAuthorize("isAuthenticated()")
  @QueryMapping
  public List<Book> allBooks(Principal principal) {
    String name = principal.getName();
    System.out.println("Principal name: " + name);

    JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) principal;

    String token = jwtAuthToken.getToken().getTokenValue();
    System.out.println("Token: " + token);

    String subject = jwtAuthToken.getToken().getSubject();
    System.out.println("Subject: " + subject);

    String email = jwtAuthToken.getToken().getClaimAsString("email");
    System.out.println("Email: " + email);

    String sub = jwtAuthToken.getToken().getClaimAsString("name");
    System.out.println("Name: " + sub);

    return List.of(
        new Book(1, "The Lord of the Rings", 1216, Rating.FIVE_STARTS, new Author(1, "J.R.R.", "Tolkien")),
        new Book(2, "The Hobbit", 310, Rating.FIVE_STARTS, new Author(1, "J.R.R.", "Tolkien")),
        new Book(3, "The Silmarillion", 429, Rating.FIVE_STARTS, new Author(1, "J.R.R.", "Tolkien")),
        new Book(4, "The Fellowship of the Ring", 423, Rating.FIVE_STARTS, new Author(1, "J.R.R.", "Tolkien")),
        new Book(5, "The Two Towers", 352, Rating.FIVE_STARTS, new Author(1, "J.R.R.", "Tolkien")));
  }

}
