package ev.laborai.pirmaslab.usecases;

import ev.laborai.pirmaslab.services.RatingCalculator;

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

@SessionScoped
@Named
public class DriverRatingCalculator implements Serializable {
    @Inject
    private RatingCalculator ratingCalculator;

    private CompletableFuture<Float> calculatorTask;

    public String findRating() throws ExecutionException, InterruptedException {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        calculatorTask = CompletableFuture.supplyAsync(() -> ratingCalculator.generateRating());
        return "/driver?faces-redirect=true&driverId=" + requestParameters.get("driverId");
    }

    public boolean isTaskRunning() {
        return calculatorTask != null && !calculatorTask.isDone();
    }

    public String getTaskStatus() throws ExecutionException, InterruptedException {
        if (calculatorTask == null) {
            return null;
        } else if (isTaskRunning()) {
            return "Driver's rating is still being found out.";
        }
        return "Driver's rating is: " + calculatorTask.get();
    }

}
