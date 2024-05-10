package DavidRios.DemoASSI.Repositories;

import DavidRios.DemoASSI.Entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantTableRepo extends JpaRepository<RestaurantTable, Long> {

    @Query("SELECT r FROM RestaurantTable r WHERE r.status = 'FREE' OR r.status = 'PARTIALLY_OCCUPIED'")
    public List<RestaurantTable> findFreeTables();

    @Query("SELECT r FROM RestaurantTable r WHERE r.status = 'FULL' OR r.status = 'PARTIALLY_OCCUPIED'")
    public List<RestaurantTable> findOccupiedTables();

    @Query("SELECT r FROM RestaurantTable r ORDER BY r.tableNumber ")
    public List<RestaurantTable> findOrderedTables();
}
