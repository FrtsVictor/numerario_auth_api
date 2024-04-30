package numerario.auth.api.auth.modules.test;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import numerario.auth.api.auth.modules.mail.use_cases.SendSimpleEmailUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SendSimpleEmailUseCase sendSimpleEmailUseCase;


    @PostMapping()
    public ResponseEntity<UUID> create(@Valid @RequestBody() SendEmailTestDto emailTestDto) throws MessagingException, UnsupportedEncodingException {
        this.sendSimpleEmailUseCase.execute("victorfreitas.job@gmail.com", "Conta criada com sucesso, aguarde um gestor aprovar seu login!");
//        SimpleMailMessage message = new SimpleMailMessage();
//        System.out.printf(emailTestDto.toString());
//
//        if(emailTestDto.getEmailFrom() != null){message.setFrom(emailTestDto.getEmailFrom());}
//        if(emailTestDto.getEmailTo() != null) {message.setTo(emailTestDto.getEmailTo());}
//        message.re
//
//        message.setSubject("Creation Request");
//        message.setText("TestEmail");
//        emailSender.send(message);
        return ResponseEntity.created(URI.create("/")).body(UUID.randomUUID());
    }
}
