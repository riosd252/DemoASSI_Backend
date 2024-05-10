package DavidRios.DemoASSI.Services;

import DavidRios.DemoASSI.DTOs.ModifiedTable;
import DavidRios.DemoASSI.DTOs.NewRestaurantTable;
import DavidRios.DemoASSI.Entities.RestaurantTable;
import DavidRios.DemoASSI.Repositories.RestaurantTableRepo;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTableService {

    @Autowired
    private RestaurantTableRepo restaurantTableRepo;

    public RestaurantTable save(ModifiedTable newTable) {
        RestaurantTable table = new RestaurantTable(newTable.tableNum(), newTable.capacity());
        return restaurantTableRepo.save(table);
    }

    public RestaurantTable getTableById(long tableId) {
        return restaurantTableRepo.findById(tableId).orElseThrow(() -> new RuntimeException("Table not found."));
    }

    public List<RestaurantTable> getAllTables() {
        return restaurantTableRepo.findOrderedTables();
    }

    public List<RestaurantTable> getAvailableTables() {
        return this.restaurantTableRepo.findFreeTables();
    }

    public List<RestaurantTable> getOccupiedTables() { return this.restaurantTableRepo.findOccupiedTables();}

    public RestaurantTable occupantsIn(long tableId, int arrivingOccupants) {
        RestaurantTable table = this.getTableById(tableId);
        table.addOccupants(arrivingOccupants);
        return restaurantTableRepo.save(table);
    }

    public RestaurantTable occupantsOut(long tableId, int departingOccupants) {
        RestaurantTable table = this.getTableById(tableId);
        table.removeOccupants(departingOccupants);
        return restaurantTableRepo.save(table);
    }

    public RestaurantTable modifyTable(long tableId, ModifiedTable modifiedTable) {
        RestaurantTable table = this.getTableById(tableId);
        table.setTableNumber(modifiedTable.tableNum());
        table.setCapacity(modifiedTable.capacity());
        return restaurantTableRepo.save(table);
    }

    public void deleteTable(long tableId) {
       RestaurantTable table = this.getTableById(tableId);
       restaurantTableRepo.delete(table);
    }
}
