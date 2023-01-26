package my.gymService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GymServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(GymServiceApplication.class, args);
  }
}
