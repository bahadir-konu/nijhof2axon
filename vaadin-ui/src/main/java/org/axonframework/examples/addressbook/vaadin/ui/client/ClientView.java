package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-24
 * Time: 8:43:19 PM
 */
public class ClientView extends MediatorVerticalLayout {

    public ClientView(CommandBus commandBus, ClientContainer clientContainer) {

        VerticalLayout mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setSizeFull();

        VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
        verticalSplitPanel.setHeight("900px");
        verticalSplitPanel.setWidth("100%");
        verticalSplitPanel.setSplitPosition(30, Sizeable.UNITS_PERCENTAGE);

        verticalSplitPanel.setFirstComponent(new ClientForm(commandBus, clientContainer));
        verticalSplitPanel.setSecondComponent(new ClientList(clientContainer));

        mainVerticalLayout.addComponent(verticalSplitPanel);

        addComponent(mainVerticalLayout);

    }

}
