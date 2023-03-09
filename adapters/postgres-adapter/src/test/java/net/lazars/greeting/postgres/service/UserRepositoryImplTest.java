package net.lazars.greeting.postgres.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.outbound.UserRepository;
import net.lazars.greeting.postgres.EntityRepositoryTest;
import net.lazars.greeting.postgres.entity.UserEntity;
import net.lazars.greeting.postgres.repository.UserEntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {UserEntityRepository.class, UserRepositoryImpl.class})
class UserRepositoryImplTest extends EntityRepositoryTest {

  @Autowired private UserEntityRepository userEntityRepository;

  @Autowired private UserRepository userRepository;

  @AfterEach
  void cleanUp() {
    userEntityRepository.deleteAll();
  }

  @Test
  void shouldSaveUser() {
    final User user = User.builder().id("1").name("Johnathan").nicknames(List.of("Johnny")).build();

    assertThat(userEntityRepository.existsById(user.id())).isFalse();

    userRepository.save(user);

    assertThat(userEntityRepository.existsById(user.id())).isTrue();

    final UserEntity userDocument = userEntityRepository.findById(user.id()).orElseThrow();
    assertThat(userDocument.getId()).isEqualTo(user.id());
    assertThat(userDocument.getName()).isEqualTo(user.name());
    assertThat(userDocument.getNicknames()).containsExactlyElementsOf(user.nicknames());
  }

  @Test
  void shouldFindUserById() {
    final User user = User.builder().id("1").name("Johnathan").nicknames(List.of("Johnny")).build();

    assertThat(userRepository.findById(user.id())).isNotPresent();

    userRepository.save(user);

    final User retrievedUser = userRepository.findById(user.id()).orElseThrow();
    assertThat(retrievedUser).isEqualTo(user);
  }

  @Test
  void shouldNotFindUserById_WhenUserDoesNotExist() {
    assertThat(userRepository.findById("1")).isNotPresent();
  }
}
