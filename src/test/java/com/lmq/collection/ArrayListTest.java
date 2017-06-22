package com.lmq.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by liumingqi on 17/6/18.
 */
public class ArrayListTest {
    @Test
    public void size() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        assertEquals(0, arrayList.size());
        arrayList.add(1);
        assertEquals(1, arrayList.size());
    }

    @Test
    public void isEmpty() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        assertEquals(true, arrayList.isEmpty());
        arrayList.add(1);
        assertEquals(false, arrayList.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        arrayList.add(1);
        arrayList.add(null);
        assertEquals(true, arrayList.contains(1));
        assertEquals(true, arrayList.contains(null));
    }

    @Test
    public void iterator() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        arrayList.add(1);
        arrayList.add(2);
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
            iterator.remove();
        }
        assertEquals(true, arrayList.isEmpty());
    }

    @Test
    public void add() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        arrayList.add(1);
        arrayList.add(null);
        assertEquals(new Integer(1), arrayList.get(0));
        assertEquals(null, arrayList.get(1));
    }

    @Test
    public void removeIndex() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        arrayList.add(1);
        arrayList.remove(0);
        assertEquals(0, arrayList.size());
    }

    @Test
    public void get() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        arrayList.add(1);
        assertEquals(new Integer(1), arrayList.get(0));
    }

    @Test
    public void set() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        arrayList.add(1);
        arrayList.set(0, 2);
        assertEquals(new Integer(2), arrayList.get(0));
    }

    @Test
    public void add1() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        arrayList.add(1);
        arrayList.add(0, 2);
        assertEquals(new Integer(2), arrayList.get(0));
    }

    @Test
    public void removeElement() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        arrayList.add(1);
        arrayList.remove(new Integer(1));
        assertEquals(true, arrayList.isEmpty());
    }

    @Test
    public void indexOf() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Integer.class);
        arrayList.add(1);
        assertEquals(0, arrayList.indexOf(1));
    }

    /**
     * 引用数组的特性
     * A[] a = new SubA[]{};
     * SubA[] 类型的父类为Object
     */
    @Test
    public void testArray() throws  Exception {
        Integer[] integers = new Integer[]{1, 2};
        System.out.println(integers.getClass().getSuperclass().getSimpleName());
        Object[] obj = integers;
        System.out.println(Arrays.toString(obj));
        Number[] numbers = integers;
        System.out.println(Arrays.toString(numbers));
    }
}