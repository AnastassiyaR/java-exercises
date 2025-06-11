package ee.taltech.iti0202.kittens;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Kitten(Long id, Gender gender, LocalDate birthDate, BigDecimal wallet) {
}
