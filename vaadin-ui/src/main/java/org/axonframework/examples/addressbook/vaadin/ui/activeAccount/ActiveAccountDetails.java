package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Form;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Table;
import nijhof2axon.app.query.ActiveAccountEntry;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.ActiveAccountContainer;
import org.axonframework.examples.addressbook.vaadin.data.LedgerContainer;
import org.axonframework.examples.addressbook.vaadin.events.ActiveAccountDetailsRequestedEvent;
import org.axonframework.examples.addressbook.vaadin.events.CashDepositeCompletedEvent;
import org.axonframework.examples.addressbook.vaadin.events.CashDepositeRequestedEvent;
import org.axonframework.examples.addressbook.vaadin.events.CashWithdrawalRequestedEvent;

import java.util.Arrays;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-04
 * Time: 11:13:27 AM
 */
public class ActiveAccountDetails extends MediatorVerticalLayout implements MediatorListener {

    private ActiveAccountEntry activeAccountEntry;
    private LedgerContainer ledgerContainer;
    private ActiveAccountContainer activeAccountContainer;
    private Form activeAccountForm = new Form();

    public ActiveAccountDetails(ActiveAccountContainer activeAccountContainer, final CommandBus commandBus, final LedgerContainer ledgerContainer) {
        this.activeAccountContainer = activeAccountContainer;
        this.ledgerContainer = ledgerContainer;

        com.vaadin.ui.MenuBar menuBar = new com.vaadin.ui.MenuBar();

        MenuBar.MenuItem menuItemTransfer = menuBar.addItem("Transfer", null);

        menuItemTransfer.addItem("Make cash deposite", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                fire(new CashDepositeRequestedEvent(activeAccountEntry));
            }
        });

        menuItemTransfer.addItem("Withdraw cash", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                fire(new CashWithdrawalRequestedEvent(activeAccountEntry));
            }
        });

        addComponent(menuBar);

        activeAccountForm.setCaption("Active Account Details");
        activeAccountForm.setSizeFull();

        activeAccountForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "name"}));

        activeAccountForm.setReadOnly(true);

        addComponent(activeAccountForm);

        final Table ledgersTable = new Table("Ledgers");
//        ledgerContainer.refreshContent(activeAccountEntry.getIdentifier());
        ledgersTable.setContainerDataSource(ledgerContainer);

        addComponent(ledgersTable);
    }

    public void refreshFor(ActiveAccountEntry activeAccountEntry) {

        this.activeAccountEntry = activeAccountContainer.refresh(activeAccountEntry);

        BeanItem item = new BeanItem(this.activeAccountEntry);
        item.removeItemProperty("identifier");

        activeAccountForm.setItemDataSource(item);

        ledgerContainer.refreshContent(activeAccountEntry.getIdentifier());
    }

    @Override
    public void handleEvent(MediatorEvent event) {
        if (event instanceof ActiveAccountDetailsRequestedEvent) {
            refreshFor(((ActiveAccountDetailsRequestedEvent) event).getActiveAccountEntry());
        }

        if (event instanceof CashDepositeCompletedEvent) {
            refreshFor(((CashDepositeCompletedEvent) event).getActiveAccountEntry());
        }

    }


}
