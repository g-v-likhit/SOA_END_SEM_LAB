package com.klu.springmvc;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/orders")
public class OrderController {
  private final ConcurrentHashMap<Integer, FoodOrder> orders = new ConcurrentHashMap<>();
  private final AtomicInteger nextId = new AtomicInteger(1);
  private final RestClient restaurantClient;
  public OrderController(RestClient.Builder builder, @Value("${restaurant.service.url}") String url) {
    this.restaurantClient = builder.baseUrl(url).build();
  }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FoodOrder createOrder(@Valid @RequestBody CreateOrderRequest request) {
    RestaurantResponse restaurant;
    try {
      restaurant = restaurantClient.get().uri("/restaurants/{id}", request.restaurantId())
        .retrieve().body(RestaurantResponse.class);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found or Restaurant Service unavailable");
    }
    int id = nextId.getAndIncrement();
    FoodOrder order = new FoodOrder(id, restaurant.restaurantId(), restaurant.restaurantName(),
      request.customerName(), request.itemName(), request.quantity(), "PLACED");
    orders.put(id, order);
    return order;
  }
  @GetMapping
  public List<FoodOrder> getOrders() {
    return orders.values().stream().sorted((a,b) -> a.orderId().compareTo(b.orderId())).toList();
  }
  @GetMapping("/{id}")
  public FoodOrder getOrder(@PathVariable Integer id) {
    FoodOrder order = orders.get(id);
    if (order == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
    return order;
  }
}
