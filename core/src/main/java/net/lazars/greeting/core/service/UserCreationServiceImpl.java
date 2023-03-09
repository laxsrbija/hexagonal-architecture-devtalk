package net.lazars.greeting.core.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.inbound.UserCreationService;
import net.lazars.greeting.core.port.outbound.UserRepository;

@RequiredArgsConstructor
public class UserCreationServiceImpl implements UserCreationService {

  private final UserRepository userRepository;

  @Override
  public void saveAll(final List<User> users) {
    users.forEach(userRepository::save);
  }
}
