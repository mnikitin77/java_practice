package com.mvnikitin.practice.lesson2.list;

import com.mvnikitin.practice.lesson2.MyList;

import java.util.Iterator;

public class MyListMain {
    public static void main(String[] args) {
        MyLinkedList<Integer> myList = new MyLinkedList<>();
        printInfo(myList);

        myList.add(1);
        myList.add(12);
        myList.add(17);
        printInfo(myList);

        System.out.println("foreach: ");
        int count_1 = 0;
        for (Integer item
                : myList) {
            System.out.println("item[" + count_1 + "] = " + item);
            count_1++;
        }

        System.out.println("Adding 99 at index = 2");
        myList.add(2, 99);
        printInfo(myList);

        Object[] objArray = myList.toArray();
        Integer[] intArray = new Integer[objArray.length];
        System.arraycopy(objArray, 0, intArray, 0, objArray.length);
        System.out.print("toArray() =" );
        for (int i: intArray) {
            System.out.print(" " + i);
        }
        System.out.println();

        System.out.println("Iterator");
        Iterator<Integer> iter = myList.iterator();
        while (iter.hasNext()) {
            System.out.println("next = " + iter.next());
        }

        System.out.println("Added three items");
        myList.add(100);
        myList.add(1000);
        myList.add(100500);
        printInfo(myList);

        System.out.println("contains(100): " + myList.contains(100));
        System.out.println("indexOf(100): " + myList.indexOf(100));
        System.out.println("lastIndexOf(100): " + myList.lastIndexOf(100));
        System.out.println("get(5): " + myList.get(5));
        System.out.println("isEmpty(): " + myList.isEmpty());
        System.out.println("set(5, 999): " + myList.set(5, 999));
        System.out.println("get(5): " + myList.get(5));

        System.out.println("remove((Object)999)");
        myList.remove((Object) 999);
        printInfo(myList);

        System.out.println("remove(3)");
        myList.remove(3);
        printInfo(myList);

        System.out.println("clear()");
        myList.clear();
        printInfo(myList);
    }

    private static void printInfo(MyList<Integer> list) {
        System.out.println("List size = " + list.size() + " " + list);
    }
}


//        List size = 0 MyLinkedList=[]
//        List size = 3 MyLinkedList=[1, 12, 17]
//        foreach:
//        item[0] = 1
//        item[1] = 12
//        item[2] = 17
//        Adding 99 at index = 2
//        List size = 4 MyLinkedList=[1, 12, 99, 17]
//        toArray() = 1 12 99 17
//        Iterator
//        next = 1
//        next = 12
//        next = 99
//        next = 17
//        Added three items
//        List size = 7 MyLinkedList=[1, 12, 99, 17, 100, 1000, 100500]
//        contains(100): true
//        indexOf(100): 4
//        lastIndexOf(100): 4
//        get(5): 1000
//        isEmpty(): false
//        set(5, 999): 1000
//        get(5): 999
//        remove((Object)999)
//        List size = 6 MyLinkedList=[1, 12, 99, 17, 100, 100500]
//        remove(3)
//        List size = 5 MyLinkedList=[1, 12, 99, 100, 100500]
//        clear()
//        List size = 0 MyLinkedList=[]
