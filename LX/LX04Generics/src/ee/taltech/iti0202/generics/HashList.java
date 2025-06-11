package ee.taltech.iti0202.generics;

import java.util.Objects;

/**
 * HashList — это структура данных, объединяющая свойства ArrayList и HashMap.
 * <p>
 * Она сохраняет порядок добавления элементов (как ArrayList) и обеспечивает
 * быструю проверку наличия элемента (contains) за счёт использования хеш-таблицы (как HashMap).
 * <p>
 * Внутреннее устройство:
 * - Массив `order` хранит элементы в порядке их добавления.
 * - Массив `elements` содержит цепочки элементов (Node) по хешу, как в HashMap.
 * <p>
 * Основные операции:
 * - add(T): добавление за O(1) в среднем.
 * - get(index): доступ к элементу по индексу за O(1).
 * - contains(element): поиск за O(1) в среднем.
 * - remove(index): удаление сдвигает массив, может занять O(n).
 *
 * @param <T> тип элементов в списке
 */
public class HashList<T> {

    // Начальная ёмкость хеш-таблицы
    private static final int INITIAL_CAPACITY = 10;

    // Коэффициент загрузки для увеличения размера хеш-таблицы
    // Если количество элементов превышает 75% от ёмкости, хеш-таблица увеличится.
    private static final double LOAD_FACTOR = 0.75;

    private static final int TWO = 2;

    /**
     * Внутренний класс Node — обёртка над элементом,
     * используется для реализации цепочек в хеш-таблице (открытая адресация).
     */
    private static class Node<T> {
        T value;

        // ссылка на следующий элемент в цепочке, если хеш для двух элементов совпадает.
        Node<T> next;


        Node(T value) {
            this.value = value;
        }
    }

    // 📝 Блокнот (order) - куда ты записываешь вещи по порядку
    // 🗂️ Картотека (elements) - где ты быстро можешь найти вещи по их "имени"

    // Это как картотека (быстрый поиск)
    private Node<T>[] elements = new Node[INITIAL_CAPACITY];
    private Object[] order = new Object[INITIAL_CAPACITY]; // T[]

    // Сколько всего элементов
    private int size = 0;


    /**
     * Добавляет элемент в конец списка.
     * Элемент попадает и в массив порядка, и в хеш-таблицу.
     *
     * @param element элемент, который нужно добавить
     */
    public void add(T element) {
        if ((double) (size / elements.length) >= LOAD_FACTOR) {
            resize();
        }

        if (size >= order.length) {
            growOrderArray();
        }

        // 123456 % 10 = 6 (так как 123456 / 10 = 12345 с остатком 6)
        int index = Math.abs(element.hashCode()) % elements.length;
        Node<T> newNode = new Node<>(element);

        if (elements[index] == null) {
            elements[index] = newNode;
        } else {
            Node<T> current = elements[index];
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        order[size] = element;
        size++;
    }

    /**
     * Возвращает элемент по заданному индексу.
     *
     * @param index индекс элемента
     * @return элемент на позиции index
     * @throws IndexOutOfBoundsException если индекс вне допустимого диапазона
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) order[index];
    }

    /**
     * Возвращает текущее количество элементов в списке.
     *
     * @return размер списка
     */
    public int size() {
        return size;
    }

    /**
     * Удаляет элемент по индексу. Обновляет как массив порядка, так и хеш-таблицу.
     *
     * @param index индекс удаляемого элемента
     * @return удалённый элемент
     * @throws IndexOutOfBoundsException если индекс некорректен
     */
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removed = (T) order[index];

        // Сдвиг элементов в массиве порядка
        System.arraycopy(order, index + 1, order, index, size - index - 1);
        order[size - 1] = null;
        size--;

        // Удаление из хеш-таблицы
        int hashIndex = Math.abs(removed.hashCode()) % elements.length;
        Node<T> current = elements[hashIndex];
        Node<T> prev = null;

        while (current != null) {
            if (current.value.equals(removed)) {
                if (prev == null) {
                    elements[hashIndex] = current.next;
                } else {
                    prev.next = current.next;
                }
                break;
            }
            prev = current;
            current = current.next;
        }

        return removed;
    }

    /**
     * Проверяет, содержится ли элемент в списке.
     *
     * @param element элемент для поиска
     * @return true, если элемент найден; иначе false
     */
    public boolean contains(T element) {
        int index = Math.abs(element.hashCode()) % elements.length;
        Node<T> current = elements[index];
        while (current != null) {
            if (current.value.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Добавляет в список все элементы из переданной коллекции.
     * Поддерживает обобщённые подтипы.
     *
     * @param list коллекция элементов для добавления
     */
    public void addAll(Iterable<? extends T> list) {
        for (T item : list) {
            add(item);
        }
    }

    /**
     * Увеличивает размер хеш-таблицы, если превышен допустимый коэффициент загрузки.
     * Выполняет повторную хешировку всех элементов.
     */
    private void resize() {
        Node<T>[] oldElements = elements;
        elements = new Node[oldElements.length * TWO];

        for (Node<T> node : oldElements) {
            while (node != null) {
                rehash(node.value);
                node = node.next;
            }
        }
    }

    /**
     * Повторно добавляет элемент в новую хеш-таблицу при увеличении размера.
     *
     * @param element элемент для повторного добавления
     */
    private void rehash(T element) {
        // 1. Вычисляем новый индекс в увеличенной таблице
        int index = Math.abs(element.hashCode()) % elements.length;
        Node<T> newNode = new Node<>(element);
        if (elements[index] == null) {
            elements[index] = newNode;
        } else {
            Node<T> current = elements[index];
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    /**
     * new Object[order.length * 2] - создает новый массив объектов,
     * размер которого в два раза больше текущего массива order.
     *
     * System.arraycopy(order, 0, newOrder, 0, order.length) - копирует
     * все элементы из старого массива order в новый массив newOrder.
     *
     * Первый аргумент (order) - исходный массив
     * Второй аргумент (0) - начальная позиция в исходном массиве
     * Третий аргумент (newOrder) - целевой массив
     * Четвертый аргумент (0) - начальная позиция в целевом массиве
     * Пятый аргумент (order.length) - количество копируемых элементов
     */
    private void growOrderArray() {
        Object[] newOrder = new Object[order.length * TWO];
        System.arraycopy(order, 0, newOrder, 0, order.length);
        order = newOrder;
    }
}
