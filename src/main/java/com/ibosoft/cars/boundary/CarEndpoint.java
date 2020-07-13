package com.ibosoft.cars.boundary;

import com.ibosoft.cars.model.Car;
import com.ibosoft.cars.repo.CarManager;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("cars")
@RequestScoped
public class CarEndpoint {

  @Inject
  private CarManager carManager;

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getBook(@PathParam("id") String id) {
    Car car = carManager.get(id);
    return Response.ok(car).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllBooks() {
    return Response.ok(carManager.getAll()).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response add(Car car) {
    String carId = carManager.add(car);
    return Response.created(
        UriBuilder.fromResource(this.getClass()).path(carId).build())
        .build();
  }
}
