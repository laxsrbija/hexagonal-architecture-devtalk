package net.lazars.greeting.core.port.outbound;

import java.util.Optional;
import net.lazars.greeting.core.model.User;

public interface UserRepository {

  Optional<User> findById(String id);

  void save(User user);
}
