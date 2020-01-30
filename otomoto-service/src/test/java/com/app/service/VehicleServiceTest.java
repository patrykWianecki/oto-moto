package com.app.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.model.Vehicle;
import com.app.repository.VehicleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.app.MockData.*;
import static com.app.model.Colour.*;
import static com.app.model.Condition.*;
import static com.app.model.Drive.*;
import static com.app.model.EmmisionClass.*;
import static com.app.model.Feature.*;
import static com.app.model.Fuel.*;
import static com.app.model.Gearbox.*;
import static com.app.model.Make.*;
import static com.app.model.Type.*;
import static java.util.Objects.*;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private VehicleService vehicleService;

    private static Vehicle vehicle = createVehicle();

    @Test
    public void should_add_vehicle() {
        // given
        Mockito.when(vehicleRepository.save(any(Vehicle.class))).thenReturn(Mono.just(vehicle));

        // when
        Mono<Vehicle> actualVehicle = vehicleService.addVehicle(vehicle);

        // then
        StepVerifier
            .create(actualVehicle)
            .expectNextMatches(VehicleServiceTest::validateVehicle)
            .expectComplete()
            .verify();
    }

    // TODO NPE findById
    // @Test
    // public void should_update_vehicle() {
    //     // given
    //     Mockito.when(vehicleRepository.save(any(Vehicle.class))).thenReturn(Mono.just(vehicle));
    //
    //     // when
    //     Mono<Vehicle> actualVehicle = vehicleService.updateVehicle(vehicle);
    //
    //     // then
    //     StepVerifier
    //         .create(actualVehicle)
    //         .expectNextMatches(VehicleServiceTest::validateVehicle)
    //         .expectComplete()
    //         .verify();
    // }

    @Test
    public void should_find_vehicle_by_id() {
        // given
        Mockito.when(vehicleRepository.findById((BigInteger) any())).thenReturn(Mono.just(vehicle));

        // when
        Mono<Vehicle> actualVehicle = vehicleService.findVehicleById(BigInteger.valueOf(1));

        // then
        StepVerifier
            .create(actualVehicle)
            .expectNextMatches(VehicleServiceTest::validateVehicle)
            .expectComplete()
            .verify();
    }

    @Test
    public void should_find_all_vehicles() {
        // given
        Mockito.when(vehicleRepository.findAll()).thenReturn(Flux.just(vehicle));

        // when
        Flux<Vehicle> actualVehicles = vehicleService.findAllVehicles();

        // then
        StepVerifier
            .create(actualVehicles)
            .expectNextMatches(VehicleServiceTest::validateVehicle)
            .expectComplete()
            .verify();
    }

     @Test
     public void should_remove_vehicle_by_id() {
         // given
         Mockito.when(vehicleRepository.deleteById((BigInteger) any())).thenReturn(Mono.empty());

         // when
         Mono<Void> actualVehicles = vehicleService.removeVehicleById(BigInteger.valueOf(1));

         // then
         StepVerifier
             .create(actualVehicles)
             .expectComplete()
             .verify();
     }

     @Test
     public void should_remove_all_vehicles() {
         // given
         Mockito.when(vehicleRepository.deleteAll()).thenReturn(Mono.empty());

         // when
         Mono<Void> actualVehicles = vehicleService.removeAllVehicles();

         // then
         StepVerifier
             .create(actualVehicles)
             .expectNext()
             .expectComplete()
             .verify();
     }

    private static boolean validateVehicle(final Vehicle vehicle) {
        return BLACK.equals(vehicle.getColour()) &&
            NEW.equals(vehicle.getCondition()) &&
            "PLN".equals(vehicle.getCurrency()) &&
            ALL_WHEEL_DRIVE.equals(vehicle.getDrive()) &&
            nonNull(vehicle.getEngine()) &&
            4.0 == vehicle.getEngine().getCapacity() &&
            EURO_6.equals(vehicle.getEngine().getEmmisionClass()) &&
            PETROL.equals(vehicle.getEngine().getFuel()) &&
            15.0 == vehicle.getEngine().getFuelConsumption() &&
            612 == vehicle.getEngine().getPower() &&
            Set.of(ABS, ASR, ESP).containsAll(vehicle.getFeatures()) &&
            LocalDate.of(2020, 1, 1).isEqual(vehicle.getFirstRegistration()) &&
            AUTOMATIC.equals(vehicle.getGearbox()) &&
            vehicle.isAccidentFree() &&
            !vehicle.isDamaged() &&
            "Warsaw".equals(vehicle.getLocation()) &&
            BMW.equals(vehicle.getMake()) &&
            0 == vehicle.getMileage() &&
            "E-Class".equals(vehicle.getModel()) &&
            4 == vehicle.getNumberOfSeats() &&
            0 == vehicle.getNumberOfVehicleOwners() &&
            BigDecimal.valueOf(900000).equals(vehicle.getPrice()) &&
            SEDAN.equals(vehicle.getType());
    }

    //    private static void validateVehicle(final Vehicle vehicle) {
    //        assertNotNull(vehicle);
    //        assertEquals(BLACK, vehicle.getColour());
    //
    //        assertNotNull(vehicle.getEngine());
    //        validateEngine(vehicle.getEngine());
    //
    //    }
    //
    //    private static void validateEngine(final Engine engine) {
    //        assertEquals(4.0, engine.getCapacity());
    //        assertEquals(EURO_6, engine.getEmmisionClass());
    //        assertEquals(PETROL, engine.getFuel());
    //        assertEquals(15.0, engine.getFuelConsumption());
    //        assertEquals(612, engine.getPower());
    //    }
}