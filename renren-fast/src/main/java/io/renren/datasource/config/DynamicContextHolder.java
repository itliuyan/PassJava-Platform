/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.datasource.config;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 多数据源上下文
 *
 * @author Mark sunlightcs@gmail.com
 */
public class DynamicContextHolder {

    /**
     * 这里设置数据源我不太清楚为什么需要使用一个队列的结构来存储数据源配置，难道一个项目的数据源配置不是只有一个吗？那不是用一个字符串就可以存储了嘛
     * 看了最后那个移除的方法，或者是说可以通过设置一个多优先级的数据源配置，然后在不同阶段使用不同的数据源配置，有点类似责任链。并且，如果队列没有数据源
     * 配置了还需要清空ThreadLocal，避免出现内存泄漏的问题
     */
    private static final ThreadLocal<Deque<String>> CONTEXT_HOLDER = ThreadLocal.withInitial(ArrayDeque::new);

    /**
     * 获得当前线程数据源
     *
     * @return 数据源名称
     */
    public static String peek() {
        return CONTEXT_HOLDER.get().peek();
    }

    /**
     * 设置当前线程数据源
     *
     * @param dataSource 数据源名称
     */
    public static void push(String dataSource) {
        CONTEXT_HOLDER.get().push(dataSource);
    }

    /**
     * 清空当前线程数据源
     */
    public static void poll() {
        Deque<String> deque = CONTEXT_HOLDER.get();
        deque.poll();
        if (deque.isEmpty()) {
            CONTEXT_HOLDER.remove();
        }
    }

}