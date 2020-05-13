package com.app.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Locality {

    private String id;
    private String countyId;
    private String name;
    private double longitude;
    private double latitude;
    private int distance;
}
