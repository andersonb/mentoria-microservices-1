package br.com.idelivery.order.model;

import lombok.Data;

@Data
public class ItemDTO {
  private String name;
  private Integer quantity;
}
