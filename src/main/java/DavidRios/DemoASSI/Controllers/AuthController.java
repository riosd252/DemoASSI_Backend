package DavidRios.DemoASSI.Controllers;

import DavidRios.DemoASSI.DTOs.LoginToken;
import DavidRios.DemoASSI.DTOs.LoginUser;
import DavidRios.DemoASSI.DTOs.NewUser;
import DavidRios.DemoASSI.Entities.User;
import DavidRios.DemoASSI.Security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public LoginToken login(@RequestBody LoginUser loginUser) {
        return new LoginToken(authService.authentication(loginUser));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody NewUser newUser) {
        return this.authService.saveUser(newUser);
    }
}