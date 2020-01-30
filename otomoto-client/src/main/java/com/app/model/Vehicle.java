package com.app.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vehicles")
public class Vehicle {

    @Id
    private BigInteger id;
    private Colour colour;
    private Condition condition;
    private String currency;
    private Drive drive;
    private Engine engine;
    private Set<Feature> features;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstRegistration;
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

    //    ENUMS
    //    @Field(bridge = @FieldBridge(impl = EnumBridge.class))
    //    @Enumerated(EnumType.STRING)

    //    FIELDS
    //    @Field

    //    COLLECTION
    //    @ElementCollection
    //    @CollectionTable(name = "features", joinColumns = @JoinColumn(name = "features_id"))
    //    @Column(name = "features")
    //    @Enumerated(EnumType.STRING)
    //    @IndexedEmbedded
    //    private Set<Feature> features = Set.of();

    //    CLASS
    //    @Indexed
}
