package br.com.idelivery.order.user;

import br.com.idelivery.order.model.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class CustomerService {

  private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);


  private final RestTemplate restTemplate;
  private final String customerServiceUrl;

  public CustomerService(RestTemplate restTemplate, String customerServiceUrl) {
    this.restTemplate = restTemplate;
    this.customerServiceUrl = customerServiceUrl;
  }

  public CustomerDTO getById(Long customerId) {
    try {

      //
      String url = customerServiceUrl + "/users/" + customerId;

      ResponseEntity<CustomerDTO> res = restTemplate.getForEntity(url, CustomerDTO.class);

      if(res.getStatusCode().is4xxClientError()){
        throw new RuntimeException("user not found");
      }
      if (res.getStatusCode().is2xxSuccessful()) {
        return res.getBody();
      }

    }catch (RestClientException e){
      logger.error("failed to call user-service", e);
    }
    throw new RuntimeException("failed to get customer");
  }
}
