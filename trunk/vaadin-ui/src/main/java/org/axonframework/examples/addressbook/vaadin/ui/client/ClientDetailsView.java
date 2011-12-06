package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.ActiveAccountContainer;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientDetailsView extends MediatorVerticalLayout implements MediatorListener {
    private ClientDetailsForm clientDetailsForm;
    private ActiveAccountList activeAccountList;

    public ClientDetailsView(ActiveAccountContainer activeAccountContainer) {

        VerticalLayout mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setWidth("50%");
        mainVerticalLayout.setSizeFull();

        VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
        verticalSplitPanel.setHeight("900px");
        verticalSplitPanel.setWidth("100%");
        verticalSplitPanel.setSplitPosition(30, Sizeable.UNITS_PERCENTAGE);

        clientDetailsForm = new ClientDetailsForm();
        activeAccountList = new ActiveAccountList(activeAccountContainer);

        verticalSplitPanel.setFirstComponent(clientDetailsForm);
        verticalSplitPanel.setSecondComponent(activeAccountList);

        mainVerticalLayout.addComponent(verticalSplitPanel);

        addComponent(mainVerticalLayout);


    }

    @Override
    public void handleEvent(MediatorEvent event) {
        clientDetailsForm.handleEvent(event);
        activeAccountList.handleEvent(event);
    }

}
