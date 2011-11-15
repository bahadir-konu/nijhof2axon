package org.axonframework.sample.app.query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 4:32:56 PM
 */
@Repository
@Transactional(readOnly = true)
public class ActiveAccountRepositoryImpl implements ActiveAccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ActiveAccountEntry> findAll() {

        return entityManager.createQuery("SELECT e FROM ActiveAccountEntry e")
                .setMaxResults(250)
                .getResultList();

    }

    @Override
    public List<ActiveAccountEntry> findByClient(String clientIdentifier) {

        return entityManager.createQuery("SELECT e FROM ActiveAccountEntry e WHERE e.clientIdentifier = :clientIdentifier")
                .setParameter("clientIdentifier", clientIdentifier)
                .setMaxResults(250)
                .getResultList();

    }
}
