package org.axonframework.sample.app.query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-01
 * Time: 3:43:54 PM
 */
@Repository
@Transactional(readOnly = true)
public class ClientRepositoryImpl implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked"})
    public List<ClientEntry> findAllClients() {
        return entityManager.createQuery("SELECT e FROM ClientEntry e")
                .setMaxResults(250)
                .getResultList();
    }

    @Override
    public ClientEntry findByIdentifier(String identifier) {
        return (ClientEntry) entityManager.createQuery("SELECT e FROM ClientEntry e WHERE e.identifier=:identifier")
                .setParameter("identifier", identifier)
                .getSingleResult();
    }

    @Override
    public void persist(ClientEntry entry) {
        entityManager.persist(entry);
    }


}

