package com.klu.springmvc;
public record FoodOrder(Integer orderId, Integer restaurantId, String restaurantName,
  String customerName, String itemName, Integer quantity, String status) {}
