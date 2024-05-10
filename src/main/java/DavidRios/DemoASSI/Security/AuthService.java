package DavidRios.DemoASSI.Security;

import DavidRios.DemoASSI.DTOs.LoginUser;
import DavidRios.DemoASSI.DTOs.NewUser;
import DavidRios.DemoASSI.Entities.User;
import DavidRios.DemoASSI.Exceptions.BadRequestException;
import DavidRios.DemoASSI.Exceptions.UnauthorizedException;
import DavidRios.DemoASSI.Repositories.UserRepo;
import DavidRios.DemoASSI.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private JWTTools jwtTools;

    public String authentication(LoginUser loginUser) {

        User user = userService.findByEmail(loginUser.email());
        if (bcrypt.matches(loginUser.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Wrong credentials!");
        }
    }

    public User saveUser(NewUser newUser) {
        userRepository.findByEmail(newUser.email()).ifPresent(user -> {
            throw new BadRequestException("The following email address exists already: " + user.getEmail());
        });
        User userToSave = new User(newUser.username(), bcrypt.encode(newUser.password()), newUser.fullName(), newUser.email(), newUser.role());

        return userRepository.save(userToSave);
    }
}