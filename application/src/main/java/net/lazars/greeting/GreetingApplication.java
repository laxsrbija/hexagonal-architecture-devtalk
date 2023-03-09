package net.lazars.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.context.annotation.ComponentScan;import org.springframework.context.annotation.ComponentScan.Filter;import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
		includeFilters =
		@Filter(
				type = FilterType.REGEX,
				pattern = {"net.lazars.greeting.core.service.*"}))
public class GreetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingApplication.class, args);
	}

}
