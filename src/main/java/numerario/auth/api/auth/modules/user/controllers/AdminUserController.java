package numerario.auth.api.auth.modules.user.controllers;


import jakarta.servlet.http.HttpServletRequest;
import numerario.auth.api.auth.modules.user.dto.UserProfileResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
@RequestMapping("/admin/user")
public class AdminUserController {

//    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<UserProfileResponseDto> get(HttpServletRequest request) {
//        var userId = request.getAttribute("user_id");
//        var user = this.userProfileUseCase.execute(UUID.fromString(userId.toString()));
//
//        return ResponseEntity.ok().body(user);
//    }
}
