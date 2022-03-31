package br.com.idelivery.notification;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NotificationRequest {
  @NotNull
  private String to;
  private String title;
  private String message;
}
