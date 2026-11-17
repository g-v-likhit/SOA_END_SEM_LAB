package com.klu.springmvc;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record CreateOrderRequest(@NotNull Integer restaurantId, @NotBlank String customerName,
  @NotBlank String itemName, @NotNull @Min(1) Integer quantity) {}
