package com.lmq.collection;

/**
 * Created by liumingqi on 17/6/16.
 */
public interface List<E> extends Collection<E>{
    //位置访问操作

    /**
     * 访问列表指定位置的元素
     * @param index 指定索引
     * @return 指定索引的元素
     * @throws IndexOutOfBoundsException 若索引不合法
     */
    E get(int index);

    /**
     * 用指定元素替换指定索引上的元素
     * @param index 指定索引
     * @param e 指定元素
     * @return 指定位置上替换前的元素
     * @throws UnsupportedOperationException 该列表不支持此操作
     * @throws NullPointerException 指定元素为null，而列表不支持null元素
     * @throws IndexOutOfBoundsException 指定索引越界
     */
    E set(int index, E e);

    /**
     * 在指定的索引插入指定的元素。往右移动索引位置上和其右边的元素
     * @param index 指定的索引
     * @param e 指定的元素
     * @throws NullPointerException 指定元素为null，而列表不支持null元素
     * @throws IndexOutOfBoundsException 指定索引越界
     */
    void add(int index, E e);

    /**
     * 删除指定索引处元素。往左移动其右边的元素。
     * @param index 指定的索引
     * @return 删除前该索引处的元素
     * @throws UnsupportedOperationException 该列表不支持此操作
     * @throws IndexOutOfBoundsException 索引越界
     */
    E remove(int index);


    //搜索操作
    /**
     * 返回指定元素在列表第一次出现的索引位置。
     * @param o 指定元素
     * @return 第一次出现的索引位置，－1若列表不包含该元素
     * @throws ClassCastException 指定元素类型不合要求
     * @throws NullPointerException 指定元素为null，而列表不支持null元素
     */
    int indexOf(Object o);

}
