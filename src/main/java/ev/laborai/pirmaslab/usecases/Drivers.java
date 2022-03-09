package ev.laborai.pirmaslab.usecases;

import ev.laborai.pirmaslab.entities.Driver;
import ev.laborai.pirmaslab.persistence.DriversDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Drivers {

    @Inject
    private DriversDAO driversDAO;

    @Getter
    @Setter
    private Driver driverToCreate = new Driver();

    @Getter
    private List<Driver> allDrivers;

    @PostConstruct
    public void init(){
        loadAllDrivers();
    }

    @Transactional
    public void createDriver(){
        this.driversDAO.persist(driverToCreate);
    }

    private void loadAllDrivers(){
        this.allDrivers = driversDAO.loadAll();
    }

}
