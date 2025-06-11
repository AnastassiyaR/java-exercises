import ee.taltech.iti0202.di.controller.CarController;
import ee.taltech.iti0202.di.repository.Car;
import ee.taltech.iti0202.di.repository.CarRepository;
import ee.taltech.iti0202.di.service.CarService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

class DependencyInjectionTest {

    @Test
    void testCarControllerCorrectlyReturnsGetCarByMakeAndModel() {
        // CarRepository implementatsiooni ei ole, seega tekitame testis "mock" implementatsiooni
        CarRepository repository = mock(CarRepository.class);
        CarService service = new CarService(repository);
        CarController controller = new CarController(service);

        // mingisugune sobiv auto
        Car expectedCar = new Car("Ford", "Focus", "blue", 2007, BigDecimal.TEN);

        // juhul kui mockitud klassi tuleb selline päring,
        // siis tagasta selline asi
        when(repository.existsByMakeAndModel("Ford", "Focus"))
                .thenReturn(true);

        // juhul kui mockitud klassi tuleb selline päring,
        // siis tagasta selline asi
        when(repository.getCarByMakeAndModel("Ford", "Focus"))
                .thenReturn(expectedCar);

        // meetod, mille toimimist me reaalselt tahtsime testida
        assertEquals(expectedCar.toString(), controller.handleRequest("/get_car?make=Ford&model=Focus"));

        // kontrollime, et existsByMakeAndModel() kutsuti vähemalt korra nende parameetritega
        verify(repository, atLeastOnce()).existsByMakeAndModel("Ford", "Focus");

        // kontrollime, et repository meetodit getCarByMakeAndModel() kutsuti nende parameetritega välja täpselt 1 kord
        verify(repository, times(1)).getCarByMakeAndModel("Ford", "Focus");
    }
}
