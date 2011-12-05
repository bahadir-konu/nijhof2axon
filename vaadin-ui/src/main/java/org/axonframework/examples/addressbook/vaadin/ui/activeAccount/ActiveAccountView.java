package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.ActiveAccountContainer;
import org.axonframework.examples.addressbook.vaadin.data.LedgerContainer;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ActiveAccountView extends MediatorVerticalLayout implements MediatorListener {
    private ActiveAccountDetailsForm activeAccountDetailsForm;
    private LedgerList ledgerList;


    public ActiveAccountView(ActiveAccountContainer activeAccountContainer, LedgerContainer ledgerContainer, CommandBus commandBus) {
        VerticalLayout mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setSizeFull();

        VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
        verticalSplitPanel.setHeight("900px");
        verticalSplitPanel.setWidth("100%");
        verticalSplitPanel.setSplitPosition(20, Sizeable.UNITS_PERCENTAGE);

        activeAccountDetailsForm = new ActiveAccountDetailsForm(activeAccountContainer, commandBus);
        ledgerList = new LedgerList(ledgerContainer);

        verticalSplitPanel.setFirstComponent(activeAccountDetailsForm);
        verticalSplitPanel.setSecondComponent(ledgerList);

        mainVerticalLayout.addComponent(verticalSplitPanel);

        addComponent(mainVerticalLayout);
    }

    @Override
    public void handleEvent(MediatorEvent event) {
        activeAccountDetailsForm.handleEvent(event);
        ledgerList.handleEvent(event);
    }
}
