package ee.taltech.iti0202.di.repository;

import java.math.BigDecimal;

public record Car(String brand, String model, String color, int year, BigDecimal price) {
}
