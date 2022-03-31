package br.com.idelivery.notification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  NotificationService notificationService(){
    return new NotificationService();
  }
}
