package net.lazars.greeting.core.port.inbound;

import java.util.List;import net.lazars.greeting.core.model.User;

public interface UserCreationService {

  void saveAll(List<User> users);
}
