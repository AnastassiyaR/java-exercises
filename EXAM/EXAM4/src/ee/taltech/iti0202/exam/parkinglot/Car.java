package ee.taltech.iti0202.exam.parkinglot;

public class Car extends Vehicle {

    /**
     * Constructor car
     * @param licensePlate
     */
    public Car(String licensePlate) {
        super(licensePlate);
    }

    /**
     * Fee should be calculated as 2 * hours parked for regular cars,
     * and if the hours given is negative or 0, throw IllegalArgumentException.
     *
     * @param hours duration parked
     * @return float, which represents parking fee
     */
    @Override
    public double calculateParkingFee(int hours) throws IllegalArgumentException {
        if (hours <= 0) {
            throw new IllegalArgumentException();
        }
        return 2 * hours;
    }
}
