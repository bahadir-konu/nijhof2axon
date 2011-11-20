package org.axonframework.sample.app.query;

import java.util.List;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 4:27:00 PM
 */
public interface ActiveAccountRepository {

    List<ActiveAccountEntry> findAll();
    List<ActiveAccountEntry> findByClient(String clientIdentifier);
    void save(ActiveAccountEntry activeAccountEntry);
    ActiveAccountEntry findById(String id);

}
