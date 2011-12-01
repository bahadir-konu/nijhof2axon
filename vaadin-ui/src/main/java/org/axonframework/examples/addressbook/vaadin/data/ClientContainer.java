package org.axonframework.examples.addressbook.vaadin.data;

import com.vaadin.data.util.BeanItemContainer;
import nijhof2axon.app.query.ClientDetailsEntry;
import nijhof2axon.app.query.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-01
 * Time: 3:28:40 PM
 */
@Component
public class ClientContainer extends BeanItemContainer<ClientDetailsEntry> implements Serializable {

    @Autowired
    private ClientRepository clientRepository;

    public ClientContainer() throws IllegalArgumentException {
        super(ClientDetailsEntry.class);
    }

    public void refreshContent() {
        List<ClientDetailsEntry> allClients = clientRepository.findAllClients();
        removeAllItems();
        addAll(allClients);
    }


}

