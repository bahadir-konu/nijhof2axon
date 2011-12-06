package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import nijhof2axon.app.command.DepositCashCommand;
import nijhof2axon.app.query.ActiveAccountEntry;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.LedgerContainer;
import org.axonframework.examples.addressbook.vaadin.events.CashDepositeCompletedEvent;

import java.math.BigDecimal;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class CashDepositView extends MediatorVerticalLayout {

    private ActiveAccountEntry activeAccountEntry;

    public CashDepositView(final CommandBus commandBus, final LedgerContainer ledgerContainer) {

        final TextField depositAmount = new TextField("Specify the amount to be deposit");

        addComponent(depositAmount);

        Button deposit = new Button("Deposit");

        deposit.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                BigDecimal amount = BigDecimal.valueOf(new Double(depositAmount.getValue().toString()));

                DepositCashCommand depositCashCommand =
                        new DepositCashCommand(activeAccountEntry.getIdentifier(), amount);

                commandBus.dispatch(depositCashCommand);

                ledgerContainer.refreshContent(activeAccountEntry.getIdentifier());

                fire(new CashDepositeCompletedEvent(activeAccountEntry));
            }
        });

        addComponent(deposit);
    }

    public void refreshFor(ActiveAccountEntry activeAccountEntry) {
        this.activeAccountEntry = activeAccountEntry;
    }

}
