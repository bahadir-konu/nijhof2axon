package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.Nijhof2AxonApplication;
import org.axonframework.examples.addressbook.vaadin.data.LedgerContainer;
import org.axonframework.sample.app.api.fohjin.command.WithdrawCashCommand;
import org.axonframework.sample.app.query.ActiveAccountEntry;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-19
 * Time: 11:52:45 AM
 */
public class CashWithdrawView extends VerticalLayout {

    public CashWithdrawView(final ActiveAccountEntry activeAccountEntry, final CommandBus commandBus, final LedgerContainer ledgerContainer) {

        final TextField withdrawAmount = new TextField("Specify the amount to withdraw");

        addComponent(withdrawAmount);

        Button deposit = new Button("Withdraw");

        deposit.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                BigDecimal amount = BigDecimal.valueOf(new Double(withdrawAmount.getValue().toString()));

                WithdrawCashCommand withdrawCashCommand =
                        new WithdrawCashCommand(activeAccountEntry.getIdentifier(), amount);

                commandBus.dispatch(withdrawCashCommand);

                ledgerContainer.refreshContent(activeAccountEntry.getIdentifier());

                ((Nijhof2AxonApplication) getApplication()).switchBackToAccountDetailsMode(activeAccountEntry);
            }
        });

        addComponent(deposit);

    }
}

