package br.com.idelivery.user;

import br.com.idelivery.user.entity.User;
import br.com.idelivery.user.model.NewUserRequest;
import br.com.idelivery.user.model.UserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void create(NewUserRequest newUserRequest) {

    String userEmail = newUserRequest.getEmail();
    Boolean userAlreadyExist = userRepository.userExist(userEmail);

    if(userAlreadyExist){
      throw new RuntimeException(String.format("user with email %s already registered", userEmail));
    }

    User user = new User();
    user.setId(-1L);
    user.setEmail(userEmail);
    user.setName(newUserRequest.getName());
    user.setCreatedAt(LocalDateTime.now());
    user.setDeleted(false);

    userRepository.save(user);
  }

  public UserDTO getById(Long id) {
    return userRepository.getById(id)
            .map(this::map)
            .orElseThrow(
                () -> new RuntimeException(String.format("user with id %s not found", id))
            );
  }

  public List<UserDTO> list() {
    return userRepository.list().stream().map(this::map).collect(Collectors.toList());
  }

  public UserDTO map(User user){
    UserDTO dto =  new UserDTO();
    dto.setEmail(user.getEmail());
    dto.setName(user.getName());
    dto.setId(user.getId());
    return dto;
  }
}
