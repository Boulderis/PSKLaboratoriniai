package ev.laborai.pirmaslab.persistence;
import ev.laborai.pirmaslab.entities.Driver;
import ev.laborai.pirmaslab.entities.Rider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@ApplicationScoped
public class RidersDAO {

    @Inject
    private EntityManager em;

    public List<Rider> loadAll() {
        return em.createNamedQuery("Rider.findAll", Rider.class).getResultList();
    }

    public void persist(Rider rider){
        this.em.persist(rider);
    }

    public Rider findOne(Long id){
        return em.find(Rider.class, id);
    }

    public Rider update(Rider rider){
        return em.merge(rider);
    }

}
