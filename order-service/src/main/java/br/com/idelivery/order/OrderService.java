package br.com.idelivery.order;

import br.com.idelivery.order.entity.Item;
import br.com.idelivery.order.entity.Order;
import br.com.idelivery.order.model.CustomerDTO;
import br.com.idelivery.order.model.ItemDTO;
import br.com.idelivery.order.model.NewOrderRequest;
import br.com.idelivery.order.model.OrderDTO;
import br.com.idelivery.order.model.OrderStatus;
import br.com.idelivery.order.user.CustomerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

  private final OrderRepository orderRepository;
  private final NotificationService notificationService;
  private final CustomerService customerService;

  public OrderService(OrderRepository orderRepository, NotificationService notificationService, CustomerService customerService) {
    this.orderRepository = orderRepository;
    this.notificationService = notificationService;
    this.customerService = customerService;
  }

  public OrderDTO getById(Long id) {
    Order order = getOrder(id);
    CustomerDTO customer = customerService.getById(order.getCustomerId());
    return mapToDto(order, customer);
  }

  private OrderDTO mapToDto(Order order, CustomerDTO customerDTO) {
    OrderDTO dto = new OrderDTO();

    dto.setId(order.getId().toString());
    dto.setStatus(order.getStatus().toString());
    dto.setDate(order.getCreatedAt().toString());
    dto.setCustomerDTO(customerDTO);

    dto.setItemList(
        order.getItemList().stream().map(
            it -> {
              ItemDTO itemDTO = new ItemDTO();
              itemDTO.setName(it.getName());
              itemDTO.setQuantity(it.getQuantity());
              return itemDTO;
            }
        ).collect(Collectors.toList())
    );

    return dto;
  }

  public OrderDTO newOrder(NewOrderRequest newOrderRequest) {

    Long customerId = newOrderRequest.getCustomerId();

    Order order = new Order();
    order.setCreatedAt(LocalDateTime.now());
    order.setStatus(OrderStatus.WAITING);
    order.setCustomerId(customerId);
    order.setItemList(
        newOrderRequest
            .getItemList()
            .stream()
            .map(it -> {
                Item item = new Item();
                item.setName(it.getName());
                item.setQuantity(it.getQuantity());
                item.setOrder(order);
                return item;
              }
            ).collect(Collectors.toList())
    );

    orderRepository.save(order);

    CustomerDTO customer = customerService.getById(customerId);
    notificationService.notifyCustomer(customer, order);

    return mapToDto(order, customer);
  }

  public List<OrderDTO> list() {
    return orderRepository.findAll().stream()
        .map( it -> {
                CustomerDTO customer = customerService.getById(it.getCustomerId());
                return mapToDto(it, customer);
              }
            ).collect(Collectors.toList());
  }

  private Order getOrder(Long orderId){
    return orderRepository.findById(orderId).orElseThrow(
        () -> new RuntimeException(String.format("order with id %s not found", orderId))
    );
  }

  public void updateStatus(Long orderId, OrderStatus newStatus) {
    Order order = getOrder(orderId);

    order.setStatus(newStatus);
    orderRepository.save(order);
  }
}
