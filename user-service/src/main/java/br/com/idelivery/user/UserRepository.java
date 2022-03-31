package br.com.idelivery.user;

import br.com.idelivery.user.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository {

  private static final Long INSERTION_ID_FLAG = -1L;
  private final List<User> inMemoryDatabase = new ArrayList<>();

  public List<User> list() {
    return Collections.unmodifiableList(inMemoryDatabase);
  }

  public Optional<User> getById(Long id) {
    return inMemoryDatabase
        .stream()
        .filter(it -> !it.isDeleted())
        .filter(it -> it.getId().equals(id))
        .findFirst();
  }

  public void save(User user) {

    if(INSERTION_ID_FLAG.equals(user.getId())){
      Long nextId = inMemoryDatabase.size() + 1L;
      user.setId(nextId);
    }

    inMemoryDatabase.add(user);
  }

  public Boolean userExist(String userEmail) {
    return inMemoryDatabase
        .stream()
        .filter(it -> !it.isDeleted())
        .anyMatch(it -> it.getEmail().equals(userEmail));
  }
}
