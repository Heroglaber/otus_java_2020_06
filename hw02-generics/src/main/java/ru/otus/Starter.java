package ru.otus;

import java.util.*;

public class Starter {
    public static void main(String[] args) {
        List<Integer> list = new DIYArrayList<>();
        for(int i = 0; i < 20; i++) {
            list.add((int) (Math.random() * 1000));
        }

        System.out.println(Arrays.toString(list.toArray()));
        Collections.sort(list);
        System.out.println(Arrays.toString(list.toArray()));

        List<Integer> list2 = new DIYArrayList<>();
        for(int i = 0; i < 20; i++) {
            list2.add((int) (Math.random() * 1000));
        }
        System.out.println(Arrays.toString(list2.toArray()));

        Collections.addAll(list, 41, 42);
        System.out.println(Arrays.toString(list.toArray()));
        list.addAll(list2);
        System.out.println(Arrays.toString(list.toArray()));

        List<Integer> list3 = new DIYArrayList<>();
        for(int i = 0; i < 45; i++) {
            list3.add(0);
        }
        System.out.println(list3.size());
        Collections.copy(list3, list);
        System.out.println(Arrays.toString(list3.toArray()));
    }
}
