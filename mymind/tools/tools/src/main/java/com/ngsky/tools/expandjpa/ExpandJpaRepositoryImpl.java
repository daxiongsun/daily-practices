package com.ngsky.tools.expandjpa;

import com.ngsky.tools.exception.ForbiddenException;
import com.ngsky.tools.exception.ParamErrorException;
import com.ngsky.tools.parameter.Operator;
import com.ngsky.tools.parameter.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * <dl>
 * <dt>ExpandJpaRepository</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 10/28/2018 2:15 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Transactional
public class ExpandJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExpandJpaRepository<T, ID> {

    private final EntityManager entityManager;
    private final JpaEntityInformation jpaEntityInformation;

    public ExpandJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.jpaEntityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    public T findOne(String condition, Object... objects) throws ParamErrorException {
        if (StringUtils.isEmpty(condition)) {
            throw new ParamErrorException("condition cannot be null");
        }
        T result = null;
        Query query = createQuery(condition, null, objects);
        if (query != null) {
            result = (T) query.getSingleResult();
        }
        return result;
    }

    @Override
    public List<T> findAll(String condition, Object... objects) throws ParamErrorException {
        Sort sort = null;
        return findAll(condition, sort, objects);
    }

    @Override
    public List<T> findAll(String condition, Sort sort, Object... objects) throws ParamErrorException {
        if (StringUtils.isEmpty(condition)) {
            throw new ParamErrorException("condition cannot be null");
        }
        List<T> results = new ArrayList<T>();
        Query query = createQuery(condition, sort, objects);
        if (query != null) {
            results = (List<T>) query.getResultList();
        }
        return results;
    }

    @Override
    public Page<T> findAll(String condition, Pageable pageable, Object... objects) throws ParamErrorException, ForbiddenException {
        Sort sort = null;
        return findAll(condition, pageable, sort, objects);
    }

    @Override
    public Page<T> findAll(String condition, Pageable pageable, Sort sort, Object... objects) throws ParamErrorException, ForbiddenException {
        if (pageable == null) {
            return new PageImpl<T>((List<T>) findAll(condition, sort, objects));
        }
        Long total = count(condition, sort, objects);
        Query query = createQuery(condition, pageable.getSort(), objects);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T>emptyList();
        return new PageImpl<T>(content, pageable, total);
    }

    @Override
    public List<T> findAll(Iterable<Predicate> predicates, Operator operator) {
        return null;
    }

    @Override
    public List<T> findAll(Iterable<Predicate> predicates, Operator operator, Sort sort) {
        return null;
    }

    @Override
    public Page<T> findAll(Iterable<Predicate> predicates, Operator operator, Pageable pageable) {
        return null;
    }

    @Override
    public Page<T> findAll(Iterable<Predicate> predicates, Operator operator, Sort sort, Pageable pageable) {
        return null;
    }

    @Override
    public Long count(Iterable<Predicate> predicates, Operator operator) throws ParamErrorException {
        return null;
    }

    @Override
    public Long count(String condition, Object... objects) throws ForbiddenException {
        return new JPQLQueryHolder(condition, objects).createCountQuery().getSingleResult();
    }

    @Override
    public void deleteByIds(Iterable<ID> ids) {
        List<T> tList = super.findAllById(ids);
        super.deleteInBatch(tList);
    }

    @Override
    public Class<T> getEntity() {
        return jpaEntityInformation.getJavaType();
    }

    @Override
    public List<Map<String, Object>> nativeQueryMap(String sql) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return nativeQuery.getResultList();
    }

    @Override
    public Page<Map> nativeQueryMap(String sql, Pageable pageable) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        nativeQuery.setFirstResult((int) pageable.getOffset());
        nativeQuery.setMaxResults(pageable.getPageSize());
        Query countNativeQuery = entityManager.createNativeQuery("SELECT count(*) FROM ("+ sql +") aliasa");
        long total = Long.valueOf(String.valueOf(countNativeQuery.getSingleResult()));
        return new PageImpl<Map>(nativeQuery.getResultList(), pageable, total) ;
    }

    @Override
    public Object nativeQueryObject(String sql) throws ForbiddenException {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List results = nativeQuery.getResultList();
        if(results == null || results.size() == 0){
            return null;
        } else if(results.size() > 1){
            throw new ForbiddenException("查询结果数量大于1,禁止访问!");
        } else if (results.size() == 1){
            return results.get(0);
        }
        return null;
    }

    private Query createQuery(String condition, Sort sort, Object[] objects) {
        try {
            JPQLQueryHolder jpqlQueryHolder = new JPQLQueryHolder(condition, sort, objects);
            return jpqlQueryHolder.createQuery();
        } catch (ForbiddenException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查询Holder
    private class JPQLQueryHolder {
        //别名
        private final String ALIAS = "x";

        //QUERY ALL
        private final String FIND_ALL_QUERY_STRING = "FROM %s " + ALIAS;

        //传入的condition 排除列表
        private final String[] IGNORE_CONSTAINS_CHARSEQUENCE = {"where", "WHERE", "from", "FROM"};

        private String condition = null;
        private Sort sort;
        private Object[] objects;
        private Iterable<Predicate> predicates;
        private Operator operator = Operator.AND;

        private JPQLQueryHolder(Iterable<Predicate> predicates, Operator operator, Sort sort) {
            this.predicates = predicates;
            this.operator = operator;
            this.sort = sort;
        }

        private JPQLQueryHolder(Iterable<Predicate> predicates, Operator operator) {
            this.operator = operator;
            this.predicates = predicates;
        }

        private JPQLQueryHolder(String condition, Sort sort, Object[] objects) throws ForbiddenException {
            this(condition, objects);
            this.sort = sort;
        }

        private JPQLQueryHolder(String condition, Object[] objects) throws ForbiddenException {

            if (StringUtils.startsWithAny(condition, IGNORE_CONSTAINS_CHARSEQUENCE)) {
                throw new ForbiddenException("查询条件中只能包含WHERE条件表达式!");
            }
            this.condition = trimToNull(condition);
            this.objects = objects;
        }

        private Query createQuery() {
            StringBuilder sb = new StringBuilder();
            // select x from table
            sb.append(QueryUtils.getQueryString(FIND_ALL_QUERY_STRING, jpaEntityInformation.getEntityName()))
                    //where
                    .append(applyCondition());

            Query query = entityManager.createQuery(QueryUtils.applySorting(sb.toString(), sort, ALIAS));
            applyQueryParameter(query);
            return query;
        }

        private TypedQuery<Long> createCountQuery() {
            String ql = String.format(QueryUtils.COUNT_QUERY_STRING, ALIAS, "%s");
            ql = QueryUtils.getQueryString(ql, jpaEntityInformation.getEntityName());
            ql += applyCondition();

            TypedQuery<Long> query = entityManager.createQuery(ql, Long.class);
            applyQueryParameter(query);
            return query;
        }

        private List<String> map2Conditions() {
            if (predicates == null || !predicates.iterator().hasNext()) {
                return new ArrayList<String>();
            }
            List<String> conditions = new ArrayList<String>();

            Iterator<Predicate> iterator = predicates.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                Predicate predicate = iterator.next();
                if (predicate.getKey() == null) {
                    continue;
                }
                conditions.add(predicate.toCondition(String.valueOf(index)));
                index++;
            }
            return conditions;
        }

        private String applyCondition() {
            List<String> conditions = map2Conditions();
            if (condition != null) {
                conditions.add(condition);
            }
            condition = join(conditions, " " + operator.name() + " ");
            return isEmpty(condition) ? "" : " where " + condition;
        }

        private void applyQueryParameter(Query query) {
            if (objects != null) {
                int i = 0;
                for (Object value : objects) {
                    i++;
                    query.setParameter(i, value);
                }
            }
            if (predicates != null && predicates.iterator().hasNext()) {
                int index = 0;
                Iterator<Predicate> iterator = predicates.iterator();
                while (iterator.hasNext()) {
                    Predicate predicate = iterator.next();
                    predicate.setParameter(query, String.valueOf(index));
                    index++;
                }
            }
        }
    }
}
