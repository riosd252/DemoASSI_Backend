package DavidRios.DemoASSI.Services;

import DavidRios.DemoASSI.Entities.User;
import DavidRios.DemoASSI.Exceptions.NotFoundException;
import DavidRios.DemoASSI.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) { return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with " + email + " was not found."));}

    public User update(UUID id, User modifiedUser) {
        User found = this.findById(id);
        found.setUsername(modifiedUser.getUsername());
        found.setPassword(modifiedUser.getPassword());
        found.setFullName(modifiedUser.getFullName());
        found.setEmail(modifiedUser.getEmail());
        return userRepository.save(found);
    }

    public void delete(UUID id) {
        User found = this.findById(id);
        userRepository.delete(found);
    }

}