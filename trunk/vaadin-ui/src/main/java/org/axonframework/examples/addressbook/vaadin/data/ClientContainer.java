package org.axonframework.examples.addressbook.vaadin.data;

import com.vaadin.data.util.BeanItemContainer;
import nijhof2axon.app.query.ClientEntry;
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
public class ClientContainer extends BeanItemContainer<ClientEntry> implements Serializable {
    public static final Object[] NATURAL_COL_ORDER = new Object[]{"name", "identifier"};
    public static final String[] COL_HEADERS_ENGLISH = new String[]{"Name", "Identifier"};

    @Autowired
    private ClientRepository clientRepository;

    public ClientContainer() throws IllegalArgumentException {
        super(ClientEntry.class);
    }

    public void refreshContent() {
        List<ClientEntry> allClients = clientRepository.findAllClients();
        removeAllItems();
        addAll(allClients);
    }


}

