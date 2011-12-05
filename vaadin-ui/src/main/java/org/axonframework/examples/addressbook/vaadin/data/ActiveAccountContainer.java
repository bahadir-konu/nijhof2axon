package org.axonframework.examples.addressbook.vaadin.data;

import com.vaadin.data.util.BeanItemContainer;
import nijhof2axon.app.query.ActiveAccountEntry;
import nijhof2axon.app.query.ActiveAccountRepository;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.events.ActiveAccountCreatedEvent;
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
public class ActiveAccountContainer extends BeanItemContainer<ActiveAccountEntry> implements Serializable, MediatorListener {

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

    public ActiveAccountEntry refresh(ActiveAccountEntry activeAccountEntry) {
        return activeAccountRepository.findById(activeAccountEntry.getIdentifier());
    }

    @Override
    public void handleEvent(MediatorEvent event) {
        if (event instanceof ActiveAccountCreatedEvent) {
            refreshContent(((ActiveAccountCreatedEvent) event).getClientIdentifier());
        }
    }
}

