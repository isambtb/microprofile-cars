package com.ibosoft.cars;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/${api.version}")
public class CarsApplication  extends Application {

}
