package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Engine {

    private Double capacity;
    private EmmisionClass emmisionClass;
    private Fuel fuel;
    private Double fuelConsumption;
    private Integer power;
}
