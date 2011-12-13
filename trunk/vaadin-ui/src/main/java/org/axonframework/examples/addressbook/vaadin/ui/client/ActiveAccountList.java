package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import nijhof2axon.app.query.ActiveAccountEntry;
import nijhof2axon.ui.MediatorListener;
import nijhof2axon.ui.MediatorVerticalLayout;
import nijhof2axon.ui.UIEvent;
import nijhof2axon.ui.data.ActiveAccountContainer;
import nijhof2axon.ui.events.ActiveAccountDetailsRequestedEvent;
import nijhof2axon.ui.events.ChangeClientNameCompletedEvent;
import nijhof2axon.ui.events.ClientSelectedEvent;

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
    public void handleEvent(UIEvent event) {

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
