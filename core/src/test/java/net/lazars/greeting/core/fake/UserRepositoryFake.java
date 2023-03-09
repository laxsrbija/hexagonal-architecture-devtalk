package net.lazars.greeting.core.fake;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.outbound.UserRepository;

public class UserRepositoryFake implements UserRepository {

  private final List<User> users = new ArrayList<>();

  @Override
  public Optional<User> findById(final String id) {
    return users.stream().filter(user -> user.id().equals(id)).findFirst();
  }

  @Override
  public void save(final User user) {
    findById(user.id()).ifPresent(users::remove);
    users.add(user);
  }
}
