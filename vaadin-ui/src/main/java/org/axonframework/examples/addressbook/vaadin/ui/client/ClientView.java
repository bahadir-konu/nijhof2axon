package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import nijhof2axon.ui.MediatorListener;
import nijhof2axon.ui.MediatorVerticalLayout;
import nijhof2axon.ui.UIEvent;
import nijhof2axon.ui.data.ClientContainer;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientView extends MediatorVerticalLayout implements MediatorListener {
    private ClientForm clientForm;
    private ClientList clientList;

    public ClientView(ClientContainer clientContainer) {

        VerticalLayout mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setSizeFull();

        VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
        verticalSplitPanel.setHeight("900px");
        verticalSplitPanel.setWidth("100%");
        verticalSplitPanel.setSplitPosition(30, Sizeable.UNITS_PERCENTAGE);

        clientForm = new ClientForm(clientContainer);
        verticalSplitPanel.setFirstComponent(clientForm);
        clientList = new ClientList(clientContainer);
        verticalSplitPanel.setSecondComponent(clientList);

        mainVerticalLayout.addComponent(verticalSplitPanel);

        addComponent(mainVerticalLayout);

    }

    @Override
    public void handleEvent(UIEvent event) {
        clientForm.handleEvent(event);
        clientList.handleEvent(event);
    }
}
