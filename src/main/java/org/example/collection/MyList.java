package org.example.collection;

import java.util.Iterator;

public interface MyList<E extends Comparable<E>> extends Iterable<E> {
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
