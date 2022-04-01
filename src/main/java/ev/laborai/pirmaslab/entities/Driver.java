package ev.laborai.pirmaslab.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Driver.findAll", query = "select driver from Driver as driver")
})
@Entity
@Table(name = "driver")
@Getter @Setter
public class Driver {

    public Driver() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @Column(name="NAME", nullable = false)
    private String name;
    @Size(max = 100)
    @Column(name="SURNAME", nullable = false)
    private String surname;
    @OneToMany(mappedBy = "driver")
    private List<Car> cars;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
