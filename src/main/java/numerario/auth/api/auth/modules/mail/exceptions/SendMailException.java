package numerario.auth.api.auth.modules.mail.exceptions;

public class SendMailException extends  RuntimeException {
    public SendMailException(){
        super("Erro ao enviar email");
    }
}
