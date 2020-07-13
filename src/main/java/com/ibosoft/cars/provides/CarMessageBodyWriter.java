package com.ibosoft.cars.provides;


import com.ibosoft.cars.model.Car;
import com.ibosoft.cars.util.CarMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CarMessageBodyWriter implements MessageBodyWriter<Car> {

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    return type.equals(Car.class);
  }

  public long getSize(Car car, Class<?> aClass, Type type, Annotation[] annotations,
      MediaType mediaType) {
    return 0;
  }

  /**
   * Marsahl Car to OutputStream
   */
  @Override
  public void writeTo(Car car, Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
      throws IOException, WebApplicationException {
    JsonWriter jsonWriter = Json.createWriter(entityStream);
    JsonObject jsonObject = CarMapper.map(car);
    jsonWriter.writeObject(jsonObject);
    jsonWriter.close();
  }
}
