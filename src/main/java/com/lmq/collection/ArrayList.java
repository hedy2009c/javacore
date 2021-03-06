package com.lmq.collection;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * Created by liumingqi on 17/6/16.
 */
public class ArrayList<E> implements List<E> {
    private static  final  int INIT_CAPACITY = 10;

    public ArrayList(Class<E> classType) {
        elements = (E[]) Array.newInstance(classType,INIT_CAPACITY);
        this.classType = classType;
        size = 0;
    }

    public ArrayList(Class<E> classType, int initCapacity) {
        elements = (E[]) Array.newInstance(classType, initCapacity);
        this.classType = classType;
        size = 0;
    }

    private E[] elements;
    private Class<E> classType;
    private int size;

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

    private class Itr implements Iterator<E> {
        private int cursor = 0;
        private boolean canRemove = false;
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         */
        @Override
        public E next() {
            canRemove = true;
            return elements[cursor++];
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.  The behavior of an iterator
         * is unspecified if the underlying collection is modified while the
         * iteration is in progress in any way other than by calling this
         * method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw  new IllegalStateException();
            }
            ArrayList.this.remove(--cursor);
            canRemove = false;
        }
    }

    /**
     * 确保该集合包含指定的元素
     *
     * @param e 指定的元素
     * @return true若集合发生变化
     * @throws UnsupportedOperationException 若该集合不支持<tt>add</tt>操作
     */
    @Override
    public boolean add(E e) {
        if (size >= elements.length) {
            E[] temp = elements;
            elements = (E[]) Array.newInstance(classType,size + size >> 1);
            System.arraycopy(temp, 0, elements, 0, temp.length);
        }
        elements[size] = e;
        size++;
        return true;
    }

    /**
     * 从集合中删除指定元素的单一实例
     *
     * @param o 指定元素
     * @return true若集合某一元素被删除
     */
    @Override
    public boolean remove(Object o) {
        boolean removed = false;
        int indexFound = indexOf(o);
        if (indexFound != -1) {
            remove(indexFound);
            removed = true;
        }
        return removed;
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return elements[index];
    }

    /**
     * 用指定元素替换指定索引上的元素
     *
     * @param index 指定索引
     * @param e     指定元素
     * @return 指定位置上替换前的元素
     * @throws UnsupportedOperationException 该列表不支持此操作
     * @throws IndexOutOfBoundsException     指定索引越界
     */
    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E previous = elements[index];
        elements[index] = e;
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        E[] temp = elements;
        if (size >= elements.length) {
            elements = (E[]) Array.newInstance(classType, size+size>>1);
        }
        System.arraycopy(temp, 0, elements, 0, index);
        elements[index] = e;
        System.arraycopy(temp, index, elements, index + 1, size -index );
        size++;
    }

    /**
     * 删除指定索引处元素。往左移动其右边的元素。
     *
     * @param index 指定的索引
     * @return 删除前该索引处的元素
     * @throws UnsupportedOperationException 该列表不支持此操作
     * @throws IndexOutOfBoundsException     索引越界
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E previous = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        elements[size-1] = null;
        size--;
        return previous;
    }

    /**
     * 返回指定元素在列表第一次出现的索引位置。
     *
     * @param o 指定元素
     * @return 第一次出现的索引位置，－1若列表不包含该元素
     */
    @Override
    public int indexOf(Object o) {
        int indexFirstAppear = -1;
        if (o == null) {
            for(int i=0; i<size; i++) {
                if (elements[i] == null) {
                    indexFirstAppear = i;
                    break;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(o)) {
                    indexFirstAppear = i;
                    break;
                }
            }
        }
        return indexFirstAppear;
    }
}
