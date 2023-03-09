package net.lazars.greeting.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.lazars.greeting.core.fake.UserRepositoryFake;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.inbound.UserCreationService;
import net.lazars.greeting.core.port.outbound.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserCreationServiceImplTest {

  private UserRepository userRepository;
  private UserCreationService userCreationService;

  @BeforeEach
  void init() {
    userRepository = new UserRepositoryFake();
    userCreationService = new UserCreationServiceImpl(userRepository);
  }

  @Test
  void shouldSaveCreatedUser() {
    final User user1 =
        User.builder().id("1").name("Johnathan").nicknames(List.of("Johnny")).build();
    final User user2 =
        User.builder().id("2").name("Michael").nicknames(List.of("Mike")).build();

    assertThat(userRepository.findById("1")).isEmpty();
    assertThat(userRepository.findById("2")).isEmpty();

    userCreationService.saveAll(List.of(user1, user2));

    assertThat(userRepository.findById("1").orElseThrow()).isEqualTo(user1);
    assertThat(userRepository.findById("2").orElseThrow()).isEqualTo(user2);
  }
}
