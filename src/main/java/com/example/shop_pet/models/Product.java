package com.example.shop_pet.models;

import org.springframework.ui.ModelExtensionsKt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
  private String id;
  private String title;
  private double price;
  private String imageUrl;
  private String moneyType;
  private String createdAt;
  private String updatedAt;

  public Product(String id, String title, double price, String imageUrl, String moneyType, String createdAt,
      String updatedAt) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.imageUrl = imageUrl;
    this.moneyType = moneyType;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
