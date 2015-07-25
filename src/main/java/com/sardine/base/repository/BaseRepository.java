package com.sardine.base.repository;

import com.sardine.base.entity.BaseEntity;
import com.sardine.base.page.Page;
import com.sardine.base.page.Pageable;
import com.sardine.base.page.adapter.SortAdapter;
import com.sardine.base.search.Searchable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * 基础数据层接口
 *
 * @param <T>  泛型，指实体类
 * @param <ID> 泛型，指实体类的主键
 *             Created by Wakim on 2015/7/8.
 */
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> {

    /**
     * 保存或者更新
     *
     * @param entity 实体
     * @return 保存或者更新后的实体
     */
    public T save(T entity);

    /**
     * 批量保存或者更新实体
     *
     * @param es 实体集合
     * @return 保存或者更新后的实体
     */
    public Iterable<T> save(Iterable<T> es);

    /**
     * 删除实体
     *
     * @param entity 删除
     */
    public void remove(T entity);

    /**
     * 根据id删除实体
     *
     * @param id 实体id
     */
    public void remove(ID id);

    /**
     * 根据id查找实体
     *
     * @param id 实体id
     * @return 返回实体
     */
    public T get(ID id);

    /**
     * 获取所有实体
     *
     * @return 实体集合
     */
    public List<T> getAll();

    /**
     * 批量保存实体
     *
     * @param es 实体集合
     * @return 保存后的实体集合
     */
    public Iterable<T> saveInBatch(Iterable<T> es);

    /**
     * 批量删除实体
     *
     * @param es 实体集合
     */
    public void deleteInBatch(Iterator<T> es);

    /**
     * 删除所有实体
     */
    public void deleteAll();

    /**
     * 批量删除
     *
     * @param ids id数组
     */
    public void delete(ID[] ids);

    /**
     * 删除实体
     *
     * @param entity 实体
     */
    public void delete(T entity);

    /**
     * 根据id删除实体
     *
     * @param id 实体id
     */
    public void delete(ID id);

    /**
     * 批量删除实体
     *
     * @param es 实体集合
     */
    public void delete(Iterable<T> es);

    /**
     * 查找所有实体并排序
     *
     * @param sortAdapter 排序适配器
     * @return 有序实体集合
     */
    public List<T> getAll(SortAdapter sortAdapter);

    /**
     * 查找所有实体类并进行分页
     *
     * @param pageable 分页适配器
     * @return 含实体的分页对象
     */
    public Page<T> findAll(Pageable pageable);

    /**
     * 统计实体数量
     *
     * @return 实体数量
     */
    public int count();

    /**
     * 按条件查找所有实体对象
     *
     * @param searchable 搜索适配器
     * @return 实体集合
     */
    List<T> findAll(Searchable searchable);

    /**
     * 按条件查找实体并且进行分页
     *
     * @param searchable 搜索适配器
     * @return 含实体的分页对象
     */
    Page<T> getAll(Searchable searchable, Pageable pageable);

    /**
     * 刷新数据连接
     */

    public void flush();

    /**
     * 清理数据代理
     */

    public void clear();

    /**
     * 根据搜索条件统计数量
     *
     * @param searchable 搜索适配器
     * @return 统计数量
     */
    public int count(Searchable searchable);

    /**
     * @param id 实体id
     *           检查实体是否存在
     * @return 存在：true /不存在： false
     */
    public boolean exists(ID id);

}
