package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.ui.HorizontalSplitPanel;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-24
 * Time: 8:43:19 PM
 */
public class ClientView extends HorizontalSplitPanel {

    public ClientView(CommandBus commandBus, ClientContainer clientContainer) {
        setFirstComponent(new ClientList(clientContainer));
        setSecondComponent(new ClientForm(commandBus, clientContainer));
    }

}
