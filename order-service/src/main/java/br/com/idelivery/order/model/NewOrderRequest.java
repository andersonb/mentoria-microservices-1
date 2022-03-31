package br.com.idelivery.order.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class NewOrderRequest {

  private Long customerId;
  private List<ItemRequest> itemList;
}
