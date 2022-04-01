package ev.laborai.pirmaslab.services;

import ev.laborai.pirmaslab.entities.Car;
import ev.laborai.pirmaslab.persistence.CarsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class RatingCalculator implements Serializable {

    public float generateRating() {
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {}
        Random random = new Random();
        float rating = 5 * random.nextFloat();
        return rating;
    }

}
