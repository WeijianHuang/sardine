package com.sardine.base.repository;

import com.sardine.base.entity.BaseEntity;
import com.sardine.base.page.Page;
import com.sardine.base.page.Pageable;
import com.sardine.base.page.adapter.SortAdapter;
import com.sardine.base.search.Searchable;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * 基础数据层实现类
 * <p/>
 * <p>Created by Wakim on 2015/7/18.
 *
 * @author Wakim
 * @version 0.1-SNAPSHOT
 */
@Repository
public class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable> implements BaseRepository<T, ID> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 保存或者更新
     *
     * @param entity 实体
     * @return 保存或者更新后的实体
     */
    @Override
    public T save(T entity) {
        getSession().save(entity);
        return entity;
    }

    /**
     * 批量保存或者更新实体
     *
     * @param es 实体集合
     * @return 保存或者更新后的实体
     */
    @Override
    public Iterable<T> save(Iterable<T> es) {
        for (T entity : es) {
            getSession().save(entity);
        }
        return es;
    }

    /**
     * 删除实体
     *
     * @param entity 删除
     */
    @Override
    public void remove(T entity) {
        getSession().delete(entity);
    }

    /**
     * 根据id删除实体
     *
     * @param id 实体id
     */
    @Override
    public void remove(ID id) {
        Query query = getSession().createQuery("delete from " + entityClass.getSimpleName() + "id=?");
        query.setParameter(0, id);
        query.executeUpdate();
    }

    /**
     * 根据id查找实体
     *
     * @param id 实体id
     * @return 返回实体
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(ID id) {
        return (T) getSession().get(entityClass, id);

    }

    /**
     * 获取所有实体
     *
     * @return 实体集合
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Query query = getSession().createQuery("from " + entityClass.getSimpleName());
        return query.list();

    }

    /**
     * 批量保存实体
     *
     * @param es 实体集合
     * @return 已经保存的实体集合
     */
    @Override
    public Iterable<T> saveInBatch(Iterable<T> es) {
        int i = 0;
        for (T entity : es) {
            save(entity);
            i++;
            if (i % 20 == 0) {
                clear();
            }
        }

        return es;
    }

    /**
     * 批量删除实体
     *
     * @param es 实体集合
     */
    @Override
    public void deleteInBatch(Iterator<T> es) {
        String id = "";
        while (es.hasNext()) {
            T entity = es.next();
            id += entity.getId();
            if (es.hasNext()) {
                id += ",";
            }
        }
        Query query = getSession().createQuery("delete from " + entityClass.getSimpleName() + "where id in(" + id + ")");
        query.executeUpdate();
    }

    /**
     * 删除所有实体
     */
    @Override
    public void deleteAll() {
        getSession().createQuery("delete from " + entityClass.getSimpleName());
    }

    /**
     * 批量删除
     *
     * @param ids id数组
     */
    @Override
    public void delete(ID[] ids) {
        int i = 0;
        for (ID id : ids) {
            remove(id);
            i++;
            if (i % 20 == 0) {
                clear();
            }
        }
    }

    /**
     * 删除实体
     *
     * @param entity 实体
     */
    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    /**
     * 根据id删除实体
     *
     * @param id 实体id
     */
    @Override
    public void delete(ID id) {
        Query query = getSession().createQuery("delete from " + entityClass.getSimpleName() + " where id = ?");
        query.setParameter(0, id);
        query.executeUpdate();
    }

    /**
     * 批量删除实体
     *
     * @param es 实体集合
     */
    @Override
    public void delete(Iterable<T> es) {
        for (T entity : es) {
            getSession().delete(entity);
        }
    }

    /**
     * 查找所有实体并排序
     *
     * @param sortAdapter 排序适配器
     * @return 有序实体集合
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll(SortAdapter sortAdapter) {
        Query query = getSession().createQuery("from " + entityClass.getSimpleName() + " " + sortAdapter.getPrepQL());
        return query.list();
    }

    /**
     * 查找所有实体类并进行分页
     *
     * @param pageable 分页适配器
     * @return 含实体的分页对象
     */
    @Override
    @SuppressWarnings("unchecked")
    public Page<T> findAll(Pageable pageable) {
        Query query = getSession().createQuery("from " + entityClass.getSimpleName() + " " + pageable.getPageSortAdapter().getPrepQL());
        pageable.setCountOfElements(count());
        if (pageable.exists() && pageable.getPageNumber() != 0) {
            throw new IndexOutOfBoundsException("页面越界");
        } else if (pageable.getCountOfElements() == 0) {
            return null;
        }
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageOffset());
        query.setMaxResults(pageable.getPageSize());
        return new Page<>(pageable, query.list());
    }

    /**
     * 统计实体数量
     *
     * @return 实体数量
     */
    @Override
    public int count() {
        Query query = getSession().createQuery("select count(*) from " + entityClass.getSimpleName());
        return (int) query.list().get(0);
    }

    /**
     * 按条件查找所有实体对象
     *
     * @param searchable 搜索适配器
     * @return 实体集合
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll(Searchable searchable) {
        if (searchable.getPrepQL() != null) {
            Query query = getSession().createQuery("from " + entityClass.getSimpleName() + " " + searchable.getPrepQL());
            return query.list();
        } else {
            Criteria criteria = searchable.getCriteria(getSession().createCriteria(entityClass));
            return criteria.list();
        }

    }

    /**
     * 按条件查找实体并且进行分页
     *
     * @param searchable 搜索适配器
     * @return 含实体的分页对象
     */
    @Override
    @SuppressWarnings("unchecked")
    public Page<T> getAll(Searchable searchable, Pageable pageable) {
        pageable.setCountOfElements(count(searchable));
        if (pageable.exists() && pageable.getPageNumber() != 0) {
            throw new IndexOutOfBoundsException("页面越界");
        } else if (pageable.getCountOfElements() == 0) {
            return null;
        }

        if (searchable.getPrepQL() != null) {
            Query query = getSession().createQuery("from " + entityClass.getSimpleName() + " " + searchable.getPrepQL() + " " + pageable.getPageSortAdapter().getPrepQL());
            query.setParameter(0, entityClass.getName());
            query.setFirstResult(pageable.getPageSize() * pageable.getPageOffset());
            query.setMaxResults(pageable.getPageSize());
            return new Page<>(pageable, query.list());
        } else {
            Criteria criteria = searchable.getCriteria(getSession().createCriteria(entityClass));
            criteria.setFirstResult(pageable.getPageSize() * pageable.getPageOffset());
            criteria.setMaxResults(pageable.getPageSize());
            pageable.getPageSortAdapter().getCriteria(criteria);
            return new Page<>(pageable, criteria.list());
        }
    }

    /**
     * 刷新数据连接
     */
    @Override
    public void flush() {
        getSession().flush();
    }

    /**
     * 清理数据代理
     */
    @Override
    public void clear() {
        flush();
        getSession().clear();
    }

    /**
     * 根据搜索条件统计数量
     *
     * @param searchable 搜索适配器
     * @return 统计数量
     */
    @Override
    public int count(Searchable searchable) {
        if (searchable.getPrepQL() != null) {
            Query query = getSession().createQuery("select count(*) from " + entityClass.getSimpleName() + " " + searchable.getPrepQL());
            query.setParameter(0, entityClass.getName());
            return (int) query.list().get(0);
        } else {
            Criteria criteria = searchable.getCriteria(getSession().createCriteria(entityClass));
            return (int) criteria.uniqueResult();
        }
    }

    /**
     * 检查实体是否存在
     *
     * @param id 实体id
     * @return 存在：true /不存在： false
     */
    @Override
    public boolean exists(ID id) {
        Query query = getSession().createQuery("select count(*) from " + entityClass.getSimpleName() + " where id = ?");
        query.setParameter(0, id);
        return ((int) query.list().get(0)) != 0;
    }

    /**
     * 数组转换QL包含条件
     *
     * @param ids 序列数组
     * @return " ( [item1],[item2],... ) "
     */
    public static String toArrayInSQL(Integer[] ids) {
        String temp = "";
        for (Integer e : ids) {
            temp += e.toString() + ",";
        }
        temp = temp.substring(0, temp.length() - 1);
        return "(" + temp + ") ";
    }

    /**
     * 执行原生SQL语句
     *
     * @param prepSQL SQL语句
     */
    public void executePrepSQL(final String prepSQL) {
        getSession().createSQLQuery(prepSQL).executeUpdate();
    }

    /**
     * 执行原生HQL语句
     *
     * @param prepHQL HQL语句
     */
    public void executePrepHQL(final String prepHQL) {
        getSession().createQuery(prepHQL).executeUpdate();
    }
}
