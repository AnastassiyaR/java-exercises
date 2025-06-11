package ee.taltech.iti0202.di.controller;

import ee.taltech.iti0202.di.service.CarService;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Car controller.
 */
public class CarController {

    private final CarService service;

    /**
     * Instantiates a new Car controller.
     *
     * @param service the car service
     */
    public CarController(CarService service) {
        this.service = service;
    }

    /**
     * Handle request.
     *
     * @param request the request
     * @return the response
     */
    public String handleRequest(String request) {
        if (request == null || request.isEmpty()) {
            return "Not found";
        }

        String[] parts = request.split("\\?");
        String endpoint = parts[0];
        String parameters = parts.length > 1 ? parts[1] : "";

        Map<String, String> params = parseParameters(parameters);

        return switch (endpoint) {
            case "/get_cars" -> handleGetCars(params);
            case "/get_car" -> handleGetCar(params);
            case "/delete_car" -> handleDeleteCar(params);
            default -> "Not found";
        };
    }

    private String handleGetCars(Map<String, String> params) {
        if (params.containsKey("price_lower") && params.containsKey("price_upper")) {
            return service.findCarsInPriceRange(params.get("price_lower"), params.get("price_upper"));
        } else if (params.containsKey("color")) {
            return service.findCarsByColor(params.get("color"));
        } else if (params.containsKey("newer_than")) {
            return service.findCarsNewerThan(params.get("newer_than"));
        } else if (params.containsKey("older_than")) {
            return service.findCarsOlderThan(params.get("older_than"));
        } else if (params.isEmpty()) {
            return service.getAllCars();
        }
        return "Not found";
    }

    private String handleGetCar(Map<String, String> params) {
        if (params.containsKey("make") && params.containsKey("model")) {
            return service.getCarByMakeAndModel(params.get("make"), params.get("model"));
        }
        return "Not found";
    }

    private String handleDeleteCar(Map<String, String> params) {
        if (params.containsKey("make") && params.containsKey("model")) {
            return service.deleteCarByMakeAndModel(params.get("make"), params.get("model"));
        }
        return "Not found";
    }

    private Map<String, String> parseParameters(String parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return Map.of();
        }

        return Arrays.stream(parameters.split("&"))
                .map(param -> param.split("=", 2))
                .collect(Collectors.toMap(
                        arr -> arr[0],
                        arr -> arr.length > 1 ? arr[1] : ""
                ));
    }
}
