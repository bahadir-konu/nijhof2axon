package org.axonframework.sample.app.query;

import org.axonframework.sample.app.api.fohjin.ActiveAccount;

import java.util.List;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 4:27:00 PM
 */
public interface ActiveAccountRepository {

    List<ActiveAccountEntry> findAll();


}
