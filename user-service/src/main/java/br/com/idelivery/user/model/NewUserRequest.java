package br.com.idelivery.user.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class NewUserRequest {
  @NotNull
  @Email
  private String email;
  @NotNull
  private String name;
}
