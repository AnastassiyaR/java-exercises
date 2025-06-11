package ee.taltech.iti0202.exam.parkinglot;

public abstract class Vehicle {
    /**
     * The vehicle class should have a licensePlate and parkingSpotNumber, which defaults to -1, if it isn't parked.
     *
     * @param licensePlate license plate of the vehicle
     */
    private String licensePlate;
    private int parkingSpotNumber = -1;

    /**
     * Constructor Vehicle
     * @param licensePlate
     */
    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(int parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    /**
     * Calculate parking fee
     * @param hours
     * @return
     */
    public abstract double calculateParkingFee(int hours);

}
