package com.ibosoft.cars.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

  private String id;
  private String model;
  private String code;
  private String manufacturer;
  private Integer maxSpeed;
  private Double price;


}
