package br.com.idelivery.order;

import lombok.Data;

@Data
public class NotificationDTO {

  private String to;
  private String title;
  private String message;
}
