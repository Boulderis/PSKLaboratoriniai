package ev.laborai.pirmaslab.usecases;

import ev.laborai.pirmaslab.entities.Car;
import ev.laborai.pirmaslab.entities.Rider;
import ev.laborai.pirmaslab.persistence.CarsDAO;
import ev.laborai.pirmaslab.persistence.RidersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class Riders {

    @Inject
    private RidersDAO ridersDAO;
    @Inject
    private CarsDAO carsDAO;

    @Getter
    private List<Car> allCars;

    @Getter
    @Setter
    private Rider rider;

    @Getter
    @Setter
    private Long carToRideId;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long riderId = Long.parseLong(requestParameters.get("riderId"));
        rider = ridersDAO.findOne(riderId);
        loadAllCars();
    }

    @Transactional
    public void createRide() {
        Car car = carsDAO.findOne(carToRideId);
        rider.getCars().add(car);
        ridersDAO.update(rider);
    }

    private void loadAllCars(){
        allCars = carsDAO.loadAll();
    }

}
