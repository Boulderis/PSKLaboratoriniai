package ev.laborai.pirmaslab.rest;

import ev.laborai.pirmaslab.entities.Driver;
import ev.laborai.pirmaslab.persistence.DriversDAO;
import ev.laborai.pirmaslab.rest.dto.DriverDTO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/drivers")
public class DriverController {

    @Inject
    @Setter
    @Getter
    private DriversDAO driversDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Driver driver = driversDAO.findOne(id);
        if (driver == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setName(driver.getName());
        driverDTO.setSurname(driver.getSurname());
        driverDTO.setCarsDTO(driver.getCars());
        return Response.ok(driverDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Long driverId, DriverDTO driverDTO) {
        try {
            Driver driver = driversDAO.findOne(driverId);
            if (driver == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.setName(driverDTO.getName());
            driver.setSurname(driverDTO.getSurname());
            driversDAO.update(driver);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response post(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setSurname(driverDTO.getSurname());
        driversDAO.persist(driver);
        return Response.ok().build();
    }
}
