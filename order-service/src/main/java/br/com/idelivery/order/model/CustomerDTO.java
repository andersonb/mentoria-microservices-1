package br.com.idelivery.order.model;

import lombok.Data;

@Data
public class CustomerDTO {
  private Long id;
  private String email;
  private String name;
}
