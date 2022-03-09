package ev.laborai.pirmaslab.usecases;

import ev.laborai.pirmaslab.entities.Driver;
import ev.laborai.pirmaslab.persistence.DriversDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class DriverCars {

    @Inject
    private DriversDAO driversDAO;

    @Getter
    @Setter
    private Driver driver;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long driverId = Long.parseLong(requestParameters.get("driverId"));
        driver = driversDAO.findOne(driverId);
    }

}
