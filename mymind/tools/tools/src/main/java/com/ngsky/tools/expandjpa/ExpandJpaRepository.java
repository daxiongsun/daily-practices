package com.ngsky.tools.expandjpa;

import com.ngsky.tools.exception.ForbiddenException;
import com.ngsky.tools.exception.ParamErrorException;
import com.ngsky.tools.parameter.Operator;
import com.ngsky.tools.parameter.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>ExpandJpaRepository</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 10/28/2018 2:15 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@NoRepositoryBean
public interface ExpandJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    /**
     * 查询单条记录
     *
     * @param condition: where 字段
     * @param objects:   where 值
     * @return 实体
     */
    T findOne(String condition, Object... objects) throws ParamErrorException;

    // 根据条件查询集合
    List<T> findAll(String condition, Object... objects) throws ParamErrorException;

    // 根据条件排序查询集合
    List<T> findAll(String condition, Sort sort, Object... objects) throws ParamErrorException;

    // 分页查询
    Page<T> findAll(String condition, Pageable pageable, Object... objects) throws ParamErrorException, ForbiddenException;

    // 排序分页查询
    Page<T> findAll(String condition, Pageable pageable, Sort sort, Object... objects) throws ParamErrorException, ForbiddenException;

    // 迭代条件查询集合
    List<T> findAll(Iterable<Predicate> predicates, Operator operator) throws ParamErrorException;

    List<T> findAll(Iterable<Predicate> predicates, Operator operator, Sort sort) throws ParamErrorException;

    Page<T> findAll(Iterable<Predicate> predicates, Operator operator, Pageable pageable) throws ParamErrorException;

    Page<T> findAll(Iterable<Predicate> predicates, Operator operator, Sort sort, Pageable pageable) throws ParamErrorException;

    Long count(Iterable<Predicate> predicates, Operator operator) throws ParamErrorException;
    Long count(String condition, Object... objects) throws ForbiddenException;

    // 根据 IDS 批量删除
    void deleteByIds(Iterable<ID> ids) throws ParamErrorException;

    // 获取当前实体Class
    Class<T> getEntity() throws ParamErrorException;

    // 原生查询
    List<Map<String, Object>> nativeQueryMap(String sql) throws ParamErrorException;

    Page<Map> nativeQueryMap(String sql, Pageable pageable) throws ParamErrorException;

    Object nativeQueryObject(String sql) throws ParamErrorException, ForbiddenException;
}