package ee.taltech.iti0202.exam.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private int totalSpots;
    private int totalcars = 0;
    private List<Integer> nums = new ArrayList<>();
    private List<String> carsLicences = new ArrayList<>();
    private Map<Vehicle, Integer> vehicles = new HashMap<>();

    /**
     * Constructor parkingLot
     * @param totalSpots
     */
    public ParkingLot(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    /**
     * Attempts to park the given vehicle in the parking lot.
     *
     * @param vehicle the vehicle to be parked
     * @return true if the vehicle was successfully parked; false if the lot is full
     *         or the vehicle is already parked
     */
    public boolean parkVehicle(Vehicle vehicle) {
        if (vehicle.getLicensePlate() == null || vehicle.getLicensePlate().trim().isEmpty()) {
            return false;
        }
        for (String licence : carsLicences) {
            if (licence.equals(vehicle.getLicensePlate())) {
                return false;
            }
        }

        if (totalcars >= totalSpots) {
            return false;
        }
        if (!nums.isEmpty() && nums != null) {
            vehicle.setParkingSpotNumber(nums.getFirst());
            nums.removeFirst();
            totalcars++;
        } else {
            totalcars++;
            vehicle.setParkingSpotNumber(totalSpots);

        }

        vehicles.put(vehicle, vehicle.getParkingSpotNumber());
        carsLicences.add(vehicle.getLicensePlate());
        return true;
    }

    /**
     * Removes the vehicle with the given license plate from the parking lot.
     * Should reset the vehicle.parkingSpotNumber as well.
     *
     * @param licensePlate the license plate of the vehicle to remove
     * @return true if the vehicle was found and removed; false if not found
     */
    public boolean removeVehicle(String licensePlate) {
        for (Vehicle vehicle : vehicles.keySet()) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                vehicles.remove(vehicle);
                nums.add(vehicle.getParkingSpotNumber());
                vehicle.setParkingSpotNumber(-1);
                totalcars--;
                carsLicences.remove(vehicle.getLicensePlate());
                return true;
            }
        }
        return false;
    }

    public String getAvailableSpots() {
        return "Free spaces: " + (totalSpots - totalcars);
    }

    /**
     * Returns a formatted string listing all currently occupied parking spots.
     *
     * @return a string showing occupied spots and their corresponding license plates
     */
    public String getOccupiedSpots() {
        if (vehicles.isEmpty() && nums.isEmpty() && carsLicences.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Vehicle vehicle : vehicles.keySet()) {
            builder.append("Space ")
                    .append(vehicle.getLicensePlate())
                    .append(": ")
                    .append(vehicle.getLicensePlate())
                    .append(" ");
        }
        return builder.toString().trim();
    }
}
