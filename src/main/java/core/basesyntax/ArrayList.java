package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float GROW_MULTIPLYER = 1.5f;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elements.length) {
            grow();
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound of list size");
        }
        System.arraycopy(elements, index, elements, index + 1,
                elements.length - index - 1);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            elements[size] = list.get(i);
            size++;
        }
    }

    private void grow() {
        Object[] resizedElements = new Object[Math.round(elements.length * GROW_MULTIPLYER)];
        System.arraycopy(elements, 0,
                resizedElements,0, elements.length);
        elements = resizedElements;
    }

    private void isInRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound of list size");
        }
    }

    @Override
    public T get(int index) {
        isInRange(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        isInRange(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isInRange(index);
        size--;
        T desiredValue = (T) elements[index];
        System.arraycopy(elements, index + 1,
                elements, index, elements.length - index - 1);
        return desiredValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                size--;
                T desiredValue = (T) elements[i];
                System.arraycopy(elements, i + 1,
                        elements, i, elements.length - i - 1);
                return desiredValue;
            }
        }
        throw new NoSuchElementException("Incorrect value input");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
