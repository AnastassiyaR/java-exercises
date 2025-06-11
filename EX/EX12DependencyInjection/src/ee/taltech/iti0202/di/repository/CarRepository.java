package ee.taltech.iti0202.di.repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Car repository.
 */
public interface CarRepository {
    /**
     * Gets all cars.
     *
     * @return all cars
     */
    List<Car> getAllCars();

    /**
     * Gets car by make and model.
     *
     * @param make  the make
     * @param model the model
     * @return car by make and model
     */
    Car getCarByMakeAndModel(String make, String model);

    /**
     * Find cars in price range.
     *
     * @param minPrice the min price
     * @param maxPrice the max price
     * @return list of suitable cars
     */
    List<Car> findCarsInPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Find cars by color.
     *
     * @param color the color
     * @return list of suitable cars
     */
    List<Car> findCarsByColor(String color);

    /**
     * Find cars newer than parameter.
     *
     * @param year the year
     * @return list of suitable cars
     */
    List<Car> findCarsNewerThan(Integer year);

    /**
     * Find cars older than parameter.
     *
     * @param year the year
     * @return list of suitable cars
     */
    List<Car> findCarsOlderThan(Integer year);

    /**
     * Check if car exists by make and model.
     *
     * @param make  the make
     * @param model the model
     * @return whether the car exists or not
     */
    boolean existsByMakeAndModel(String make, String model);

    /**
     * Delete car by make and model.
     *
     * @param make  the make
     * @param model the model
     * @return whether the car was successfully deleted or not
     */
    boolean deleteCarByMakeAndModel(String make, String model);
}
