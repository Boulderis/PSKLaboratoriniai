package ev.laborai.pirmaslab.services;

import ev.laborai.pirmaslab.entities.Car;
import ev.laborai.pirmaslab.persistence.CarsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class RideCalculator implements Serializable {
    @Inject
    private CarsDAO carsDAO;
    
    public Car findBestRide() {
        Car car = null;
        try {
            Thread.sleep(5000);
            long min = 0L;
            long max = carsDAO.Size();
            long pick = min + (long) (Math.random() * (max - min));
            car = carsDAO.findOne(pick);
        } catch(InterruptedException e) {}
        return car;
    }

}
