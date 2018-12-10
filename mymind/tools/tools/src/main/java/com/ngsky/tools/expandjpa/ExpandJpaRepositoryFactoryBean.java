package com.ngsky.tools.expandjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * <dl>
 * <dt>ExpandJpaRepositoryFactoryBean</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 10/30/2018 10:58 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class ExpandJpaRepositoryFactoryBean <R extends JpaRepository<T, ID>, T, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, ID> {

    public ExpandJpaRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    protected RepositoryFactorySupport createRepositoryFactory(
            EntityManager entityManager) {
        return new ExpandJpaRepositoryFactory<T, ID>(entityManager);
    }

    private static class ExpandJpaRepositoryFactory<T, ID extends Serializable>
            extends JpaRepositoryFactory {

        private final EntityManager entityManager;

        public ExpandJpaRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

//        protected Object getTargetRepository(RepositoryMetadata metadata) {
//            JpaEntityInformation<T, Serializable> jpaEntityInformation = (JpaEntityInformation<T, Serializable>) this.getEntityInformation(metadata.getDomainType());
//            return new ExpandJpaRepositoryImpl<T, ID>(jpaEntityInformation, entityManager);
//        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return ExpandJpaRepositoryImpl.class;
        }
    }
}