package com.sardine.base.search;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;

/**
 * 搜索适配器
 * <p/>
 * <p>Created by Wakim on 2015/7/21.
 *
 * @author Wakim
 * @version 0.1-SNAPSHOT
 */
public interface Searchable {
    /**
     * 添加条件
     *
     * @param criterion Hibernate Criterion 适配条件
     * @return 自身对象
     */
    Searchable addCriterions(Criterion criterion);

    /**
     * 删除条件
     *
     * @param criterion Hibernate Criterion 需要删除的适配条件
     * @return 自身对象
     */
    Searchable removeCriterions(Criterion criterion);

    /**
     * 添加投影，聚合，或分组
     *
     * @param projection Hibernate Criteria 映射适配器
     * @return 自身对象
     */
    Searchable addProjection(Projection projection);

    /**
     * 添加关联
     *
     * @param alias 关联字段
     * @param as    映射到字段
     * @return 自身对象
     */
    Searchable addAlias(String alias, String as);

    /**
     * 删除关联
     *
     * @param alias 需要删除的关联字段
     * @return 自身对象
     */
    Searchable addAlias(String alias);

    /**
     * 添加动态关联抓取
     *
     * @param field     动态关联字段
     * @param fetchMode 抓取模式
     * @return 自身对象
     */
    Searchable addFetchMode(String field, FetchMode fetchMode);

    /**
     * 删除动态关联抓取
     *
     * @param field 删除关联字段
     * @return 自身对象
     */
    Searchable addFetchMode(String field);

    /**
     * 获得已编译的Criteria 对象
     *
     * @param criteria 从Session中获取的Criteria实例
     * @return 编译后的Criteria，如果无条件则返回原传入对象
     */
    Criteria getCriteria(Criteria criteria);

    /**
     * 获得用于统计记录总数的Criteria
     *
     * @param criteria 从Session中获取的Criteria实例
     * @return 编译后的Criteria对象
     */
    Criteria getCountCriteria(Criteria criteria);

    /**
     * 返回预编译的QL
     *
     * @return 包含前缀的预编译QL，无设定时为null
     */
    String getPrepQL();

    /**
     * 设定预编译QL
     *
     * @param prepQL 预编译QL，必须包含操作前缀
     */
    void setPrepQL(String prepQL);
}
