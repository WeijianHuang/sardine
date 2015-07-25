package com.sardine.base.page.adapter;

import org.hibernate.Criteria;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * 排序适配器
 * Created by Wakim on 2015/7/14.
 */
public class SortAdapter {
    public enum Order {
        /**
         * 由小到大排序
         */
        ASC("asc"),
        /**
         * 由大到小排序
         */
        DESC("DESC");
        private String content;

        private Order(String content) {
            this.content = content;
        }

        /**
         * 复写转换字符串
         *
         * @return prepQL关键字
         */
        public String toString() {
            return content;
        }
    }

    private LinkedHashMap<String, Order> orderMap = null;

    /**
     * 添加排序要求
     *
     * @param field 关键字段
     * @param order 序列顺序
     */
    public void addOrder(String field, Order order) {
        if (orderMap == null)
            orderMap = new LinkedHashMap<>();
        orderMap.put(field, order);
    }

    /**
     * 添加排序要求
     *
     * @param field 关键字段
     * @param order 序列顺序("ASC" == Order.ASC, "DESC" == Order.DESC)
     */
    public void addOrder(String field, String order) {
        if (orderMap == null)
            orderMap = new LinkedHashMap<>();
        if (order != null && !order.equals("")) {
            if (!order.trim().toUpperCase().equals("DESC"))
                orderMap.put(field, Order.ASC);
            else
                orderMap.put(field, Order.DESC);
        } else {
            orderMap.put(field, Order.ASC);
        }
    }

    /**
     * 取得SQL排序语句
     *
     * @return 返回排序语句字符串, 如果没有排序条件，则返回空字符串
     */
    public String getPrepQL() {
        if (orderMap == null)
            return "";

        String prepQL = "order by";
        String key;
        Iterator<String> index = orderMap.keySet().iterator();

        while (index.hasNext()) {
            key = index.next();
            prepQL += " " + key + " " + orderMap.get(key);
            if (index.hasNext()) {
                prepQL += ",";
            }
        }

        return prepQL;
    }

    /**
     * 取得添加排序后的Hibernate Criteria
     *
     * @param criteria 从Session获取的Criteria
     * @return 添加排序后的Criteria, 如果没有排序条件则返回原传入对象
     */
    public Criteria getCriteria(Criteria criteria) {
        if (orderMap == null)
            return criteria;

        Order value;

        for (String key : orderMap.keySet()) {
            value = orderMap.get(key);
            if (value == Order.ASC) {
                criteria.addOrder(org.hibernate.criterion.Order.asc(key));
            } else {
                criteria.addOrder(org.hibernate.criterion.Order.desc(key));
            }
        }

        return criteria;
    }
}
