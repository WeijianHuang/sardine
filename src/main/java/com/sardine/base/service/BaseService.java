package com.sardine.base.service;

import com.sardine.base.entity.BaseEntity;
import com.sardine.base.page.Page;
import com.sardine.base.page.Pageable;
import com.sardine.base.page.adapter.SortAdapter;
import com.sardine.base.search.Searchable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * 基础服务层接口
 * Created by Wakim on 2015/7/12.
 */

public interface BaseService<T extends BaseEntity, ID extends Serializable> {

    /**
     * 保存实体对象
     *
     * @param entity 实体对象
     * @return 保存后的实体对象
     */
    T save(T entity);

    /**
     * 批量保存实体
     *
     * @param es 实体集合
     * @return 保存后的实体集合
     */
    Iterable<T> save(Iterable<T> es);

    /**
     * 批量保存实体
     * <p>经过优化的保存方法</p>
     *
     * @param es 实体集合
     * @return 保存后的实体集合
     */
    Iterable<T> saveInBatch(Iterable<T> es);

    /**
     * 根据id查找实体对象
     *
     * @param id 实体id
     * @return 实体对象
     */
    T find(ID id);

    /**
     * 批量删除实体对象
     *
     * @param ids 实体对象id数组
     */
    void deleteInBatch(ID[] ids);

    /**
     * 检查实体是否存在
     *
     * @return 存在：true  / 不存在： false
     */
    boolean exists(ID id);

    /**
     * 删除实体
     *
     * @param entity 实体
     */
    void delete(T entity);

    /**
     * 根据id删除实体
     *
     * @param id 实体id
     */
    void delete(ID id);

    /**
     * 根据id数组删除实体
     *
     * @param ids id数组
     */
    void delete(ID[] ids);

    /**
     * 批量删除实体
     *
     * @param es 实体集合
     */
    void delete(Iterable<T> es);

    /**
     * 批量删除实体
     * <p>经过优化的删除方法</p>
     *
     * @param es 实体集合
     */
    void deleteInBatch(Iterator<T> es);

    /**
     * 删除所有实体
     */
    void deleteAll();

    /**
     * 刷新数据代理
     */
    void flush();

    /**
     * 清除数据代理
     */
    void clear();

    /**
     * 保存后刷新数据代理
     *
     * @param entity 实体对象
     * @return 保存后的实体
     */
    T saveAndFlush(T entity);

    /**
     * 查找所有实体
     *
     * @return 实体集合
     */
    List<T> findAll();

    /**
     * 查找所有实体并排序
     *
     * @param sortAdapter 排序适配器
     * @return 实体集合
     */
    List<T> findAll(SortAdapter sortAdapter);

    /**
     * 查找所有实体并分页
     *
     * @param pageable 分页适配器
     * @return 包含实体的分页对象
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 根据条件查找实体并分页
     *
     * @param searchable 搜索适配器
     * @param pageable   分页适配器
     * @return 包含实体对象的分页对象
     */
    Page<T> findAll(Searchable searchable, Pageable pageable);

    /**
     * 根据条件查找对象
     *
     * @param searchable 搜索适配器
     * @return 实体集合
     */
    List<T> findAll(Searchable searchable);

    /**
     * 统计实体数量
     *
     * @return 实体数量
     */
    int count();

    /**
     * 根据搜索条件统计实体数量
     *
     * @param searchable 搜索适配器
     * @return 统计数量
     */
    int count(Searchable searchable);
}
