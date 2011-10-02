package org.axonframework.sample.app.query;

import java.util.List;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-01
 * Time: 3:32:59 PM
 */
public interface ClientRepository {
    List<ClientEntry> findAllClients();
}
