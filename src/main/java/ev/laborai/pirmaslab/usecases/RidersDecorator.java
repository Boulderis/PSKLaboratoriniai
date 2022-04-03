package ev.laborai.pirmaslab.usecases;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class RidersDecorator implements IRider {

    @Inject
    @Delegate
    @Any
    IRider rider;

    @Override
    public void init() {
    }

    @Override
    public void createRide() {
        System.out.println("Decorator called. A user is booking a ride.");
        rider.createRide();
    }

    @Override
    public void loadAllCars() {

    }

    @Override
    public void updateRider() {

    }
}
