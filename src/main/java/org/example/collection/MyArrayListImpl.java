package org.example.collection;

import java.util.Iterator;

public class MyArrayListImpl<E extends Comparable<E>> implements MyList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elementData = new Object[DEFAULT_CAPACITY];

    private int size = 0;

    public void add(int index, E element) {
        checkIndex(index);

        if (isFull()) {
            elementData = resize(elementData.length);
        }

        if (index != size) {
            arrayCopy(index);
        }

        elementData[index] = element;
        size++;
    }

    @Override
    public boolean addAll(MyList<? extends E> c) {
        int newSize = c.size() + size;
        if (size < newSize) {
            elementData = resize(newSize);
        }

        for (int i = 0; i < c.size(); i++) {
            add(size, c.get(i));
        }

        return true;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E remove(int index) {
        E removedElement = get(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public boolean remove(Object o) {
        int index = 0;
        for(E e: this) {
            if (o == null) {
                if (null == e) {
                    remove(index);
                    return true;
                }
            } else {
                if (e.equals(o)) {
                    remove(index);
                    return true;
                }
            }
            index++;
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void sort() {
        quickSort(elementData, 0, size - 1);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public E next() {
                E element = (E) elementData[cursor];
                cursor++;
                return element;
            }
        };
    }


    private void quickSort(Object[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex, right);
        }
    }

    private int partition(Object[] arr, int left, int right) {
        E pivot = (E) arr[left];

        while (left <= right) {
            while (((E) arr[left]).compareTo(pivot) < 0) {
                left++;
            }

            while (((E) arr[right]).compareTo(pivot) > 0) {
                right--;
            }

            if (left <= right) {
                E tmp = (E) arr[right];
                arr[right] = arr[left];
                arr[left] = tmp;
                left++;
                right--;
            }
        }
        return left;
    }


    private Object[] resize(int newLength) {
        int newCapacity = newLength + (newLength >> 1);
        Object[] newElementData = new Object[newCapacity];
        for (int i = 0; i < size - 1; i++) {
            newElementData[i] = elementData[i];
        }
        return newElementData;
    }

    private void arrayCopy(int index) {
        Object[] newElementData = new Object[elementData.length];

        int oldIndex = 0;
        for (int i = 0; oldIndex < size; i++) {
            if (i == index) {
                continue;
            }
            newElementData[i] = elementData[oldIndex];
            oldIndex++;

        }
        elementData = newElementData;
    }

    private boolean isFull() {
        return size == elementData.length;
    }


    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(elementData[i]);
            if (size - 1 != i) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
