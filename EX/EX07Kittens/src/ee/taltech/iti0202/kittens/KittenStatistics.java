package ee.taltech.iti0202.kittens;

import ee.taltech.iti0202.kittens.http.HttpException;
import ee.taltech.iti0202.kittens.http.Response;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Set;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class KittenStatistics {

    /**
     *
     * @param kittens
     * @param id
     * @return
     */
    public static Optional<Kitten> findById(List<Kitten> kittens, Long id) {
        return kittens.stream()
                .filter(kitten -> kitten.id().equals(id))
                .findFirst();
    }

    /**
     *
     * @param kittens
     * @param gender
     * @return
     */
    public static List<Kitten> findKittensByGender(List<Kitten> kittens, Gender gender) {
        return kittens.stream()
                .filter(kitten -> kitten.gender().equals(gender))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     *
     * @param kittens
     * @return
     */
    public static Map<Long, Kitten> convertToIdKittenMap(List<Kitten> kittens) {
        return kittens.stream()
                .collect(Collectors.toUnmodifiableMap(Kitten::id, kitten -> kitten));
    }

    /**
     *
     * @param kittens
     * @return
     */
    public static List<Kitten> sortKittensYoungerFirst(List<Kitten> kittens) {
        return kittens.stream()
                .sorted(Comparator.comparing(Kitten::birthDate).reversed())
                .collect(Collectors.toUnmodifiableList()); //  Нельзя изменять и проверяет нулл
    }

    /**
     *
     * @param kittens
     * @return
     */
    public static Optional<Kitten> findOldestKitten(List<Kitten> kittens) {
        return kittens.stream()
                .min(Comparator.comparing(Kitten::birthDate));
    }

    /**
     *
     * @param kittens
     * @param dayOfWeek
     * @return
     */
    public static List<Kitten> findKittensBornOnDayOfWeek(List<Kitten> kittens, DayOfWeek dayOfWeek) {
        return kittens.stream()
                .filter(kitten -> kitten.birthDate().getDayOfWeek() == dayOfWeek)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     *
     * @param kittens
     * @param month
     * @return
     */
    public static List<Kitten> findKittensBornInMonth(List<Kitten> kittens, Month month) {
        return kittens.stream()
                .filter(kitten -> kitten.birthDate().getMonth() == month)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     *
     * @param kittens
     * @param minBirthDate
     * @param maxBirthDate
     * @return
     */
    public static Set<Kitten> findKittensBornBetween(
            List<Kitten> kittens,
            LocalDate minBirthDate,
            LocalDate maxBirthDate) {
        return kittens.stream()
                // надо с !, чтобы лимиты были включены
                .filter(kitten -> !kitten.birthDate().isBefore(minBirthDate)
                        && !kitten.birthDate().isAfter(maxBirthDate))
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     *
     * @param kittens
     * @return
     */
    public static List<Integer> calculateKittensAgesInFullYear(List<Kitten> kittens) {
        if (kittens.isEmpty()) {
            return List.of();
        }
        // Вызываем LocalDate.now() ТОЛЬКО ОДИН РАЗ!!! А то эррор и 0 эффективность
        LocalDate now = LocalDate.now();
        return kittens.stream()

                .map(kitten -> Period.between(kitten.birthDate(), now).getYears())
                // Возвращает неизменяемый (!!!) список, а вообще можешь использовать toUnmodifiable
                .toList();
        // неизменяемый лучше тем, что так безопасней, надежней, да и вообще жизнь упрощает
    }

    /**
     *
     * @param kittens
     * @param httpRequestExecutor
     * @return
     */
    public static List<Kitten> findKittensEligibleForFreeHealthCheckup(
            List<Kitten> kittens,
            Supplier<Response<FreeHealthcheckCriteria>> httpRequestExecutor) {
        // Запрос Supplier<Response<FreeHealthcheckCriteria>> httpRequestExecutor выполняется сразу
        // если не использовать Supplier
        if (kittens.isEmpty()) {
            return List.of();
        }
        Response<FreeHealthcheckCriteria> response = httpRequestExecutor.get(); // Запрос только здесь используется
        if (!response.isOk()) {
            throw new HttpException(response.httpStatus());
        }
        FreeHealthcheckCriteria criteria = response.body();
        return kittens.stream()
                .filter(kitten -> kitten.gender() == criteria.gender()
                        && kitten.birthDate().getYear() == criteria.year())
                .toList();
    }

    /**
     *
     * @param kittens
     * @param maxLimit
     * @return
     */
    public static List<Kitten> sortKittensRichestFirst(List<Kitten> kittens, BigDecimal maxLimit) {
        return kittens.stream()
                // Оператор < работает только с примитивными типами!!!!!!!!!!
                .filter(kitten -> kitten.wallet().compareTo(maxLimit) <= 0)
                .sorted(Comparator.comparing(Kitten::wallet).reversed())
                .toList();
    }

    /**
     *
     * @param kittens
     * @return
     */
    public static BigDecimal calculateTotalWealthOfUpToThreeRichestKittens(List<Kitten> kittens) {
        return kittens.stream()
                .sorted(Comparator.comparing(Kitten::wallet).reversed())
                .limit(3)
                .map(Kitten::wallet)
                // reduce — это терминальная операция в Stream API,
                // которая объединяет все элементы потока в один результат.

                // Он принимает два аргумента:
                // Начальное значение (identity) — значение, с которого начинается вычисление.
                // Функция аккумуляции (accumulator) — функция, которая объединяет текущий результат
                // с очередным элементом потока.
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Суммируем BigDecimal
    }

}
