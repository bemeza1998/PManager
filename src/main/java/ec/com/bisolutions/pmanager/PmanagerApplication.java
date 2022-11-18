package ec.com.bisolutions.pmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PmanagerApplication {

  // @Autowired JobLauncher jobLauncher;

  public static void main(String[] args) {
    SpringApplication.run(PmanagerApplication.class, args);
  }
}
