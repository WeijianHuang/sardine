package com.sardine.base.page.adapter;

import com.sardine.base.page.Pageable;

/**
 * 页面适配器
 * Created by Wakim on 2015/7/14.
 */
public class PageableAdapter implements Pageable {
    //页面数量
    private int pageNumber;
    //页面大小
    private int pageSize;
    //页面偏移量
    private int pageOffset;
    //实体总数
    private long countOfElements;
    //排序适配器
    private SortAdapter pageSortAdapter = new SortAdapter();

    @Override
    public SortAdapter getPageSortAdapter() {
        return pageSortAdapter;
    }

    /**
     * 获取实体总数
     *
     * @return 实体总数
     */
    @Override
    public long getCountOfElements() {
        return countOfElements;
    }

    /**
     * @param countOfElements 实体元素总量
     */
    @Override
    public void setCountOfElements(long countOfElements) {
        this.countOfElements = countOfElements;
        pageNumber = new Long(countOfElements / pageSize).intValue();
        if (new Long(countOfElements % pageSize).intValue() > 0)
            pageNumber++;
    }

    /**
     * 设置排序适配器
     *
     * @param pageSortAdapter 排序适配器
     */
    @Override
    public void setPageSortAdapter(SortAdapter pageSortAdapter) {
        this.pageSortAdapter = pageSortAdapter;
    }

    /**
     * 判断页面是否存在
     *
     * @return true or false
     */
    @Override
    public boolean exists() {
        return pageNumber - pageOffset < 1 && pageNumber > 0 && pageOffset >= 0 && pageSize > 0;
    }

    /**
     * 获取页面数量
     *
     * @return 页面数量
     */
    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * 获取页面大小
     *
     * @return 页面大小
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置页面大小
     *
     * @param pageSize 页面大小
     */
    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取页面偏移量
     *
     * @return 偏移量
     */
    @Override
    public int getPageOffset() {
        return this.pageOffset;
    }

    /**
     * 设定页面偏移量
     *
     * @param pageOffset 偏移量（获取第一页时，偏移量为0）
     */
    @Override
    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    /**
     * 获取下一页的适配器
     *
     * @return 下一页的适配器
     */
    @Override
    public Pageable next() {
        if (hasNext()) {
            pageOffset++;
        } else {
            throw new IndexOutOfBoundsException("下一页不存在");
        }
        return this;
    }

    /**
     * 获取上一页的适配器
     *
     * @return 上一页的适配器
     */
    @Override
    public Pageable previous() {
        if (hasPrevious()) {
            pageOffset--;
        } else {
            throw new IndexOutOfBoundsException("上一页不存在");
        }
        return this;
    }

    /**
     * 是否有下一页
     *
     * @return true or false
     */
    @Override
    public boolean hasNext() {
        return pageOffset + 1 < pageNumber;
    }

    /**
     * 是否是首页
     *
     * @return true or false
     */
    @Override
    public boolean hasPrevious() {
        return pageOffset - 1 > 0;
    }

    /**
     * 获取第一页的页面适配器
     *
     * @return 第一页的适配器
     */
    @Override
    public Pageable first() {
        pageOffset = 0;
        return this;
    }

    /**
     * 获取最后一页的适配器
     *
     * @return 最后一页的适配器
     */
    @Override
    public Pageable last() {
        pageOffset = pageNumber - 1;
        return this;
    }
}
