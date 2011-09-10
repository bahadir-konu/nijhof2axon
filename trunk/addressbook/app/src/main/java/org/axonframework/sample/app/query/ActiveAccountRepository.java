package org.axonframework.sample.app.query;

import org.axonframework.sample.app.api.fohjin.ActiveAccount;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 4:27:00 PM
 */
public interface ActiveAccountRepository {

    ActiveAccount findById();


}
