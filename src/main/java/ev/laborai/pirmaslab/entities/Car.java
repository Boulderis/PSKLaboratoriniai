package ev.laborai.pirmaslab.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter
@Table(name = "car")
@Entity
public class Car {

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
    @JoinColumn(name="DRIVER_ID", foreignKey = @ForeignKey(name = "FK_DRIVER_ID"))
    private Driver driver;

}
