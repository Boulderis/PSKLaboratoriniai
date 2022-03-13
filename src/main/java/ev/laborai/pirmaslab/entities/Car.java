package ev.laborai.pirmaslab.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select car from Car as car")
})
@Getter @Setter
@Table(name = "car")
@Entity
public class Car implements Serializable {

    public Car() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="MAKE", nullable = false)
    private String make;
    @Column(name = "MODEL", nullable = false)
    private String model;
    @Column(nullable = false)
    private String plateNumber;
    @ManyToOne
    @JoinColumn(name="DRIVER_ID")
    private Driver driver;
    @ManyToMany
    @JoinTable(name="cars")
    private List<Rider> riders;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
