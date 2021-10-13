package com.mvnikitin.practice.lesson2.arraylist;

import com.mvnikitin.practice.lesson2.MyList;
import com.mvnikitin.practice.lesson2.list.MyLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.lang.System.arraycopy;

public class MyArrayList<E> implements MyList<E>, Iterable<E> {

    private static final int INITIAL_CAPACITY = 10;
    private static final float GROWTH_FACTOR = 1.5f;

    private Object[] data = {};

    void ensureCapacity(int minCapacity) {
        if (data.length < minCapacity) {
            Object[] newArray = new Object[minCapacity];
            if(data.length > 0) {
                arraycopy(data, 0, newArray, 0, data.length);
            }
            data = newArray;
        }
    }

    void trimToSize() {
        if (data.length > 0 ) {
            int newSize = getIndexOfLastNonNull() + 1;
            if(data.length > newSize) {
                Object[] newArray = new Object[newSize];
                arraycopy(data, 0, newArray, 0, newSize);
                data = newArray;
            }
        }
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        checkSize(index + 1);
        shiftRight(index + 1);
        data[index] = element;
    }

    @Override
    public boolean add(E element) {
        int index = getIndexOfLastNonNull() + 1;
        checkSize(index);
        data[index] = element;

        return true;
    }

    @Override
    public void clear() {
        data = new Object[0];
    }

    @Override
    public boolean contains(Object o) {
        for (Object item: data) {
            if (Objects.equals(item, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < data.length; i++) {
            if (Objects.equals(data[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (data.length == 0) {
            return true;
        }
        for(Object item: data) {
            if(item != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = data.length - 1; i >= 0; i--) {
            if (Objects.equals(data[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Object retVal = data[index];
        shiftLeft(index);

        return (E) retVal;
    }

    @Override
    public boolean remove(Object o) {

        int index = indexOf(o);

        if (index == -1) {
            return false;
        }
        shiftLeft(index);

        return true;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Object retVal = data[index];
        data[index] = element;
        return (E) retVal;
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public Object[] toArray() {
        Object[] copy = new Object[data.length];
        arraycopy(data, 0, copy, 0, data.length);
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MyArrayList = [");
        boolean isFirst = true;
        for(Object item: data) {
            if(isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append(", ");
            }
            stringBuilder.append(item);
        }
        return stringBuilder.append(']').toString();
    }


    private int getIndexOfLastNonNull() {
        for(int i = data.length - 1; i >= 0; i--) {
            if (data[i] != null) {
                return i;
            }
        }
        return data.length - 1;
    }

    private void checkSize(int index) {
        int newSize = 0;
        if (data.length == 0) {
            newSize = INITIAL_CAPACITY;
        } else if (index >= data.length) {
            newSize = (int) (data.length * GROWTH_FACTOR);
        }
        ensureCapacity(newSize);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void shiftLeft(int indexTo) {
        if (data.length - indexTo - 1 >= 0) {
            arraycopy(data, indexTo + 1, data, indexTo, data.length - indexTo - 1);
            data[data.length - 1] = null;
        }
    }

    private void shiftRight(int indexTo) {
        if (data.length - indexTo + 1 >= 0) {
            arraycopy(data, indexTo - 1, data, indexTo, data.length - indexTo + 1);
        }
    }

    class MyArrayListIterator implements Iterator<E> {

        int pointer = 0;

        @Override
        public boolean hasNext() {
            return pointer < data.length;
        }

        @Override
        public E next() {
            if (pointer == data.length) {
                throw new NoSuchElementException();
            } else {
                int current = pointer;
                pointer = pointer + 1;
                return (E) data[current];
            }
        }
    }
}
