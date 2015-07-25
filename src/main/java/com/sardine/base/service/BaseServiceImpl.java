package com.sardine.base.service;

import com.sardine.base.entity.BaseEntity;
import com.sardine.base.page.Page;
import com.sardine.base.page.Pageable;
import com.sardine.base.page.adapter.SortAdapter;
import com.sardine.base.repository.BaseRepository;
import com.sardine.base.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * 基础服务层实现类
 * Created by Wakim on 2015/7/12.
 */
@Service
public class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    /**
     * 保存实体对象
     *
     * @param entity 实体对象
     * @return 保存后的实体对象
     */
    @Override
    public T save(T entity) {
        baseRepository.save(entity);
        return entity;
    }

    /**
     * 批量保存实体
     *
     * @param es 实体集合
     * @return 保存后的实体集合
     */
    @Override
    public Iterable<T> save(Iterable<T> es) {
        baseRepository.save(es);
        return es;
    }

    /**
     * 批量保存实体
     * <p>经过优化的保存方法</p>
     *
     * @param es 实体集合
     * @return 保存后的实体集合
     */
    @Override
    public Iterable<T> saveInBatch(Iterable<T> es) {
        baseRepository.saveInBatch(es);
        return es;
    }

    /**
     * 根据id查找实体对象
     *
     * @param id 实体id
     * @return 实体对象
     */
    @Override
    public T find(ID id) {
        return baseRepository.get(id);
    }

    /**
     * 批量删除实体对象
     *
     * @param ids 实体对象id数组
     */
    @Override
    public void deleteInBatch(ID[] ids) {
        baseRepository.delete(ids);
    }

    /**
     * 检查实体是否存在
     *
     * @return 存在：true  / 不存在： false
     */
    @Override
    public boolean exists(ID id) {
        return baseRepository.exists(id);
    }

    /**
     * 删除实体
     *
     * @param entity 实体
     */
    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    /**
     * 根据id删除实体
     *
     * @param id 实体id
     */
    @Override
    public void delete(ID id) {
        baseRepository.delete(id);
    }

    /**
     * 根据id数组删除实体
     *
     * @param ids id数组
     */
    @Override
    public void delete(ID[] ids) {
        baseRepository.delete(ids);
    }

    /**
     * 批量删除实体
     *
     * @param es 实体集合
     */
    @Override
    public void delete(Iterable<T> es) {
        baseRepository.delete(es);
    }

    /**
     * 批量删除实体
     * <p>经过优化的删除方法</p>
     *
     * @param es 实体集合
     */
    @Override
    public void deleteInBatch(Iterator<T> es) {
        baseRepository.deleteInBatch(es);
    }

    /**
     * 删除所有实体
     */
    @Override
    public void deleteAll() {
        baseRepository.deleteAll();
    }

    /**
     * 刷新数据代理
     */
    @Override
    public void flush() {
        baseRepository.flush();
    }

    /**
     * 清除数据代理
     */
    @Override
    public void clear() {
        baseRepository.clear();
    }

    /**
     * 保存后刷新数据代理
     *
     * @param entity 实体对象
     * @return 保存后的实体
     */
    @Override
    public T saveAndFlush(T entity) {
        baseRepository.save(entity);
        baseRepository.flush();
        return entity;
    }

    /**
     * 查找所有实体
     *
     * @return 实体集合
     */
    @Override
    public List<T> findAll() {
        return baseRepository.getAll();
    }

    /**
     * 查找所有实体并排序
     *
     * @param sortAdapter 排序适配器
     * @return 实体集合
     */
    @Override
    public List<T> findAll(SortAdapter sortAdapter) {
        return baseRepository.getAll(sortAdapter);
    }

    /**
     * 查找所有实体并分页
     *
     * @param pageable 分页适配器
     * @return 包含实体的分页对象
     */
    @Override
    public Page<T> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

    /**
     * 根据条件查找实体并分页
     *
     * @param searchable 搜索适配器
     * @param pageable   分页适配器
     * @return 包含实体对象的分页对象
     */
    @Override
    public Page<T> findAll(Searchable searchable, Pageable pageable) {
        return baseRepository.getAll(searchable, pageable);
    }

    /**
     * 根据条件查找对象
     *
     * @param searchable 搜索适配器
     * @return 实体集合
     */
    @Override
    public List<T> findAll(Searchable searchable) {
        return baseRepository.findAll(searchable);
    }

    /**
     * 统计实体数量
     *
     * @return 实体数量
     */
    @Override
    public int count() {
        return baseRepository.count();
    }

    /**
     * 根据搜索条件统计实体数量
     *
     * @param searchable 搜索适配器
     * @return 统计数量
     */
    @Override
    public int count(Searchable searchable) {
        return baseRepository.count(searchable);
    }
}
