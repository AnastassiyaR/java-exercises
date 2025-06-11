package ee.taltech.iti0202.stream.basics;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.SequencedCollection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Utility class for working with collections using Java Stream API.
 */
public final class StreamBasics {

    private StreamBasics() {
        throw new IllegalStateException("Non-instantiable class");
    }

    /**
     * Generates a collection of numbers from start (inclusive) to end (exclusive).
     *
     * @param start the starting value (inclusive)
     * @param end the ending value (exclusive)
     * @return collection of numbers
     */
    public static SequencedCollection<Integer> generateNumbersExclusive(final int start, final int end) {
        return IntStream.range(start, end).boxed().toList();
    }

    /**
     * Generates a collection of numbers from start to end (inclusive).
     *
     * @param start the starting value (inclusive)
     * @param end the ending value (inclusive)
     * @return collection of numbers
     */
    public static SequencedCollection<Double> generateNumbersInclusive(final long start, final long end) {
        return LongStream.rangeClosed(start, end).asDoubleStream().boxed().toList();
    }

    /**
     * Converts a SequencedCollection to a List.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return a List containing the elements
     */
    public static <T> List<T> convertToList(final SequencedCollection<T> collection) {
        return collection.stream().toList();
    }

    /**
     * Returns distinct values from the collection.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return collection of distinct values
     */
    public static <T> SequencedCollection<T> getDistinctValues(final SequencedCollection<T> collection) {
        return collection.stream().distinct().toList();
    }

    /**
     * Converts a collection to an unmodifiable Set.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return an unmodifiable Set
     */
    public static <T> Set<T> convertToUnmodifiableSet(final Collection<T> collection) {
        return collection.stream().collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Finds the maximum value in the collection.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return Optional containing the max value
     */
    public static <T extends Comparable<? super T>> Optional<T> findMaxValue(final Collection<T> collection) {
        return collection.stream().max(Comparable::compareTo);
    }

    /**
     * Finds the minimum value in the collection.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return Optional containing the min value
     */
    public static <T extends Comparable<? super T>> Optional<T> findMinValue(final Collection<T> collection) {
        return collection.stream().min(Comparable::compareTo);
    }

    /**
     * Filters non-negative values from the collection.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return collection of non-negative values
     */
    public static <T extends Number> SequencedCollection<T> filterNonNegativeValues(
            final SequencedCollection<T> collection) {
        return collection.stream().filter(n -> n.doubleValue() >= 0).toList();
    }

    /**
     * Finds any negative number in the collection.
     *
     * @param numbers the input collection
     * @param <T> the type of elements in the collection
     * @return Optional containing a negative number
     */
    public static <T extends Number> Optional<T> findAnyNegativeNumber(final Collection<T> numbers) {
        return numbers.stream().filter(n -> n.doubleValue() < 0).findAny();
    }

    /**
     * Finds any negative number or returns Integer.MAX_VALUE.
     *
     * @param numbers the input collection
     * @return a negative number or Integer.MAX_VALUE
     */
    public static int findAnyNegativeNumberOrElse(final SequencedCollection<Integer> numbers) {
        return numbers.stream().filter(n -> n < 0).findAny().orElse(Integer.MAX_VALUE);
    }

    /**
     * Finds the first negative number or returns Integer.MAX_VALUE.
     *
     * @param numbers the input collection
     * @return the first negative number or Integer.MAX_VALUE
     */
    public static int findFirstNegativeNumberOrElseGet(final SequencedCollection<Integer> numbers) {
        return numbers.stream().filter(n -> n < 0).findFirst().orElseGet(() -> Integer.MAX_VALUE);
    }

    /**
     * Converts collection elements to strings.
     *
     * @param collection the input collection
     * @return collection of string representations
     */
    public static SequencedCollection<String> convertToStringList(final SequencedCollection<?> collection) {
        return collection.stream().map(Object::toString).toList();
    }

    /**
     * Converts positive numbers to a formatted string.
     *
     * @param collection the input collection
     * @return formatted string of positive numbers
     */
    public static String convertPositivesToSingleString(final SequencedCollection<? extends Number> collection) {
        return collection.stream()
                .filter(n -> n.doubleValue() > 0)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "String[", "]"));
    }

    /**
     * Returns a limited list from the collection.
     *
     * @param collection the input collection
     * @param amount the maximum number of elements to return
     * @param <T> the type of elements in the collection
     * @return limited collection
     */
    public static <T> SequencedCollection<T> getLimitedList(final SequencedCollection<T> collection,
                                                            final int amount) {
        return collection.stream().limit(amount).toList();
    }

    /**
     * Returns a list starting from the specified index.
     *
     * @param collection the input collection
     * @param amount the starting index
     * @param <T> the type of elements in the collection
     * @return sublist starting from the index
     */
    public static <T> SequencedCollection<T> getListFromIndex(final SequencedCollection<T> collection,
                                                              final int amount) {
        return collection.stream().skip(amount).toList();
    }

    /**
     * Returns squared values of the collection elements.
     *
     * @param collection the input collection
     * @return collection of squared values
     */
    public static SequencedCollection<Double> getSquaredValues(
            final SequencedCollection<? extends Number> collection) {
        return collection.stream().map(n -> Math.pow(n.doubleValue(), 2)).toList();
    }

    /**
     * Divides collection elements by a divisor.
     *
     * @param collection the input collection
     * @param divisor the divisor
     * @return array of divided values
     * @throws IllegalArgumentException if divisor is less than 1
     */
    public static double[] divideValues(final SequencedCollection<? extends Number> collection, final Number divisor) {
        if (divisor.doubleValue() < 1) {
            throw new IllegalArgumentException();
        }
        return collection.stream().mapToDouble(n -> n.doubleValue() / divisor.doubleValue()).toArray();
    }

    /**
     * Counts elements in the collection.
     *
     * @param collection the input collection
     * @return count of elements
     */
    public static long countValues(final Collection<?> collection) {
        return collection.stream().count();
    }

    /**
     * Counts elements using forEach.
     *
     * @param collection the input collection
     * @return count of elements
     */
    public static int countValuesUsingForEach(final SequencedCollection<?> collection) {
        final int[] counter = new int[1];
        collection.stream().forEach(e -> counter[0]++);
        return counter[0];
    }

    /**
     * Counts elements using forEachOrdered.
     *
     * @param collection the input collection
     * @return count of elements
     */
    public static int countValuesUsingForEachOrdered(final SequencedCollection<?> collection) {
        final int[] counter = new int[1];
        collection.stream().forEachOrdered(e -> counter[0]++);
        return counter[0];
    }

    /**
     * Counts elements using parallel forEach.
     *
     * @param collection the input collection
     * @return count of elements
     */
    public static synchronized int countValuesUsingParallelForEach(final SequencedCollection<?> collection) {
        final int[] counter = new int[1];
        collection.parallelStream().forEach(e -> counter[0]++);
        return counter[0];
    }

    /**
     * Counts elements using parallel forEachOrdered.
     *
     * @param collection the input collection
     * @return count of elements
     */
    public static int countValuesUsingParallelForEachOrdered(final SequencedCollection<?> collection) {
        final int[] counter = new int[1];
        collection.parallelStream().forEachOrdered(e -> counter[0]++);
        return counter[0];
    }

    /**
     * Calculates sum using reduce.
     *
     * @param collection the input collection
     * @return Optional containing the sum
     */
    public static Optional<Integer> calculateSumUsingReduce(final Collection<Integer> collection) {
        return collection.stream().reduce(Integer::sum);
    }

    /**
     * Calculates sum using mapToInt.
     *
     * @param collection the input collection
     * @return sum of elements
     */
    public static Number calculateSumUsingIntMap(final Collection<? extends Number> collection) {
        return collection.stream().mapToInt(Number::intValue).sum();
    }

    /**
     * Calculates average using mapToInt.
     *
     * @param collection the input collection
     * @return OptionalDouble containing the average
     */
    public static OptionalDouble calculateAverageUsingIntMap(final Collection<? extends Number> collection) {
        return collection.stream().mapToInt(Number::intValue).average();
    }

    /**
     * Calculates average using collect.
     *
     * @param collection the input collection
     * @return average of elements
     */
    public static double calculateAverageUsingCollect(final Collection<? extends Number> collection) {
        return collection.stream().collect(Collectors.averagingInt(Number::intValue));
    }

    /**
     * Generates even integers starting from seed.
     *
     * @param seed the starting value (must be even)
     * @param amount the number of elements to generate
     * @return collection of even integers
     * @throws IllegalArgumentException if seed is not even
     */
    public static SequencedCollection<Integer> generateEvenIntegers(final int seed, final int amount) {
        if (seed % 2 != 0) {
            throw new IllegalArgumentException();
        }
        return Stream.iterate(seed, n -> n + 2).limit(amount).toList();
    }

    /**
     * Generates random integers in specified range.
     *
     * @param origin the origin (inclusive) of the random numbers
     * @param bound the bound (exclusive) of the random numbers
     * @param amount the number of elements to generate
     * @return collection of random integers
     */
    public static Collection<Integer> generateRandomIntegers(final int origin, final int bound, final int amount) {
        return Stream.generate(() -> ThreadLocalRandom.current().nextInt(origin, bound))
                .limit(amount)
                .toList();
    }

    /**
     * Gets random integers in specified range.
     *
     * @param origin the origin (inclusive) of the random numbers
     * @param bound the bound (exclusive) of the random numbers
     * @param amount the number of elements to generate
     * @return collection of random integers
     */
    public static SequencedCollection<Integer> getRandomIntegers(final int origin, final int bound, final int amount) {
        return ThreadLocalRandom.current().ints(origin, bound).limit(amount).boxed().toList();
    }

    /**
     * Returns self-dividable numbers or null for zero.
     *
     * @param collection the input collection
     * @return collection with numbers or null for zero
     */
    public static SequencedCollection<Integer> getSelfDividableNumbersOrNull(
            final SequencedCollection<Integer> collection) {
        return collection.stream()
                .map(n -> {
                    try {
                        int result = n / n;
                        return n;
                    } catch (ArithmeticException e) {
                        return null;
                    }
                })
                .toList();
    }

    /**
     * Flattens a collection of collections.
     *
     * @param collections the input collection of collections
     * @param <T> the type of elements in the collections
     * @return flattened collection
     */
    public static <T> SequencedCollection<T> flattenCollections(
            final SequencedCollection<SequencedCollection<T>> collections) {
        return collections.stream().flatMap(Collection::stream).toList();
    }

    /**
     * Sorts collection in natural order.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return sorted collection
     */
    public static <T extends Comparable<? super T>> SequencedCollection<T> sortInNaturalOrder(
            final Collection<T> collection) {
        return collection.stream().sorted().toList();
    }

    /**
     * Sorts collection in reverse order.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return reverse sorted collection
     */
    public static <T extends Comparable<? super T>> SequencedCollection<T> sortInReverseOrder(
            final Collection<T> collection) {
        return collection.stream().sorted(Comparator.reverseOrder()).toList();
    }

    /**
     * Partitions collection by evenness.
     *
     * @param collection the input collection
     * @return map partitioning numbers by evenness
     */
    public static Map<Boolean, List<Integer>> partitionByEvenness(final SequencedCollection<Integer> collection) {
        return collection.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
    }

    /**
     * Groups numbers by their sign.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return map grouping numbers by sign
     */
    public static <T extends Number> Map<String, List<T>> groupByNumberSign(final SequencedCollection<T> collection) {
        return collection.stream().collect(Collectors.groupingBy(n -> {
            double value = n.doubleValue();
            if (value < 0) {
                return "negative";
            } else if (value > 0) {
                return "positive";
            } else {
                return "zero";
            }
        }));
    }

    /**
     * Creates map with merged values.
     *
     * @param collection the input collection
     * @return map with original numbers as keys and values as number+1
     */
    public static Map<Integer, Integer> mapWithMergedValues(final Collection<Integer> collection) {
        return collection.stream()
                .collect(Collectors.toMap(
                        n -> n,
                        n -> n + 1,
                        (existing, replacement) -> existing * replacement
                ));
    }

    /**
     * Checks if collection contains any negative numbers.
     *
     * @param collection the input collection
     * @return true if any negative number exists
     */
    public static boolean containsAnyNegative(final Collection<? extends Number> collection) {
        return collection.stream().anyMatch(n -> n.doubleValue() < 0);
    }

    /**
     * Checks if all numbers are positive.
     *
     * @param collection the input collection
     * @return true if all numbers are positive
     */
    public static boolean areAllPositive(final Collection<? extends Number> collection) {
        return collection.stream().allMatch(n -> n.doubleValue() > 0);
    }

    /**
     * Checks if collection contains no zeros.
     *
     * @param collection the input collection
     * @return true if no zeros exist
     */
    public static boolean containsNoZero(final Collection<? extends Number> collection) {
        return collection.stream().noneMatch(n -> n.doubleValue() == 0);
    }

    /**
     * Concatenates two collections.
     *
     * @param collection1 first collection
     * @param collection2 second collection
     * @param <T> the type of elements in the collections
     * @return concatenated collection
     */
    public static <T> SequencedCollection<T> concatenateCollections(
            final SequencedCollection<T> collection1,
            final SequencedCollection<T> collection2) {
        return Stream.concat(collection1.stream(), collection2.stream()).toList();
    }

    /**
     * Takes elements while they are positive.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return collection of elements while they are positive
     */
    public static <T extends Number> SequencedCollection<T> takeWhileNumberIsPositive(
            final SequencedCollection<T> collection) {
        return collection.stream().takeWhile(n -> n.doubleValue() > 0).toList();
    }

    /**
     * Drops elements while they are positive.
     *
     * @param collection the input collection
     * @param <T> the type of elements in the collection
     * @return collection after dropping positive elements
     */
    public static <T extends Number> SequencedCollection<T> dropWhileNumberIsPositive(
            final SequencedCollection<T> collection) {
        return collection.stream().dropWhile(n -> n.doubleValue() > 0).toList();
    }

    /**
     * Main
     * @param args
     */
    public static void main(final String[] args) {
        // Do not worry about this data structure. It is just a collection. You can use it as any other collection.
        final SequencedCollection<Integer> numbers = new CopyOnWriteArrayList<>(List.of(2, 2, -3, 0, 2));

        System.out.println(generateNumbersExclusive(2, 5)); // [2, 3, 4]
        System.out.println(generateNumbersInclusive(2, 5)); // [2.0, 3.0, 4.0, 5.0]
        System.out.println();

        System.out.println(convertToList(numbers)); // [2, 2, -3, 0, 2]
        System.out.println(getDistinctValues(numbers)); // [2, -3, 0]
        System.out.println(convertToUnmodifiableSet(numbers)); // [0, 2, -3] (Here numbers may be in a different order.)
        System.out.println(findMaxValue(numbers)); // Optional[2]
        System.out.println(findMinValue(numbers)); // Optional[-3]
        System.out.println();

        System.out.println(filterNonNegativeValues(numbers)); // [2, 2, 0, 2]
        System.out.println(findAnyNegativeNumber(numbers)); // Optional[-3]
        System.out.println(findAnyNegativeNumberOrElse(numbers)); // -3
        System.out.println(findAnyNegativeNumberOrElse(filterNonNegativeValues(numbers))); // Integer.MAX_VALUE
        System.out.println(findFirstNegativeNumberOrElseGet(numbers)); // -3
        System.out.println(convertToStringList(numbers)); // [2, 2, -3, 0, 2] (strings)
        System.out.println(convertPositivesToSingleString(numbers)); // String[2, 2, 2]
        System.out.println();

        System.out.println(getLimitedList(numbers, 3)); // [2, 2, -3]
        System.out.println(getLimitedList(numbers, 0)); // []
        System.out.println(getLimitedList(numbers, numbers.size() * 2)); // [2, 2, -3, 0, 2]
        System.out.println();

        System.out.println(getListFromIndex(numbers, 3)); // [0, 2]
        System.out.println(getListFromIndex(numbers, 0)); // [2, 2, -3, 0, 2]
        System.out.println(getListFromIndex(numbers, Byte.MAX_VALUE)); // []
        System.out.println();

        System.out.println(getSquaredValues(numbers)); // [4.0, 4.0, 9.0, 0.0, 4.0]
        System.out.println(Arrays.toString(divideValues(numbers, 2.0))); // [1.0, 1.0, -1.5, 0.0, 1.0]
        System.out.println();

        System.out.println(countValues(numbers)); // 5
        System.out.println(countValuesUsingForEach(numbers)); // 5
        System.out.println(countValuesUsingForEachOrdered(numbers)); // 5

        final int TEN_MILLION = 10_000_000;

        // !=? 10000000
        System.out.println(countValuesUsingParallelForEach(IntStream.range(0, TEN_MILLION).boxed().toList()));

        // 10000000
        System.out.println(countValuesUsingParallelForEachOrdered(IntStream.range(0, TEN_MILLION).boxed().toList()));
        System.out.println();


        System.out.println(calculateSumUsingReduce(numbers)); // Optional[3]
        System.out.println(calculateSumUsingIntMap(numbers)); // 3
        System.out.println(calculateAverageUsingIntMap(numbers)); // OptionalDouble[0.6]
        System.out.println(calculateAverageUsingCollect(numbers)); // 0.6
        System.out.println();

        System.out.println(generateEvenIntegers(2, 5)); // [2, 4, 6, 8, 10]
        try {
            generateEvenIntegers(3, 5); // Is not even, IllegalArgumentException
            System.out.println("This line should not be printed");
        } catch (final IllegalArgumentException ignored) {
            System.out.println("IllegalArgumentException was thrown (as expected)");
        }
        System.out.println();

        final int ORIGIN = -9;
        final int BOUND = 9;
        final int AMOUNT = 5;
        System.out.println(generateRandomIntegers(ORIGIN, BOUND, AMOUNT)); // [*, *, *, *, *]
        System.out.println(getRandomIntegers(ORIGIN, BOUND, AMOUNT)); // [*, *, *, *, *]
        System.out.println();

        System.out.println(getSelfDividableNumbersOrNull(numbers)); // [2, 2, -3, null, 2]

        // [2, 2, -3, 0, 2, 2, 0, -3, 2, 2]
        System.out.println(flattenCollections(List.of(numbers, numbers.reversed())));
        System.out.println();

        System.out.println(sortInNaturalOrder(numbers)); // [-3, 0, 2, 2, 2]
        System.out.println(sortInReverseOrder(numbers)); // [2, 2, 2, 0, -3]
        System.out.println();

        System.out.println(partitionByEvenness(numbers)); // {false=[-3], true=[2, 2, 0, 2]}
        System.out.println(groupByNumberSign(numbers)); // {zero=[0], negative=[-3], positive=[2, 2, 2]}
        System.out.println(mapWithMergedValues(numbers)); // {0=1, -3=-2, 2=27} (for 2: (2 + 1)(2 + 1)(2 + 1) = 27)
        System.out.println();

        System.out.println(containsAnyNegative(numbers)); // true
        System.out.println(areAllPositive(numbers)); // false
        System.out.println(containsNoZero(numbers)); // false
        System.out.println();

        System.out.println(concatenateCollections(numbers, numbers.reversed())); // [2, 2, -3, 0, 2, 2, 0, -3, 2, 2]
        System.out.println(takeWhileNumberIsPositive(numbers)); // [2, 2]
        System.out.println(dropWhileNumberIsPositive(numbers)); // [-3, 0, 2]
    }
}
