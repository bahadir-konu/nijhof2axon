package org.axonframework.sample.app.query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-04
 * Time: 11:30:52 AM
 */
@Repository
@Transactional(readOnly = true)
public class LedgerRepositoryImpl implements LedgerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked"})
    public List<LedgerEntry> findAllLedgers() {
        return entityManager.createQuery("SELECT e FROM LedgerEntry e")
                .setMaxResults(250)
                .getResultList();
    }
}
