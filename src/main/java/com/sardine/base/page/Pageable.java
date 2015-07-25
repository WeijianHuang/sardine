package com.sardine.base.page;


import com.sardine.base.page.adapter.SortAdapter;

/**
 * Created by Wakim on 2015/7/14.
 */
public interface Pageable {
    /**
     * 获取排序适配器
     *
     * @return 排序适配器
     */
    SortAdapter getPageSortAdapter();

    /**
     * 获取实体元素总量
     *
     * @return 实体元素总量
     */
    long getCountOfElements();

    /**
     * 设定实体元素总量
     * <p>值传入时即自动计算页面总量
     *
     * @param countOfElements 实体元素总量
     */
    void setCountOfElements(long countOfElements);

    /**
     * 设定排序适配器
     *
     * @param pageSortAdapter 排序适配器
     */
    void setPageSortAdapter(SortAdapter pageSortAdapter);

    /**
     * 页面是否存在
     *
     * @return true - 存在，false - 不存在
     */
    boolean exists();

    /**
     * 获取页面数量
     *
     * @return
     */
    int getPageNumber();

    /**
     * 获取页面容量
     *
     * @return
     */
    int getPageSize();

    /**
     * 设定页面容量
     *
     * @param pageSize
     */
    void setPageSize(int pageSize);

    /**
     * 获取页面偏移量
     *
     * @return 页面偏移量（无偏移为0）
     */
    int getPageOffset();

    /**
     * 设定页面偏移量
     *
     * @param pageOffset 偏移量（获取第一页时，偏移量为0）
     */
    void setPageOffset(int pageOffset);

    /**
     * 获取下一页的页面适配器
     *
     * @return 页面适配器
     * @throws IndexOutOfBoundsException
     */
    Pageable next();

    /**
     * 获取上一页的页面适配器
     *
     * @return 页面适配器
     * @throws IndexOutOfBoundsException
     */
    Pageable previous();

    /**
     * 是否有下一页
     *
     * @return true - 有下一页， false - 无下一页
     */
    boolean hasNext();

    /**
     * 是否有上一页
     *
     * @return true - 有上一页， false - 无上一页
     */
    boolean hasPrevious();

    /**
     * 获取第一页的页面适配器
     *
     * @return 页面适配器
     */
    Pageable first();

    /**
     * 获取最后一页的页面适配器
     *
     * @return 页面适配器
     */
    Pageable last();
}
