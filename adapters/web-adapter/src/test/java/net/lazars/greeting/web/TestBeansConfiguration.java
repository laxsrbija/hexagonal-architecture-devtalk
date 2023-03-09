package net.lazars.greeting.web;

import net.lazars.greeting.core.fake.UserRepositoryFake;
import net.lazars.greeting.core.port.inbound.NameService;
import net.lazars.greeting.core.port.inbound.UserCreationService;
import net.lazars.greeting.core.port.outbound.UserRepository;
import net.lazars.greeting.core.service.NameServiceImpl;
import net.lazars.greeting.core.service.UserCreationServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestBeansConfiguration {

  @Bean
  UserRepository userRepository() {
    return new UserRepositoryFake();
  }

  @Bean
  NameService nameService(final UserRepository userRepository) {
    return new NameServiceImpl(userRepository);
  }

  @Bean
  UserCreationService userCreationService(final UserRepository userRepository) {
    return new UserCreationServiceImpl(userRepository);
  }
}
