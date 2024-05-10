package DavidRios.DemoASSI.Entities;

import DavidRios.DemoASSI.Enums.TableStatus;
import DavidRios.DemoASSI.Exceptions.TableException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant_tables")
@NoArgsConstructor
@Getter
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Setter
    private String tableNumber;

    @Setter
    private int capacity;

    private int occupiedSeats;

    private int freeSeats;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

    public RestaurantTable(String tableNumber, int capacity) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.occupiedSeats = 0;
        this.freeSeats = getFreeSeats();
        this.status = TableStatus.FREE;
    }

    @Override
    public String toString() {
        return "RestaurantTable{" +
                "id=" + id +
                ", tableNumber='" + tableNumber + '\'' +
                ", capacity=" + capacity +
                ", occupiedSeats=" + occupiedSeats +
                ", freeSeats=" + freeSeats +
                ", status=" + status +
                '}';
    }

    public int getFreeSeats() {
        return this.capacity - this.occupiedSeats;
    }


    public void addOccupants(int occupants) {
        if (status == TableStatus.FREE || status == TableStatus.PARTIALLY_OCCUPIED && occupants <= getFreeSeats() && occupants > 0) {
            this.occupiedSeats += occupants;
            setStatus();
        } else {
            throw new TableException("Please verify the number of arriving occupants and the table's actual occupancy.");
        }
    }


    public void removeOccupants(int departingOccupants)
    {
        if (status != TableStatus.FULL && (status != TableStatus.PARTIALLY_OCCUPIED || departingOccupants > occupiedSeats || departingOccupants <= 0))
            throw new TableException("Please verify the number of departing occupants and the table's actual occupancy.");

        this.occupiedSeats -= departingOccupants;
        setStatus();
    }

    private void setStatus() {
        if (getFreeSeats() == capacity) {
            status = TableStatus.FREE;
        } else if (getFreeSeats() < capacity && getFreeSeats() > 0 ) {
            status = TableStatus.PARTIALLY_OCCUPIED;
        } else {
            status = TableStatus.FULL;
        }
    }
}
