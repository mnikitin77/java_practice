package com.mvnikitin.practice.lesson2.list;

import com.mvnikitin.practice.lesson2.MyList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyLinkedList<E> implements MyList<E>, Iterable<E>{

    private final Node EMPTY_NODE = new Node();

    private Node _head;
    private Node _tail;
    private int _size;

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        Objects.requireNonNull(element);

        Node node = getNodeByIndex(index);
        if (Objects.equals(node, EMPTY_NODE)) {
            throw new NoSuchElementException();
        }
        Node newNode = new Node(element);
        newNode.next = node.next;
        node.next.prev = newNode;
        node.next = newNode;
        newNode.prev = node;

        _size++;
    }

    @Override
    public boolean add(E element) {
        Objects.requireNonNull(element);

        Node newNode = new Node(element);

        if(_head == null) {
            _head = newNode;
            _tail = _head;
        } else {
            _tail.prev = newNode;
            newNode.next = _tail;
            _tail = newNode;
        }

        _size++;
        return true;
    }

    @Override
    public void clear() {
        _head = null;
        _tail = null;
        _size = 0;
    }

    @Override
    public boolean contains(Object o) {
        for(E e: this) {
            if (Objects.equals(o, e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        Node node = getNodeByIndex(index);
        if (!Objects.equals(node, EMPTY_NODE)) {
            return node.item;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int currentIndex = 0;
        for(E e: this) {
            if (Objects.equals(o, e)) {
                return currentIndex;
            }
            currentIndex++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public int lastIndexOf(Object o) {

        Objects.requireNonNull(o);

        Node current = _tail;
        int currentIndex = _size -1;
        while (current.next != null) {
            if(Objects.equals(current.item,o)) {
                return currentIndex;
            }
            currentIndex--;
            current = current.next;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        Node node = getNodeByIndex(index);

        E retVal = Objects.equals(node, EMPTY_NODE) ? null : node.item;
        eliminateCurrentNode(node);

        _size--;
        return retVal;
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        Node node = getNodeByObject(o);

        if (Objects.equals(node, EMPTY_NODE)) {
            return false;
        }

        eliminateCurrentNode(node);

        _size--;
        return true;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Objects.requireNonNull(element);

        Node node = getNodeByIndex(index);
        E retVal = Objects.equals(node, EMPTY_NODE) ? null : node.item;
        node.item = element;

        return retVal;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[_size];
        int i = 0;
        for(E item: this) {
            array[i] = item;
            i++;
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MyLinkedList = [");
        boolean isFirst = true;
        for(E item: this) {
            if(isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append(", ");
            }
            stringBuilder.append(item);
        }
        return stringBuilder.append(']').toString();
    }

    class Node {
        E item;
        Node prev;
        Node next;

        public Node() {}

        public Node(E item) {
            this.item = item;
        }
    }

    class MyListIterator implements Iterator<E> {

        Node pointer = _head;

        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        @Override
        public E next() {
            if (pointer == null) {
                throw new NoSuchElementException();
            } else {
                Node current = pointer;
                pointer = pointer.prev;
                return current.item;
            }
        }
    }

    private Node getNodeByIndex(int index) {
        int i = 0;
        Node current = _head;

        while(i <= index && current != null) {
            if (i == index) {
                return current;
            }
            current = current.prev;
            i++;
        }

        return EMPTY_NODE;
    }

    private Node getNodeByObject(Object o) {
        Node current = _head;

        while(current != null) {
            if (Objects.equals(current.item, o)) {
                return current;
            }
            current = current.prev;
        }

        return EMPTY_NODE;
    }

    private void checkIndex(int index) {
        if (index > _size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void eliminateCurrentNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
