package net.lazars.greeting.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.inbound.UserCreationService;
import net.lazars.greeting.web.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@ControllerTest(GreetingController.class)
class GreetingControllerTest {

  private static final User USER =
      User.builder().id("1").name("Johnathan").nicknames(List.of("Johnny")).build();

  @Autowired private MockMvc mockMvc;

  @Autowired private UserCreationService userCreationService;

  @Test
  void shouldGreetByName() throws Exception {
    userCreationService.saveAll(List.of(USER));

    mockMvc
        .perform(get("/greet/{id}", USER.id()))
        .andExpect(status().isOk())
        .andExpect(content().string("<h1>Hello, <span>" + USER.name() + "</span>!</h1>"));
  }

  @Test
  void shouldGreetByNickName() throws Exception {
    userCreationService.saveAll(List.of(USER));

    mockMvc
        .perform(get("/greet/{id}/nickname", USER.id()))
        .andExpect(status().isOk())
        .andExpect(
            content().string("<h1>Hello, <span>" + USER.nicknames().get(0) + "</span>!</h1>"));
  }
}
