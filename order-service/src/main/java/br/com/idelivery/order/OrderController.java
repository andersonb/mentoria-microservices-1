package br.com.idelivery.order;

import br.com.idelivery.order.model.NewOrderRequest;
import br.com.idelivery.order.model.OrderDTO;
import br.com.idelivery.order.model.OrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }


  @PostMapping
  public ResponseEntity newOrder(@RequestBody @Validated NewOrderRequest newOrderRequest){
    OrderDTO order = orderService.newOrder(newOrderRequest);
    return ResponseEntity.ok(order);
  }

  @PutMapping("/{orderId}")
  public ResponseEntity updateStatus(
      @PathVariable Long orderId,
      @PathParam("newStatus") OrderStatus newStatus){
    orderService.updateStatus(orderId, newStatus);
    return ResponseEntity.accepted().build();
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderDTO> getById(@PathVariable Long orderId){
    OrderDTO order = orderService.getById(orderId);
    return ResponseEntity.ok(order);
  }

  @GetMapping
  public ResponseEntity<List<OrderDTO>> list(){
    List<OrderDTO> list = orderService.list();
    return ResponseEntity.ok(list);
  }
}
