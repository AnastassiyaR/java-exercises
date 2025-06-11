package ee.taltech.iti0202.di.service;

import ee.taltech.iti0202.di.repository.CarRepository;
import java.math.BigDecimal;

public class CarService {
    private final CarRepository carRepository;

    /**
     * Constructor
     * @param carRepository
     */
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Get all cars
     * @return cars
     */
    public String getAllCars() {
        return carRepository.getAllCars().toString();
    }

    /**
     * Find cars in price range
     * @param start
     * @param end
     * @return cars in price range "start" and "end"
     */
    public String findCarsInPriceRange(String start, String end) {
        try {
            BigDecimal minPrice = new BigDecimal(start);
            BigDecimal maxPrice = new BigDecimal(end);

            if (minPrice.compareTo(maxPrice) > 0) {
                return "Lower price must be less than upper price";
            }
            return carRepository.findCarsInPriceRange(minPrice, maxPrice).toString();
        } catch (NumberFormatException e) {
            return "Invalid arguments";
        }
    }

    /**
     * Find cars by colors
     * @param color
     * @return cars by a concrete color
     */
    public String findCarsByColor(String color) {
        return carRepository.findCarsByColor(color).toString();
    }

    /**
     * Find cars newer than "year"
     * @param year
     * @return cars which newer than a concrete year
     */
    public String findCarsNewerThan(String year) {
        try {
            int yearInt = Integer.parseInt(year);
            return carRepository.findCarsNewerThan(yearInt).toString();
        } catch (NumberFormatException e) {
            return "Invalid argument";
        }
    }

    /**
     * Find cars older than "year"
     * @param year
     * @return cars which older than a concrete year
     */
    public String findCarsOlderThan(String year) {
        try {
            int yearInt = Integer.parseInt(year);
            return carRepository.findCarsOlderThan(yearInt).toString();
        } catch (NumberFormatException e) {
            return "Invalid argument";
        }
    }

    /**
     * Get car by make and model
     * @param make
     * @param model
     * @return cars by a concrete make and model
     */
    public String getCarByMakeAndModel(String make, String model) {
        if (!carRepository.existsByMakeAndModel(make, model)) {
            return "Car not found";
        }
        return carRepository.getCarByMakeAndModel(make, model).toString();
    }

    /**
     * Delete car by make and model
     * @param make
     * @param model
     * @return
     */
    public String deleteCarByMakeAndModel(String make, String model) {
        if (!carRepository.existsByMakeAndModel(make, model)) {
            return "Car not found";
        }
        return carRepository.deleteCarByMakeAndModel(make, model)
                ? "Car was successfully deleted"
                : "Error while deleting car";
    }
}
