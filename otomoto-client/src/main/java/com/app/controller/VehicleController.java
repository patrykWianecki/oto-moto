package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.VehicleDto;
import com.app.service.VehicleService;
import com.app.validator.VehicleValidator;

import lombok.RequiredArgsConstructor;

import static com.app.constants.ClientConstants.*;
import static java.math.BigDecimal.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {

  private final VehicleService vehicleService;
  private final VehicleValidator vehicleValidator;

  @InitBinder
  private void initBinder(WebDataBinder webDataBinder) {
    webDataBinder.addValidators(vehicleValidator);
  }

  @GetMapping("/add")
  public String addVehicle(Model model) {
    model.addAttribute(VEHICLE, new VehicleDto());
    model.addAttribute(ERRORS, new HashMap<>());
    return "vehicles/add_vehicle";
  }

  @PostMapping("/add")
  public String addVehiclePost(@ModelAttribute @Valid VehicleDto vehicleDto,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      createModelAttributes(vehicleDto, bindingResult, model);
      return "vehicles/add_vehicle";
    }
    vehicleService.addVehicle(vehicleDto);
    return REDIRECT_TO_VEHICLES;
  }

  @GetMapping("/update/{id}")
  public String updateConstituency(@PathVariable String id, Model model) {
    model.addAttribute(VEHICLE, vehicleService.findVehicleById(id));
    model.addAttribute(ERRORS, new HashMap<>());
    return "vehicles/update_vehicle";
  }

  @PostMapping("/update")
  public String updateConstituenciesPost(@ModelAttribute @Valid VehicleDto vehicleDto,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      createModelAttributes(vehicleDto, bindingResult, model);
      return "vehicles/update_vehicle";
    }
    vehicleService.updateVehicle(vehicleDto);
    return REDIRECT_TO_VEHICLES;
  }

  @PostMapping("/delete")
  public String removeVehicle(@RequestParam String id) {
    vehicleService.removeVehicleById(id);
    return REDIRECT_TO_VEHICLES;
  }

  @GetMapping("/{id}")
  public String showOneVehicle(@PathVariable String id, Model model) {
    model.addAttribute(VEHICLE, vehicleService.findVehicleById(id));
    return "vehicles/show_one_vehicle";
  }

  @GetMapping("/all")
  public String listVehicles(Model model, @RequestParam("page") Optional<Integer> page,
      @RequestParam("size") Optional<Integer> size) {
    int currentPage = page.orElse(DEFAULT_CURRENT_PAGE);
    int pageSize = size.orElse(DEFAULT_PAGE_SIZE);
    Page<VehicleDto> vehiclesPage = vehicleService.findPaginatedVehicles(
        PageRequest.of(currentPage - ONE.intValue(), pageSize)
    );
    model.addAttribute("vehiclesPage", vehiclesPage);

    int totalPages = vehiclesPage.getTotalPages();
    if (totalPages > ZERO.intValue()) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
          .boxed()
          .collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    return "vehicles/show_all_vehicles";
  }

  private void createModelAttributes(VehicleDto vehicleDto, BindingResult bindingResult,
      Model model) {
    model.addAttribute(VEHICLE, vehicleDto);
    model.addAttribute(ERRORS, bindingResult.getFieldErrors()
        .stream()
        .collect(Collectors.toMap(
            FieldError::getField,
            FieldError::getCode,
            (v1, v2) -> String.join(", ", v1, v2)))
    );
  }
}