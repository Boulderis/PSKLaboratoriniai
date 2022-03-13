package ev.laborai.pirmaslab.usecases;

import ev.laborai.pirmaslab.entities.Car;
import ev.laborai.pirmaslab.entities.Driver;
import ev.laborai.pirmaslab.persistence.CarsDAO;
import ev.laborai.pirmaslab.persistence.DriversDAO;
import ev.laborai.pirmaslab.persistence.RidersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class Cars {

    @Inject
    private CarsDAO carsDAO;

    @Getter
    @Setter
    private Car car;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long carId = Long.parseLong(requestParameters.get("carId"));
        car = carsDAO.findOne(carId);
    }

}
