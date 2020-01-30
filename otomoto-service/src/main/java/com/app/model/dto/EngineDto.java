package com.app.model.dto;

import com.app.model.EmmisionClass;
import com.app.model.Fuel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EngineDto {

    private Double capacity;
    private EmmisionClass emmisionClass;
    private Fuel fuel;
    private Double fuelConsumption;
    private Integer power;
}
