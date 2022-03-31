package br.com.idelivery.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  UserService userService(){
    return new UserService(new UserRepository());
  }
}
