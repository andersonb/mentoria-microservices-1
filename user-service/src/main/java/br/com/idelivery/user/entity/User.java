package br.com.idelivery.user.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
  private Long id;
  private String email;
  private String name;
  private LocalDateTime createdAt;
  private boolean deleted;
}
