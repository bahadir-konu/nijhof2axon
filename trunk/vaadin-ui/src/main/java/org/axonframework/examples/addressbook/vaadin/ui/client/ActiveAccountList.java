package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import nijhof2axon.app.query.ActiveAccountEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.ActiveAccountContainer;
import org.axonframework.examples.addressbook.vaadin.events.ActiveAccountDetailsRequestedEvent;
import org.axonframework.examples.addressbook.vaadin.events.ChangeClientNameCompletedEvent;
import org.axonframework.examples.addressbook.vaadin.events.ClientSelectedEvent;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ActiveAccountList extends MediatorVerticalLayout implements MediatorListener {

    private ActiveAccountContainer activeAccountContainer;

    public ActiveAccountList(ActiveAccountContainer activeAccountContainer) {

        this.activeAccountContainer = activeAccountContainer;

        VerticalLayout mainVerticalLayout = new VerticalLayout();

        mainVerticalLayout.addComponent(getActiveAccountsTable(activeAccountContainer));

        addComponent(mainVerticalLayout);

    }

    @Override
    public void handleEvent(MediatorEvent event) {

        if (event instanceof ClientSelectedEvent) {
            activeAccountContainer.refreshContent(((ClientSelectedEvent) event).getSelectedClient().getIdentifier());
        }

        if (event instanceof ChangeClientNameCompletedEvent) {
            activeAccountContainer.refreshContent(((ChangeClientNameCompletedEvent) event).getClientEntry().getIdentifier());
        }

    }

    private Table getActiveAccountsTable(ActiveAccountContainer activeAccountContainer) {
        final Table activeAccountsTable = new Table("Active Accounts");
        activeAccountsTable.setContainerDataSource(activeAccountContainer);

        activeAccountsTable.setVisibleColumns(new String[]{"accountName", "accountNumber", "balance"});

        activeAccountsTable.setColumnHeader("accountName", "Account Name");
        activeAccountsTable.setColumnHeader("accountNumber", "Account Number");
        activeAccountsTable.setColumnHeader("balance", "Balance");

        activeAccountsTable.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {

                BeanItem beanItem = (BeanItem) event.getItem();

                ActiveAccountEntry activeAccountEntry = (ActiveAccountEntry) beanItem.getBean();

                fire(new ActiveAccountDetailsRequestedEvent(activeAccountEntry));

            }
        });

        return activeAccountsTable;

    }


}