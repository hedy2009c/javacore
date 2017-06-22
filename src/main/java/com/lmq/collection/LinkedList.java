package com.lmq.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by liumingqi on 17/6/19.
 */
public class LinkedList<E> implements List<E> {
    private int size = 0;
    private LinkNode<E> head;
    private LinkNode<E> tail;

    private class LinkNode<U> {
        U element;
        LinkNode<U> next;
        LinkNode<U> prev;

        public LinkNode(LinkNode<U> prev, U element, LinkNode<U> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * 返回集合中元素个数
     *
     * @return 集合元素个数
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 返回集合是否为空
     *
     * @return true若集合为空
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回true若集合包含指定元素
     *
     * @param o 指定元素
     * @return true若集合包含指定的元素
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * 返回集合迭代器
     *
     * @return 集合迭代器
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements  Iterator<E> {
        LinkNode<E> nextNode = (LinkNode<E>) head;
        LinkNode<E> lastRet;
        boolean canRemove = false;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }

            E element = nextNode.element;
            lastRet = nextNode;
            nextNode = nextNode.next;
            canRemove = true;
            return element;
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.  The behavior of an iterator
         * is unspecified if the underlying collection is modified while the
         * iteration is in progress in any way other than by calling this
         * method.
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException();
            }

            unLink(lastRet);
            lastRet = null;
            size--;
            canRemove = false;
        }
    }

    private void unLink(LinkNode<E> x) {
        LinkNode<E> prevNode = x.prev;
        LinkNode<E> nextNode = x.next;
        if (prevNode == null) {// x is headNode
            head = nextNode;
        }else {
            prevNode.next = nextNode;
            x.prev = null;
        }

        if (nextNode == null) {// x is tailNode
            tail = prevNode;
        }else {
            nextNode.prev = prevNode;
            x.next = null;
        }
    }

    /**
     * 确保该集合包含指定的元素
     *
     * @param e 指定的元素
     * @return true若集合发生变化
     */
    @Override
    public boolean add(E e) {
        LinkNode<E> newNode = new LinkNode<>(null,e, null);
        if (head == null) {
            head = tail = newNode;
        }else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    /**
     * 从集合中删除指定元素的单一实例
     *
     * @param o 指定元素
     * @return true若集合某一元素被删除
     * @throws ClassCastException            指定元素不符合
     * @throws NullPointerException          指定元素为null，而集合不允许null元素
     * @throws UnsupportedOperationException 若该集合不支持此操作
     */
    @Override
    public boolean remove(Object o) {
        boolean find = false;
        if (!isEmpty()) {
            LinkNode<E> node = head;
            if (o == null) {
                do {
                    if (node.element == null) {
                        find = true;
                        break;
                    }else {
                        node = node.next;
                    }
                }while (node != null);
            }else {
                while (node != null) {
                    if (node.element.equals(o)) {
                        find = true;
                        break;
                    }else {
                        node = node.next;
                    }
                }
            }

            if (find) {
                unLink(node);
                node = null;
                size--;
            }
        }
        return find;
    }

    /**
     * 访问列表指定位置的元素
     *
     * @param index 指定索引
     * @return 指定索引的元素
     * @throws IndexOutOfBoundsException 若索引不合法
     */
    @Override
    public E get(int index) {
        rangeCheck(index);
        LinkNode<E> node = geteLinkNode(index);
        return node.element;
    }

    private LinkNode<E> geteLinkNode(int index) {
        LinkNode<E> node = head;
        for(int i=1; i<=index; i++) {
            node = node.next;
        }
        return node;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 用指定元素替换指定索引上的元素
     *
     * @param index 指定索引
     * @param e     指定元素
     * @return 指定位置上替换前的元素
     * @throws IndexOutOfBoundsException     指定索引越界
     */
    @Override
    public E set(int index, E e) {
        rangeCheck(index);
        LinkNode<E> node = geteLinkNode(index);
        E previous = node.element;
        node.element = e;
        return previous;
    }

    /**
     * 在指定的索引插入指定的元素。往右移动索引位置上和其右边的元素
     *
     * @param index 指定的索引
     * @param e     指定的元素
     * @throws IndexOutOfBoundsException 指定索引越界
     */
    @Override
    public void add(int index, E e) {
        rangeCheckAdd(index);
        LinkNode<E> node = geteLinkNode(index);

        LinkNode<E> newNode = new LinkNode<>(null, e, null);
        if (index == 0) {//在链表头新增节点
            if (isEmpty()) {
                head = tail = newNode;
            }else {
                newNode.next = node;
                node.prev = newNode;
                head = newNode;
            }
        }else if (index == size) {//在链表尾部新增节点
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }else {//在链表中间新增节点
            LinkNode<E> prev = node.prev;
            prev.next = newNode;
            newNode.prev = prev;

            newNode.next = node;
            node.prev = newNode;
        }
        size++;
    }

    private void rangeCheckAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 删除指定索引处元素。往左移动其右边的元素。
     *
     * @param index 指定的索引
     * @return 删除前该索引处的元素
     * @throws IndexOutOfBoundsException     索引越界
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);
        LinkNode<E> node = geteLinkNode(index);
        unLink(node);
        E previousElement = node.element;
        node = null;
        size--;
        return previousElement;
    }

    /**
     * 返回指定元素在列表第一次出现的索引位置。
     *
     * @param o 指定元素
     * @return 第一次出现的索引位置，－1若列表不包含该元素
     */
    @Override
    public int indexOf(Object o) {
        if (isEmpty()) {
            return -1;
        }
        LinkNode<E> node = head;
        int indexFound = -1;
        if (o == null) {
            for(int i=0; i<size; i++) {
                if (node.element == null) {
                    indexFound = i;
                    break;
                }else {
                    node = node.next;
                }
            }
        }else {
            for(int i=0; i<size; i++) {
                if (node.element.equals(o)) {
                    indexFound = i;
                    break;
                }else {
                    node = node.next;
                }
            }
        }
        return indexFound;
    }
}
