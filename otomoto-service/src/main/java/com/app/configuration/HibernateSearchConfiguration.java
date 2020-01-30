package com.app.configuration;//package com.app.configuration;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//
//import com.app.repository.VehicleRepository;
//import com.app.service.VehicleService;
//
//import lombok.RequiredArgsConstructor;
//
//@EnableAutoConfiguration
//@RequiredArgsConstructor
//public class HibernateSearchConfiguration {
//
//    private final EntityManager entityManager;
//    private final VehicleRepository vehicleRepository;
//
//    @Bean
//    VehicleService hibernateSearchService() {
//        VehicleService vehicleService = new VehicleService(vehicleRepository, entityManager);
//        vehicleService.initializeHibernateSearch();
//        return vehicleService;
//    }
//}