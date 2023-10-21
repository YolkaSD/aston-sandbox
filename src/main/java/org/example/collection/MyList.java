package org.example.collection;

public interface MyList<E extends Comparable<E>> {
    void add(int index, E element);

    boolean addAll(MyList<? extends E> c);

    E get(int index);

    boolean isEmpty();

    E remove(int index);

    boolean remove(Object o);

    void clear();

    int size();

    void sort();


}
