package ev.laborai.pirmaslab.persistence;

import ev.laborai.pirmaslab.entities.Car;
import ev.laborai.pirmaslab.entities.Driver;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CarsDAO {

    @Inject
    private EntityManager em;

    public List<Car> loadAll() {
        return em.createNamedQuery("Car.findAll", Car.class).getResultList();
    }

    public void persist(Car car){
        this.em.persist(car);
    }

    public Car findOne(Long id){
        return em.find(Car.class, id);
    }

    public Car update(Car car){
        return em.merge(car);
    }

    public Long Size() {
        return (Long) em.createNamedQuery("Car.count").getSingleResult();
    }

}
