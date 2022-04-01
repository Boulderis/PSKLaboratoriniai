package ev.laborai.pirmaslab.usecases;

import ev.laborai.pirmaslab.entities.Driver;
import ev.laborai.pirmaslab.entities.Rider;
import ev.laborai.pirmaslab.persistence.DriversDAO;
import ev.laborai.pirmaslab.persistence.RidersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class DriversAndRiders {

    @Inject
    private DriversDAO driversDAO;

    @Inject
    private RidersDAO ridersDAO;

    @Getter
    @Setter
    private Driver driverToCreate = new Driver();

    @Getter
    @Setter
    private Rider riderToCreate = new Rider();

    @Getter
    private List<Driver> allDrivers;

    @Getter
    private List<Rider> allRiders;

    @PostConstruct
    public void init(){
        loadAllDrivers();
        loadAllRiders();
    }


    @Transactional
    public void createDriver(){
        this.driversDAO.persist(driverToCreate);
    }

    private void loadAllDrivers(){
        this.allDrivers = driversDAO.loadAll();
    }

    @Transactional
    public void createRider() {
        ridersDAO.persist(riderToCreate);
    }

    private void loadAllRiders(){
        allRiders = ridersDAO.loadAll();
    }

}
