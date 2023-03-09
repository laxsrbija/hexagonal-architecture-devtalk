package net.lazars.greeting.config;

import jakarta.annotation.PostConstruct;
import java.util.List;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.inbound.UserCreationService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

  private final UserCreationService userCreationService;

  public DataConfig(final UserCreationService userCreationService) {
    this.userCreationService = userCreationService;
  }

  @PostConstruct
  private void init() {
    final List<User> users =
        List.of(
            User.builder()
                .id("1")
                .name("Benjamin")
                .nicknames(List.of("Ben", "Benji", "Benny"))
                .build(),
            User.builder()
                .id("2")
                .name("James")
                .nicknames(List.of("Jay", "Jim", "Jimmy", "Jamie"))
                .build(),
            User.builder()
                .id("3")
                .name("Daniel")
                .nicknames(List.of("Danny", "Dan", "Dane"))
                .build());

    userCreationService.saveAll(users);
  }
}
