package com.lmq.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by liumingqi on 17/6/20.
 */
public class LinkedListTest {

    @Test
    public void size() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        Assert.assertEquals(0, linkedList.size());

    }

    @Test
    public void isEmpty() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        Assert.assertEquals(true, linkedList.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        Assert.assertEquals(true, linkedList.contains(1));
    }

    @Test
    public void iterator() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
            iterator.remove();
        }
        Assert.assertEquals(0, linkedList.size());

    }

    @Test
    public void add() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        Assert.assertEquals(true, linkedList.contains(1));
        Assert.assertEquals(1, linkedList.size());
        Assert.assertEquals(false, linkedList.isEmpty());
    }

    @Test
    public void remove() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        boolean removeSuc = linkedList.remove(new Integer(1));
        Assert.assertEquals(true, removeSuc);
        Assert.assertEquals(0, linkedList.size());
        Assert.assertEquals(true, linkedList.isEmpty());
        linkedList.add(null);
        linkedList.remove(null);
    }

    @Test
    public void get() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        Integer element = linkedList.get(0);
        Assert.assertEquals(new Integer(1), element);
        Assert.assertEquals(1, linkedList.size());
    }

    @Test
    public void set() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.set(0, 1);
        Assert.assertEquals(new Integer(1), linkedList.get(0));
        linkedList.set(0, 2);
        Assert.assertEquals(new Integer(2), linkedList.get(0));
    }

    @Test
    public void addIndex() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0, 1);
        linkedList.add(0, 2);
        linkedList.add(1, 3);
        linkedList.add(3, 4);
        Assert.assertEquals(new Integer(2), linkedList.get(0));
        Assert.assertEquals(new Integer(3), linkedList.get(1));
        Assert.assertEquals(new Integer(1), linkedList.get(2));
        Assert.assertEquals(new Integer(4), linkedList.get(3));
    }

    @Test
    public void removeIndex() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        Integer removedElement = linkedList.remove(1);
        Assert.assertEquals(new Integer(2), removedElement);
        linkedList.remove(0);
        linkedList.remove(1);
        Assert.assertEquals(new Integer(3), linkedList.get(0));
    }

    @Test
    public void indexOf() throws Exception {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(null);
        linkedList.add(2);
        Assert.assertEquals(2, linkedList.indexOf(2));
    }

    @Test
    public void test() throws  Exception {
        java.util.LinkedList<Integer> linkedList = new java.util.LinkedList<>();
        linkedList.add(null);
        Assert.assertEquals(null, linkedList.get(0));

    }

}