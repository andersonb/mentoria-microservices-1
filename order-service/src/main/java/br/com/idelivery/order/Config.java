package br.com.idelivery.order;

import br.com.idelivery.order.user.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

  private static final Logger logger = LoggerFactory.getLogger(Config.class);

  @Bean
  OrderService orderService(OrderRepository orderRepository){

    RestTemplate restTemplate = new RestTemplateBuilder().interceptors((request, body, execution) -> {
      logger.info("HTTP REQUEST {} - {}", request.getMethod(), request.getURI());
      return execution.execute(request, body);
    }).build();

    //
    String customerServiceUrl = "http://localhost:8000/user-service";
    String notificationServiceUrl = "http://localhost:8000/notification-service";

    CustomerService customerService = new CustomerService(restTemplate, customerServiceUrl);
    NotificationService notificationService = new NotificationService(restTemplate, notificationServiceUrl);

    return new OrderService(
        orderRepository,
        notificationService,
        customerService
    );
  }
}
