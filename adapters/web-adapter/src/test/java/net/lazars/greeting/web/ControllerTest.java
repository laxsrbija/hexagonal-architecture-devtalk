package net.lazars.greeting.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.lazars.greeting.web.config.ControllerResponseExceptionHandler;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableWebMvc
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(
    classes = {
      ControllerResponseExceptionHandler.class,
      TestBeansConfiguration.class,
      TestConfiguration.class,
      ThymeleafAutoConfiguration.class
    })
public @interface ControllerTest {

  @AliasFor(annotation = SpringBootTest.class, attribute = "classes")
  Class<?>[] value();
}
