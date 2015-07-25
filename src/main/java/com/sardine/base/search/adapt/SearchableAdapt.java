package com.sardine.base.search.adapt;

import com.sardine.base.search.Searchable;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p/>
 * <p>Created by Wakim on 2015/7/21.
 *
 * @author Wakim
 * @version 0.1-SNAPSHOT
 */
public class SearchableAdapt implements Searchable {

    private List<Criterion> criterions = null;
    private ProjectionList projectionList = null;
    private Map<String, String> aliaes = null;
    private Map<String, FetchMode> fetchModeMap = null;
    private String prepQL = null;


    /**
     * 添加条件
     *
     * @param criterion Hibernate Criterion 适配条件
     * @return 自身对象
     */
    @Override
    public Searchable addCriterions(Criterion criterion) {
        if (criterions == null) {
            criterions = new ArrayList<>();
        }
        criterions.add(criterion);
        return this;
    }

    /**
     * 删除条件
     *
     * @param criterion Hibernate Criterion 需要删除的适配条件
     * @return 自身对象
     */
    @Override
    public Searchable removeCriterions(Criterion criterion) {
        if (criterions == null) {
            criterions = new ArrayList<>();
        }
        criterions.remove(criterion);
        return this;
    }

    /**
     * 添加投影，聚合，或分组
     *
     * @param projection Hibernate Criteria 映射适配器
     * @return 自身对象
     */
    @Override
    public Searchable addProjection(Projection projection) {
        if (projectionList == null) {
            projectionList = Projections.projectionList();
        }
        projectionList.add(projection);
        return this;
    }

    /**
     * 添加关联
     *
     * @param alias 关联字段
     * @param as    映射到字段
     * @return 自身对象
     */
    @Override
    public Searchable addAlias(String alias, String as) {
        if (aliaes == null) {
            aliaes = new HashMap<>();
        }
        aliaes.put(alias, as);
        return this;
    }

    /**
     * 删除关联
     *
     * @param alias 需要删除的关联字段
     * @return 自身对象
     */
    @Override
    public Searchable addAlias(String alias) {
        if (aliaes == null) {
            aliaes = new HashMap<>();
        }
        aliaes.remove(alias);
        return this;
    }

    /**
     * 添加动态关联抓取
     *
     * @param field     动态关联字段
     * @param fetchMode 抓取模式
     * @return 自身对象
     */
    @Override
    public Searchable addFetchMode(String field, FetchMode fetchMode) {
        if (fetchModeMap == null) {
            fetchModeMap = new HashMap<>();
        }
        fetchModeMap.put(field, fetchMode);
        return this;
    }

    /**
     * 删除动态关联抓取
     *
     * @param field 删除关联字段
     * @return 自身对象
     */
    @Override
    public Searchable addFetchMode(String field) {
        if (fetchModeMap == null) {
            fetchModeMap = new HashMap<>();

        }
        fetchModeMap.remove(field);
        return this;
    }

    /**
     * 获得已编译的Criteria 对象
     *
     * @param criteria 从Session中获取的Criteria实例
     * @return 编译后的Criteria，如果无条件则返回原传入对象
     */
    @Override
    public Criteria getCriteria(Criteria criteria) {
        if (criterions != null) {
            for (Criterion criterion : criterions) {
                criteria.add(criterion);
            }
        }

        if (projectionList != null) {
            criteria.setProjection(projectionList);
        }

        if (aliaes != null) {
            for (String key : aliaes.keySet()) {
                criteria.createAlias(key, aliaes.get(key));
            }
        }

        if (fetchModeMap != null) {
            for (String key : fetchModeMap.keySet()) {
                criteria.setFetchMode(key, fetchModeMap.get(key));
            }
        }

        return criteria;

    }

    /**
     * 获得用于统计记录总数的Criteria
     *
     * @param criteria 从Session中获取的Criteria实例
     * @return 编译后的Criteria对象
     */
    @Override
    public Criteria getCountCriteria(Criteria criteria) {
        if (criterions != null) {
            for (Criterion criterion : criterions) {
                criteria.add(criterion);
            }
        }
        criteria.setProjection(Projections.rowCount());
        return criteria;
    }

    /**
     * 返回预编译的QL
     *
     * @return 包含前缀的预编译QL，无设定时为null
     */
    @Override
    public String getPrepQL() {
        return prepQL;
    }

    /**
     * 设定预编译QL
     *
     * @param prepQL 预编译QL，必须包含操作前缀
     */
    @Override
    public void setPrepQL(String prepQL) {
        this.prepQL = prepQL;
    }
}
