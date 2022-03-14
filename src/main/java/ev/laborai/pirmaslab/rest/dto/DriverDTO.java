package ev.laborai.pirmaslab.rest.dto;

import ev.laborai.pirmaslab.entities.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class DriverDTO {
    private String name;
    private String surname;
    private List<CarDTO> carsDTO = new ArrayList<CarDTO>();

    public void setCarsDTO(List<Car> cars) {
        for(int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            CarDTO carDTO = new CarDTO();
            carDTO.setMake(car.getMake());
            carDTO.setModel(car.getModel());
            carDTO.setPlateNumber(car.getPlateNumber());
            carsDTO.add(carDTO);
        }
    }

}
