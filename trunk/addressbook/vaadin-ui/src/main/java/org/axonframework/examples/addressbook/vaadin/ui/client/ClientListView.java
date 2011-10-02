package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.ui.VerticalSplitPanel;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientListView extends VerticalSplitPanel {

    public ClientListView(ClientList clientList, ClientForm clientForm) {
        setFirstComponent(clientList);
        setSecondComponent(clientForm);
        setSplitPosition(40);
    }
}

