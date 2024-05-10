package DavidRios.DemoASSI.Controllers;

import DavidRios.DemoASSI.DTOs.ModifiedTable;
import DavidRios.DemoASSI.DTOs.NewRestaurantTable;
import DavidRios.DemoASSI.DTOs.OccupantPayload;
import DavidRios.DemoASSI.Entities.RestaurantTable;
import DavidRios.DemoASSI.Services.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TablesController {

    @Autowired
    private RestaurantTableService restaurantTableService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantTable saveTable(@RequestBody ModifiedTable newTable) {
        return this.restaurantTableService.save(newTable);
    }

    @PostMapping("/{tableId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RestaurantTable modifyTable(@PathVariable long tableId, @RequestBody ModifiedTable modifiedTable) {
        return this.restaurantTableService.modifyTable(tableId, modifiedTable);
    }

    @DeleteMapping("/{tableId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTable(@PathVariable long tableId) {
        this.restaurantTableService.deleteTable(tableId);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public List<RestaurantTable> getAllTables() {
        return restaurantTableService.getAllTables();
    }

    @GetMapping("/freeTables")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public List<RestaurantTable> getFreeTables() {
        return restaurantTableService.getAvailableTables();
    }

    @GetMapping("/occupiedTables")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public List<RestaurantTable> getOccupiedTables() {return restaurantTableService.getOccupiedTables();}

    @GetMapping("/{tableId}")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public RestaurantTable getTableById(@PathVariable long tableId) {
        return restaurantTableService.getTableById(tableId);
    }

    @PostMapping("/{tableId}/arriving")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public RestaurantTable arriving(@PathVariable long tableId, @RequestBody OccupantPayload arrivingOccupants ) {
        return restaurantTableService.occupantsIn(tableId, arrivingOccupants.occupants());
    }

    @PostMapping("/{tableId}/departing")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public RestaurantTable departure(@PathVariable long tableId, @RequestBody OccupantPayload departingOccupants) {
        return restaurantTableService.occupantsOut(tableId, departingOccupants.occupants());
    }
}
