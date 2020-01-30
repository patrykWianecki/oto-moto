package com.app.model.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import com.app.model.Colour;
import com.app.model.Condition;
import com.app.model.Drive;
import com.app.model.Feature;
import com.app.model.Gearbox;
import com.app.model.Make;
import com.app.model.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {

    private BigInteger id;
    private Colour colour;
    private Condition condition;
    private String currency;
    private Drive drive;
    private Set<Feature> features;
    private LocalDate firstRegistration;
    private EngineDto engineDto;
    private Gearbox gearbox;
    private boolean isAccidentFree;
    private boolean isDamaged;
    private String location;
    private Make make;
    private Long mileage;
    private String model;
    private Integer numberOfSeats;
    private Integer numberOfVehicleOwners;
    private BigDecimal price;
    private Type type;
}
