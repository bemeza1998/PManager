package ec.com.bisolutions.pmanager.seguridad.services;

import ec.com.bisolutions.pmanager.config.EmailConfig;
import java.util.Objects;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender emailSender;
  private final SimpleMailMessage template;
  private final EmailConfig emailConfig;

  @Async
  public void enviarClaveGenerada(
      String userEmail, String nombre, String clave, String nombreUsuario)
      throws MessagingException {
    MimeMessage mimeMessage = emailSender.createMimeMessage();
    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");

    String emailBody =
        String.format(Objects.requireNonNull(template.getText()), nombreUsuario, clave);

    message.setFrom(this.emailConfig.getEmailAddress());
    message.setTo(userEmail);
    message.setSubject("Creación de nuevo usuario " + nombre);
    message.setText(emailBody, true);

    emailSender.send(mimeMessage);
  }
}
