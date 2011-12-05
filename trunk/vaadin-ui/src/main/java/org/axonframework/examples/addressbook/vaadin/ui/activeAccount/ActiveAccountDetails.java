package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
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

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-04
 * Time: 11:13:27 AM
 */
//BKONU: split and make similar to ClientView
public class ActiveAccountDetails extends MediatorVerticalLayout implements MediatorListener {

    private ActiveAccountEntry activeAccountEntry;
    private LedgerContainer ledgerContainer;
    private ActiveAccountContainer activeAccountContainer;

    //BKONU: use these similar to ClientDetails
    private Label accountNameLabel;
    private Label accountNumberLabel;
    private Label balanceLabel;

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


        VerticalLayout mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setSpacing(true);

        HorizontalLayout space = new HorizontalLayout();
        space.setHeight("40%");
        mainVerticalLayout.addComponent(space);

        accountNameLabel = addLabel(mainVerticalLayout, "Account Name: ");
        accountNumberLabel = addLabel(mainVerticalLayout, "Account Number: ");
        balanceLabel = addLabel(mainVerticalLayout, "Balance: ");


        addComponent(mainVerticalLayout);


        final Table ledgersTable = new Table("Ledgers");
        ledgersTable.setContainerDataSource(ledgerContainer);

        addComponent(ledgersTable);
    }

    //BKONU: duplicated at ActiveAccountDetails

    private Label addLabel(VerticalLayout verticalLayout, String caption) {

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        Label captionLabel = new Label(caption);
        captionLabel.setWidth("80px");
        captionLabel.addStyleName(Runo.LAYOUT_DARKER);
        Label valueLabel = new Label();
        layout.addComponent(captionLabel);
        layout.addComponent(valueLabel);

        verticalLayout.addComponent(layout);

        return valueLabel;
    }


    public void refreshFor(ActiveAccountEntry activeAccountEntry) {

        this.activeAccountEntry = activeAccountContainer.refresh(activeAccountEntry);

        accountNameLabel.setValue(this.activeAccountEntry.getAccountName());
        accountNumberLabel.setValue(this.activeAccountEntry.getAccountNumber());
        balanceLabel.setValue(this.activeAccountEntry.getBalance());

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
