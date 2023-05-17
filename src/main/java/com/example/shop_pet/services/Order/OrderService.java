package com.example.shop_pet.services.Order;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.shop_pet.models.Order;
import com.example.shop_pet.models.OrderItem;
import com.example.shop_pet.services.User.UserService;

@Service
public class OrderService {
  Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public Optional<Order> selectOrderUnpaidByUserId(String userId) {
    logger.info("OrderService ce is running...");
    String sql = """
              SELECT * 
              FROM orders 
              WHERE user_id = ?
              """;
    Optional<Order> order = jdbcTemplate.query(sql, new OrderRowMapper(), userId)
                            .stream()
                            .findFirst();
  
    return order;
  }

  public int insertOrderItems(OrderItem orderItem) {
    logger.info("OrderService insertOrderItems method is running...");
    String sql = """
              INSERT INTO order_items (order_id, product_id, quantity)
              VALUES (?, ?, ?) 
              """;
    return jdbcTemplate.update(sql, orderItem.getOrderId(), orderItem.getProductId(), orderItem.getQuantity());
  }
  
  public int insertOrder(Order order) {
    logger.info("OrderService insertOrder method is running...");
    String sql = """
              INSERT INTO orders (user_id, is_free_shipping, payment_status, total)
              VALUES (?, ?, CAST(? AS payment_status_enum), ?) 
              """;
    return jdbcTemplate.update(sql, order.getUserId(), order.isFreeShipping(), order.getPaymentStatus().toString(), order.getTotal());
    // return jdbcTemplate.update(sql, order.getUserId(), order.isFreeShipping(), OrderEnum.pending, order.getTotal());
  }
}