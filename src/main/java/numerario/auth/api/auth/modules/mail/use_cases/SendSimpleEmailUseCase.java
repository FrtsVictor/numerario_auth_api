package numerario.auth.api.auth.modules.mail.use_cases;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import numerario.auth.api.auth.modules.mail.exceptions.SendMailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendSimpleEmailUseCase {

    private final JavaMailSender mailSender;

    SendSimpleEmailUseCase(JavaMailSender mailSender){
        this.mailSender=mailSender;
    }

    public void execute(String emailTo, String emailText) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom("albacet_assistance@zohomail.com", "app numerario");
            helper.setTo(emailTo);
            helper.setText(emailText, false);
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            throw new SendMailException();
        }

    }

}
