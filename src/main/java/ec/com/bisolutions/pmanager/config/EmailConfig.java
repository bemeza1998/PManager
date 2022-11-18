package ec.com.bisolutions.pmanager.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig {
  @Getter private final String emailAddress;

  public EmailConfig(@Value("${spring.mail.username}") String emailAddress) {
    this.emailAddress = emailAddress;
  }

  @Bean
  public SimpleMailMessage userAndPasswordTemplateMailBody() {
    String messageBody =
        "<h2 style='text-align: center'>Bienvenido al sistema</h2>"
            + "<p>Su nombre de usuario para ingresar al sistema es: %s</p>"
            + "<p>Su clave generada es: %s</p>";

    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setText(messageBody);

    return mailMessage;
  }
}
