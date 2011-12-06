package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientView extends MediatorVerticalLayout implements MediatorListener {
    private ClientForm clientForm;
    private ClientList clientList;

    public ClientView(CommandBus commandBus, ClientContainer clientContainer) {

        VerticalLayout mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setSizeFull();

        VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
        verticalSplitPanel.setHeight("900px");
        verticalSplitPanel.setWidth("100%");
        verticalSplitPanel.setSplitPosition(30, Sizeable.UNITS_PERCENTAGE);

        clientForm = new ClientForm(commandBus, clientContainer);
        verticalSplitPanel.setFirstComponent(clientForm);
        clientList = new ClientList(clientContainer);
        verticalSplitPanel.setSecondComponent(clientList);

        mainVerticalLayout.addComponent(verticalSplitPanel);

        addComponent(mainVerticalLayout);

    }

    @Override
    public void handleEvent(MediatorEvent event) {
        clientForm.handleEvent(event);
        clientList.handleEvent(event);
    }
}
