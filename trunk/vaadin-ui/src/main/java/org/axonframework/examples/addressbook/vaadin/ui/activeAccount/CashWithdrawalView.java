package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import nijhof2axon.app.command.WithdrawCashCommand;
import nijhof2axon.app.query.ActiveAccountEntry;
import nijhof2axon.ui.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.LedgerContainer;
import org.axonframework.examples.addressbook.vaadin.events.CashWithdrawalCompletedEvent;

import java.math.BigDecimal;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class CashWithdrawalView extends MediatorVerticalLayout {

    private ActiveAccountEntry activeAccountEntry;

    public CashWithdrawalView(final LedgerContainer ledgerContainer) {

        final TextField withdrawAmount = new TextField("Specify the amount to withdraw");

        addComponent(withdrawAmount);

        Button deposit = new Button("Withdraw");

        deposit.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                BigDecimal amount = BigDecimal.valueOf(new Double(withdrawAmount.getValue().toString()));

                WithdrawCashCommand withdrawCashCommand =
                        new WithdrawCashCommand(activeAccountEntry.getIdentifier(), amount);

                commandBus().dispatch(withdrawCashCommand);

                ledgerContainer.refreshContent(activeAccountEntry.getIdentifier());

                fire(new CashWithdrawalCompletedEvent());                
            }
        });

        addComponent(deposit);

    }

    public void refreshFor(ActiveAccountEntry activeAccountEntry) {
        this.activeAccountEntry = activeAccountEntry;
    }
}

