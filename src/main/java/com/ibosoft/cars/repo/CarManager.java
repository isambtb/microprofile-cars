package com.ibosoft.cars.repo;


import com.ibosoft.cars.boundary.CarEndpoint;
import com.ibosoft.cars.model.Car;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarManager {

  private static final Logger LOGGER = Logger.getLogger(CarEndpoint.class.getName());


  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
  private AtomicInteger bookIdGenerator = new AtomicInteger(0);

  private ConcurrentMap<String, Car> inMemoryStore = new ConcurrentHashMap();

  private CarManager() {
    Car car = Car.builder()
        .id(getNextId())
        .code("BMW-serie-1")
        .manufacturer("BMW GROUP")
        .maxSpeed(250)
        .model("serie 1")
        .price(35000.0)
        .build();
    inMemoryStore.put(car.getId().toString(), car);
  }

  private String getNextId() {
    String date = LocalDate.now().format(formatter);
    return String.format("%04d-%s", bookIdGenerator.incrementAndGet(), date);
  }

  public String add(Car car) {
    String id = getNextId();
    car.setId(id);
    inMemoryStore.put(id, car);
    return id;
  }

  public Car get(String id) {
    return inMemoryStore.get(id);
  }

  public List<Car> getAll() {
    List<Car> cars = new ArrayList();
    cars.addAll(inMemoryStore.values());
    return cars;
  }

}
