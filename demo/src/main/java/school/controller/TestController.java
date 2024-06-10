package school.controller;
/*
import school.security.impl.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test Controller", description = "API endpoints for testing purposes")
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    private static UserDetailsImpl getCallingUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            return (UserDetailsImpl) principal;
        }
        return null;
    }

    @Operation(summary = "Get user information (protected for users only)")
    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getProtectedForUsersOnly() {
        UserDetailsImpl userDetails = getCallingUser();
        return ResponseEntity.ok().body(userDetails != null ? userDetails.getEmail() : "Unauthorized");
    }

    @Operation(summary = "Get user information (protected for admins only)")
    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getProtectedForAdmins() {
        UserDetailsImpl userDetails = getCallingUser();
        return ResponseEntity.ok().body(userDetails != null ? userDetails.getEmail() : "Unauthorized");
    }

    @Operation(summary = "Get user information (protected for everyone)")
    @GetMapping("/everyone")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> getProtectedForEveryone() {
        UserDetailsImpl userDetails = getCallingUser();
        return ResponseEntity.ok().body(userDetails != null ? userDetails.getEmail() : "Unauthorized");
    }

    @Operation(summary = "Get public information")
    @GetMapping("/public")
    public ResponseEntity<String> getPublic() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "Unknown";
        if (principal instanceof UserDetailsImpl) {
            email = ((UserDetailsImpl) principal).getEmail();
        }
        return ResponseEntity.ok().body(email);
    }
}*/
