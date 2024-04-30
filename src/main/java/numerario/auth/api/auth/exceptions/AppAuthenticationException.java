package numerario.auth.api.auth.exceptions;

public class AppAuthenticationException extends RuntimeException {

    public AppAuthenticationException() {
        super("Invalid username or password");
    }

}
