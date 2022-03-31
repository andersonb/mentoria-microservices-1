package br.com.idelivery.order.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
  private String id;
  private CustomerDTO customerDTO;
  private String date;
  private String status;
  private List<ItemDTO> itemList;
}
