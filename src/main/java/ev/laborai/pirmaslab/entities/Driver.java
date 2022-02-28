package ev.laborai.pirmaslab.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "driver")
@Getter @Setter
public class Driver {

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

}
