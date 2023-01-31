package my.gymService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class WithTransaction {

  @Transactional
  public void run(Runnable c) {
    c.run();
  }
}
