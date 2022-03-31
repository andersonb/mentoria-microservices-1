package br.com.idelivery.order;

import br.com.idelivery.order.entity.Order;
import br.com.idelivery.order.model.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NotificationService {


  private final RestTemplate restTemplate;
  private final String notificationServiceUrl;


  public NotificationService(RestTemplate restTemplate, String notificationServiceUrl) {
    this.restTemplate = restTemplate;
    this.notificationServiceUrl = notificationServiceUrl;
  }

  public void notifyCustomer(CustomerDTO customer, Order order) {

    NotificationDTO dto = new NotificationDTO();
    dto.setTo(customer.getEmail());
    dto.setTitle("Novo Pedido");
    dto.setMessage(String.format("%s, seu pedido id: %s foi aceito!", customer.getName(), order.getId()));

    String url = notificationServiceUrl +"/notifications";

    restTemplate.postForObject(
            url,
            dto,
            NotificationDTO.class
        );
  }
}
