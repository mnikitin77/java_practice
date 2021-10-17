package com.mvnikitin.practice.lesson2;

import java.util.Iterator;

public interface MyList<E> {

    void add(int index, E element);

    boolean add(E element);

    void clear();

    boolean contains(Object o);

    E get(int index);

    int indexOf(Object o);

    boolean isEmpty();

    Iterator<E> iterator();

    int lastIndexOf(Object o);

    E remove(int index);

    boolean remove(Object o);

    E set(int index, E element);

    int size();

    Object[] toArray();
}
