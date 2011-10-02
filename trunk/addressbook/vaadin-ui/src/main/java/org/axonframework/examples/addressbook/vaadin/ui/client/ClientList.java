package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.ui.Table;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;
import org.axonframework.examples.addressbook.vaadin.data.ContactContainer;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-01
 * Time: 3:25:17 PM
 */
public class ClientList extends Table {
    public ClientList(ClientContainer fromRepository) {
        setContainerDataSource(fromRepository);

        setVisibleColumns(ContactContainer.NATURAL_COL_ORDER);
        setColumnHeaders(ContactContainer.COL_HEADERS_ENGLISH);
        setSelectable(true);
        setImmediate(true);
        setNullSelectionAllowed(false);
        setSizeFull();
    }
}

