package org.example;

import org.example.collection.MyArrayListImpl;
import org.example.collection.MyList;

public class App {
    public static void main(String[] args) {
        MyList<String> stringMyList = new MyArrayListImpl<>();

        //add
        stringMyList.add(0, null);
        stringMyList.add(1, "A");
        stringMyList.add(2, "F");
        stringMyList.add(3, "G");
        stringMyList.add(4, "D");
        stringMyList.add(5, "B");
        stringMyList.add(6, "N");
        stringMyList.add(7, "G");
        stringMyList.add(8, "Z");
        System.out.println(stringMyList);
        //add c
        MyList<String> stringMyList2 = new MyArrayListImpl<>();
        stringMyList2.add(0, "V");
        stringMyList2.add(1, "C");
        stringMyList2.add(2, "D");
        stringMyList2.add(3, "P");
        stringMyList2.add(4, null);
        stringMyList.addAll(stringMyList2);
        System.out.println(stringMyList);

        //remove index
        stringMyList.remove(0);
        System.out.println(stringMyList);

        //remove O
        stringMyList.remove(null);
        stringMyList.remove(null);
        System.out.println(stringMyList);

        //sort quick

        stringMyList.sort();
        System.out.println(stringMyList);
    }
}
