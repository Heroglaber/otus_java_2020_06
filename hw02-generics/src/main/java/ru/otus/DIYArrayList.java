package ru.otus;

import java.util.*;

public class DIYArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size = 0;

    private Object[] elementData;

    public DIYArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public DIYArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    private void grow() {
        if(size < elementData.length - 1) {
            size++;
            return;
        }
        int newCapacity = elementData.length * 2;
        elementData = Arrays.copyOf(elementData, newCapacity);
        size++;
    }

    @Override
    public boolean add(E e) {
        elementData[size] = e;
        grow();
        return true;
    }

    @Override
    public void add(int index, E element) {
        if(index > size || index < 0)
            throw new IndexOutOfBoundsException();
        elementData[index] = element;
        grow();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new DIYIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Iterator<? extends E> iterator = c.iterator();
        while (iterator.hasNext()) {
            this.add(iterator.next());
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(int index, E element) {
        if(index > size || index < 0)
            throw new IndexOutOfBoundsException();
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        if(index > size || index < 0)
            throw new IndexOutOfBoundsException();
        E oldValue = (E) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, elementData.length - 1 - index);
        size--;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DIYListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private class DIYIterator implements Iterator<E> {

        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        DIYIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            DIYArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }

    private class DIYListIterator extends DIYIterator implements ListIterator<E> {

        DIYListIterator() {
            super();
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public E previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            cursor = i;
            return (E) elementData[lastRet = i];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            DIYArrayList.this.set(lastRet, e);
        }

        @Override
        public void add(E e) {
            int i = cursor;
            DIYArrayList.this.add(i, e);
            cursor = i + 1;
            lastRet = -1;
        }
    }
}
