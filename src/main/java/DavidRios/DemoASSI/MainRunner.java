package DavidRios.DemoASSI;

import DavidRios.DemoASSI.DTOs.NewUser;
import DavidRios.DemoASSI.Entities.RestaurantTable;
import DavidRios.DemoASSI.Repositories.RestaurantTableRepo;
import DavidRios.DemoASSI.Security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainRunner implements CommandLineRunner {

    @Autowired
    private RestaurantTableRepo service;

    @Autowired
    private AuthService authService;

    @Override
    public void run(String... args) throws Exception {

        this.authService.saveUser(new NewUser("riosd252","12345","David Amaru Rios Saenz", "riosd252@gmail.com", "ADMIN"));

        this.service.save(new RestaurantTable("1",6));
        this.service.save(new RestaurantTable("2",2));
        this.service.save(new RestaurantTable("3",8));
        this.service.save(new RestaurantTable("4",10));
        this.service.save(new RestaurantTable("5",4));

    }
}
