package com.klu.springmvc;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController
public class RestaurantController {
  private final Map<Integer, Restaurant> restaurants = Map.of(
    1, new Restaurant(1, "Paradise", "Hyderabad"),
    2, new Restaurant(2, "KFC", "Vijayawada"),
    3, new Restaurant(3, "Domino's", "Guntur")
  );
  @GetMapping("/restaurants")
  public List<Restaurant> getRestaurants() { return List.copyOf(restaurants.values()); }
  @GetMapping("/restaurants/{id}")
  public Restaurant getRestaurant(@PathVariable Integer id) {
    Restaurant restaurant = restaurants.get(id);
    if (restaurant == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
    return restaurant;
  }
}
