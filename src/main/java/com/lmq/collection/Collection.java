package com.lmq.collection;

import java.util.Iterator;

/**
 * Created by liumingqi on 17/6/16.
 */
public interface Collection<E> extends Iterable<E>{
    //查询操作

    /**
     * 返回集合中元素个数
     * @return 集合元素个数
     */
    int size();

    /**
     * 返回集合是否为空
     * @return true若集合为空
     */
    boolean isEmpty();

    /**
     * 返回true若集合包含指定元素
     * @param o 指定元素
     * @return true若集合包含指定的元素
     * @throws ClassCastException 指定元素的类型不符合
     * @throws NullPointerException 指定元素为null，而该集合不允许null元素
     */
    boolean contains(Object o);

    /**
     * 返回集合迭代器
     * @return 集合迭代器
     */
    @Override
    Iterator<E> iterator();


    //修改操作

    /**
     * 确保该集合包含指定的元素
     * @param e 指定的元素
     * @return true若集合发生变化
     * @throws UnsupportedOperationException 若该集合不支持<tt>add</tt>操作
     * @throws NullPointerException 若指定的元素为null，而集合不允许null元素
     */
    boolean add(E e);

    /**
     * 从集合中删除指定元素的单一实例
     * @param o 指定元素
     * @return true若集合某一元素被删除
     * @throws ClassCastException 指定元素不符合
     * @throws  NullPointerException 指定元素为null，而集合不允许null元素
     * @throws  UnsupportedOperationException 若该集合不支持此操作
     */
    boolean remove(Object o);

}
