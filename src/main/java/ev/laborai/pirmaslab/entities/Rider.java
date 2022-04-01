package ev.laborai.pirmaslab.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Rider.findAll", query = "select rider from Rider as rider")
})
@Entity
@Table(name = "rider")
@Getter
@Setter
public class Rider implements Serializable {

    public Rider() {

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
    @Column(name="NICKNAME", nullable = false)
    private String nickname;
    @ManyToMany(mappedBy = "riders")
    private List<Car> cars;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rider rider = (Rider) o;
        return Objects.equals(id, rider.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}
