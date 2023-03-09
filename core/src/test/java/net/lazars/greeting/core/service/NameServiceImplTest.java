package net.lazars.greeting.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.lazars.greeting.core.fake.UserRepositoryFake;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.inbound.NameService;
import net.lazars.greeting.core.port.outbound.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameServiceImplTest {

  private static final User USER =
      User.builder()
          .id("1")
          .name("Suzanne")
          .nicknames(List.of("Sue", "Susie", "Suzy", "Suzie"))
          .build();

  private UserRepository userRepository;
  private NameService nameService;

  @BeforeEach
  void init() {
    userRepository = new UserRepositoryFake();
    nameService = new NameServiceImpl(userRepository);
  }

  @Test
  void shouldGetName() {
    userRepository.save(USER);

    final String name = nameService.getName(USER.id());
    assertThat(name).isEqualTo(USER.name());
  }

  @Test
  void shouldGetNickname() {
    userRepository.save(USER);

    final String nickname = nameService.getNickname(USER.id());
    assertThat(nickname).isIn(USER.nicknames());
  }
}
