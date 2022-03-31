package br.com.idelivery.user;

import br.com.idelivery.user.model.NewUserRequest;
import br.com.idelivery.user.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity register(@RequestBody @Validated NewUserRequest newUserRequest){
      userService.create(newUserRequest);
      return ResponseEntity.accepted().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getById(@PathVariable Long id){
    UserDTO user = userService.getById(id);
    return ResponseEntity.ok(user);
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> list(){
    List<UserDTO> list = userService.list();
    return ResponseEntity.ok(list);
  }

}
