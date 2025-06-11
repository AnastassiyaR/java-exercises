package ee.taltech.iti0202.exam.parkinglot;

public class VipCar extends Car {

    /**
     * Constructor VipCar
     * @param licensePlate
     */
    public VipCar(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public double calculateParkingFee(int hours) throws IllegalArgumentException {
        return 0.0;
    }
}
