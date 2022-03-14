package ev.laborai.pirmaslab.usecases;

import ev.laborai.pirmaslab.entities.Rider;
import ev.laborai.pirmaslab.mybatis.dao.CarMapper;
import ev.laborai.pirmaslab.mybatis.model.Car;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Model
public class CarsMyBatis {

    @Inject
    private CarMapper carMapper;

    @Getter
    @Setter
    private Car car;

    @Getter
    @Setter
    private List<Rider> carRiders;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long carId = Long.parseLong(requestParameters.get("carId"));
        car = carMapper.selectByPrimaryKey(carId);
        carRiders = carMapper.getRiders(carId);
    }

}
