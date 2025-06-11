package check;

import exceptions.UniException;
import java.util.List;

public class Checker<T> {

    /**
     * Checks if the item can be added to the list.
     * Throws exception if the item is null or already exists in the list.
     *
     * @param list The list to check.
     * @param item The item to be added.
     * @throws UniException if item is null or already exists in the list.
     */
    public void addingCheck(List<T> list, T item) throws UniException {
        if (item == null) {
            throw new UniException(UniException.Reason.CANNOT_BE_NULL);
        }

        if (list.contains(item)) {
            throw new UniException(UniException.Reason.ALREADY_CONTAINS);
        }
    }

    /**
     * Checks if the item can be removed from the list.
     * Throws exception if the item is null or doesn't exist in the list.
     *
     * @param list The list to check.
     * @param item The item to be removed.
     * @throws UniException if item is null or does not exist in the list.
     */
    public void removingCheck(List<T> list, T item) throws UniException {
        if (item == null) {
            throw new UniException(UniException.Reason.CANNOT_BE_NULL);
        }

        if (!list.contains(item)) {
            throw new UniException(UniException.Reason.DO_NOT_CONTAIN);
        }
    }

    /**
     * Checks if the item is null.
     * Throws exception if the item is null
     *
     * @param item The item to be checked.
     * @throws UniException if item is null.
     */
    public void nullCheck(T item) throws UniException {
        if (item == null) {
            throw new UniException(UniException.Reason.CANNOT_BE_NULL);
        }
        if (item instanceof Integer) {
            if (((Integer) item).intValue() <= 0) {
                throw new UniException(UniException.Reason.NEGATIVE_NUMBER);
            }
        }
    }
}
