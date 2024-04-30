package numerario.auth.api.auth.modules.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailTestDto {
    private String emailTo;
    private String emailFrom;
}
