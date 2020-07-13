package com.ibosoft.cars.util;

import com.ibosoft.cars.model.Car;
import java.io.InputStream;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonString;

public class CarMapper {

  public static JsonObject map(Car car) {
    JsonObjectBuilder builder = Json.createObjectBuilder();
    addValue(builder, "id", car.getId());
    addValue(builder, "code", car.getCode());
    addValue(builder, "manufacturer", car.getManufacturer());
    addValue(builder, "model", car.getModel());
    addValue(builder, "price", car.getPrice());
    addValue(builder, "maxSpeed", car.getMaxSpeed());
    return builder.build();
  }

  private static void addValue(JsonObjectBuilder builder, String key, Object value) {
    if (value != null) {
      builder.add(key, value.toString());
    } else {
      builder.addNull(key);
    }
  }

  public static JsonArray map(List<Car> cars) {
    final JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    cars.forEach(car -> {
      JsonObject jsonObject = map(car);
      arrayBuilder.add(jsonObject);
    });
    return arrayBuilder.build();
  }

  public static Car map(InputStream is) {
    try (JsonReader jsonReader = Json.createReader(is)) {
      JsonObject jsonObject = jsonReader.readObject();
      Car car = new Car();
      car.setId(getStringFromJson("id", jsonObject));
      car.setCode(getStringFromJson("code", jsonObject));
      car.setManufacturer(getStringFromJson("manufacturer", jsonObject));
      car.setMaxSpeed(Integer.parseInt(getStringFromJson("maxSpeed", jsonObject)));
      car.setPrice(getDoubleFromJson("price", jsonObject));
      car.setModel(getStringFromJson("model", jsonObject));
      return car;
    }
  }

  private static String getStringFromJson(String key, JsonObject json) {
    String returnedString = null;
    if (json.containsKey(key)) {
      JsonString value = json.getJsonString(key);
      if (value != null) {
        returnedString = value.getString();
      }
    }
    return returnedString;
  }

  private static Integer getIntFromJson(String key, JsonObject json) {
    Integer returnedValue = null;
    if (json.containsKey(key)) {
      JsonNumber value = json.getJsonNumber(key);
      if (value != null) {
        returnedValue = value.intValue();
      }
    }
    return returnedValue;
  }

  private static Double getDoubleFromJson(String key, JsonObject json) {
    Double returnedValue = null;
    if (json.containsKey(key)) {
      JsonNumber value = json.getJsonNumber(key);
      if (value != null) {
        returnedValue = value.doubleValue();
      }
    }
    return returnedValue;
  }

}
