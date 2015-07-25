package com.sardine.base.page;

import java.util.List;

/**
 * 分页实体类
 * Created by Wakim on 2015/7/9.
 */
public class Page<T> {
    private Pageable pageable = null;
    private List<T> elements = null;

    /**
     * 构造函数，基本内容必须提供
     *
     * @param pageable 页面适配器
     * @param elements 元素列
     */
    public Page(Pageable pageable, List<T> elements) {
        this.pageable = pageable;
        this.elements = elements;
    }

    /**
     * 获取当前页面偏移量（首页偏移量为0）
     *
     * @return 偏移量
     */
    public int getPageOffset() {
        return pageable.getPageOffset();
    }

    /**
     * 获取页面容量
     *
     * @return 页面容量
     */
    public int getPageSize() {
        return pageable.getPageSize();
    }

    /**
     * 获取页面总数
     *
     * @return 页面总数
     */
    public int getTotalPages() {
        return pageable.getPageNumber();
    }

    /**
     * 获取页面适配器
     *
     * @return 页面适配器
     */
    public Pageable getPageAdapter() {
        return pageable;
    }

    /**
     * 获取当页元素数量
     *
     * @return 元素数量
     */
    public int getSizeOfElements() {
        return elements.size();
    }

    /**
     * 获取实体元素总量
     *
     * @return 实体元素总量
     */
    public long getCountOfElements() {
        return pageable.getCountOfElements();
    }

    /**
     * 是否有下一页
     *
     * @return true - 有下一页，false - 无下一页
     */
    public boolean hasNextPage() {
        return pageable.hasNext();
    }

    /**
     * 是否为第一页
     *
     * @return true - 是，false - 否
     */
    public boolean isFirstPage() {
        return pageable.getPageOffset() == 0;
    }

    /**
     * 是否有上一页
     *
     * @return true - 有上一页，false - 无上一页
     */
    public boolean hasPreviousPage() {
        return pageable.hasPrevious();
    }

    /**
     * 是否为最后一页
     *
     * @return true - 是，false - 否
     */
    public boolean isLastPage() {
        return pageable.getPageOffset() + 1 == pageable.getPageNumber();
    }

    /**
     * 获取下一页的页面适配器
     *
     * @return 页面适配器
     */
    public Pageable nextPageAdapter() {
        return pageable.next();
    }

    /**
     * 获取上一页的页面适配器
     *
     * @return 页面适配器
     */
    public Pageable previousPageAdapter() {
        return pageable.previous();
    }

    /**
     * 是否具有元素
     *
     * @return true - 是，false - 否
     */
    public boolean hasContent() {
        return elements != null && elements.size() > 0;
    }

    /**
     * 获取元素集合
     *
     * @return 元素集合
     */
    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
