package net.lazars.greeting.core.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.inbound.NameService;
import net.lazars.greeting.core.port.outbound.UserRepository;

@RequiredArgsConstructor
public class NameServiceImpl implements NameService {

  private final Random random = new Random();
  private final UserRepository userRepository;

  @Override
  public String getName(final String id) {
    return userRepository.findById(id).map(User::name).orElseThrow();
  }

  @Override
  public String getNickname(final String id) {
    return userRepository
        .findById(id)
        .map(User::nicknames)
        .map(this::pickRandomNickname)
        .orElseThrow();
  }

  private String pickRandomNickname(final List<String> nicknames) {
    return nicknames.get(random.nextInt(nicknames.size()));
  }
}
