package br.com.idelivery.user.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDTO {
  private Long id;
  private String email;
  private String name;
}
