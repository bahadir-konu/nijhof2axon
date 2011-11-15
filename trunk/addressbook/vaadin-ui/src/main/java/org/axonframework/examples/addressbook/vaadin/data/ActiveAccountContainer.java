package org.axonframework.examples.addressbook.vaadin.data;

import com.vaadin.data.util.BeanItemContainer;
import org.axonframework.sample.app.query.ActiveAccountEntry;
import org.axonframework.sample.app.query.ActiveAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-26
 * Time: 8:24:15 PM
 */
@Component
public class ActiveAccountContainer extends BeanItemContainer<ActiveAccountEntry> implements Serializable {

    @Autowired
    private ActiveAccountRepository activeAccountRepository;

    public ActiveAccountContainer() throws IllegalArgumentException {
        super(ActiveAccountEntry.class);
    }

    public void refreshContent(String clientIdentifier) {
        List<ActiveAccountEntry> activeAccountEntries = activeAccountRepository.findByClient(clientIdentifier);
        removeAllItems();
        addAll(activeAccountEntries);
    }


}


