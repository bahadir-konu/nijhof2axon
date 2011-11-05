package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.Nijhof2AxonApplication;
import org.axonframework.sample.app.api.fohjin.command.DepositCashCommand;
import org.axonframework.sample.app.query.ActiveAccountEntry;

import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-05
 * Time: 9:34:51 AM
 */
public class CashDepositView extends VerticalLayout {

    public CashDepositView(final ActiveAccountEntry activeAccountEntry, final CommandBus commandBus) {

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

                ((Nijhof2AxonApplication) getApplication()).switchBackToAccountDetailsMode(activeAccountEntry);
            }
        });

        addComponent(deposit);

    }
}
