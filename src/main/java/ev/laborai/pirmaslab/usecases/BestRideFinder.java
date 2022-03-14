package ev.laborai.pirmaslab.usecases;

import ev.laborai.pirmaslab.entities.Car;
import ev.laborai.pirmaslab.entities.Rider;
import ev.laborai.pirmaslab.persistence.CarsDAO;
import ev.laborai.pirmaslab.persistence.RidersDAO;
import ev.laborai.pirmaslab.services.RideCalculator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
@Named
public class BestRideFinder implements Serializable {
    @Inject
    private RideCalculator rideCalculator;
    @Inject
    private CarsDAO carsDAO;
    @Inject
    private RidersDAO ridersDAO;

    private CompletableFuture<Car> finderTask;

    public String findBestRide() throws ExecutionException, InterruptedException {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        finderTask = CompletableFuture.supplyAsync(() -> rideCalculator.findBestRide());
        Long riderId = Long.parseLong(requestParameters.get("riderId"));
        Rider rider = ridersDAO.findOne(riderId);
        Car car = finderTask.get();
        car.getRiders().add(rider);
        carsDAO.update(car);
        return "/rider?faces-redirect=true&riderId=" + requestParameters.get("riderId");
    }

    public boolean isTaskRunning() {
        return finderTask != null && !finderTask.isDone();
    }

}
