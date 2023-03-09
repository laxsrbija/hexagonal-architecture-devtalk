package net.lazars.greeting.mongo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.outbound.UserRepository;
import net.lazars.greeting.mongo.DocumentRepositoryTest;
import net.lazars.greeting.mongo.document.UserDocument;
import net.lazars.greeting.mongo.repository.UserDocumentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {UserDocumentRepository.class, UserRepositoryImpl.class})
class UserRepositoryImplTest extends DocumentRepositoryTest {

  @Autowired private UserDocumentRepository userDocumentRepository;

  @Autowired private UserRepository userRepository;

  @AfterEach
  void cleanUp() {
    userDocumentRepository.deleteAll();
  }

  @Test
  void shouldSaveUser() {
    final User user = User.builder().id("1").name("Johnathan").nicknames(List.of("Johnny")).build();

    assertThat(userDocumentRepository.existsById(user.id())).isFalse();

    userRepository.save(user);

    assertThat(userDocumentRepository.existsById(user.id())).isTrue();

    final UserDocument userDocument = userDocumentRepository.findById(user.id()).orElseThrow();
    assertThat(userDocument.getId()).isEqualTo(user.id());
    assertThat(userDocument.getName()).isEqualTo(user.name());
    assertThat(userDocument.getNicknames()).isEqualTo(user.nicknames());
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
