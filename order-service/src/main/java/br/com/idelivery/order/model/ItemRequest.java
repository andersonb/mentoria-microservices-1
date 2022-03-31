package br.com.idelivery.order.model;

import lombok.Data;

@Data
public class ItemRequest {
  private String name;
  private Integer quantity;
}
