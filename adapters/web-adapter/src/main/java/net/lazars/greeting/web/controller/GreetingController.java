package net.lazars.greeting.web.controller;

import lombok.RequiredArgsConstructor;
import net.lazars.greeting.core.port.inbound.NameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class GreetingController {

  private final NameService nameService;

  @GetMapping("greet/{id}")
  public String greetByName(@PathVariable final String id, final Model model) {
    model.addAttribute("name", nameService.getName(id));
    return "hello";
  }

  @GetMapping("greet/{id}/nickname")
  public String greetByNickname(@PathVariable final String id, final Model model) {
    model.addAttribute("name", nameService.getNickname(id));
    return "hello";
  }
}
