package org.axonframework.sample.app.query;

import org.axonframework.sample.app.api.fohjin.ActiveAccount;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public ActiveAccount findById() {

        //TODO: ???

        return null;
    }
}
