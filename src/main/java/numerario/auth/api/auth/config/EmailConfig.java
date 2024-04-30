package numerario.auth.api.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    private final String host;
    private final int port;
    private final String username;
    private final String password;

    public EmailConfig(
            @Value("${app.mail.host}") String host,
            @Value("${app.mail.port}") String port,
            @Value("${app.mail.username}") String username,
            @Value("${app.mail.password}") String password) {
        this.host = host;
        this.port = Integer.parseInt(port);
        this.password = password;
        this.username = username;
    }


    @Bean
    public JavaMailSender getJavaMailSender() {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(this.host);
            mailSender.setPort(this.port);
            mailSender.setUsername(this.username);
            mailSender.setPassword(this.password);

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            return mailSender;
        } catch (Exception exception) {
            System.out.printf(exception.toString());
            throw exception;
        }
    }

}
