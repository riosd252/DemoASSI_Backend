package DavidRios.DemoASSI.Controllers;

import DavidRios.DemoASSI.Services.UserService;
import DavidRios.DemoASSI.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/me")
    public User getSelfProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public User updateSelfProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody User updatedUser) {
        return this.userService.update(currentAuthenticatedUser.getId(), updatedUser);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSelf(@AuthenticationPrincipal User currentAuthenticatedUser) {
        this.userService.delete(currentAuthenticatedUser.getId());
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable UUID id) {
        return this.userService.findById(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User update(@PathVariable UUID id, @RequestBody User updatedUser) {
        return this.userService.update(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        this.userService.delete(id);
    }
}