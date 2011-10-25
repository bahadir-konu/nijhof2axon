package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.domain.StringAggregateIdentifier;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.sample.app.api.fohjin.command.OpenNewAccountForClientCommand;
import org.axonframework.sample.app.api.fohjin.event.ActiveAccountOpenedEvent;
import org.axonframework.sample.app.query.ClientEntry;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientDetails extends VerticalLayout {

    public ClientDetails(final ClientEntry clientEntry, final CommandBus commandBus) {

        addComponent(new Label("Client: " + clientEntry.getName()));

        final TextField activeAccountName = new TextField("Account Name");

        Button addActiveAccount = new Button("Add active account");

        addActiveAccount.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                AggregateIdentifier aggregateIdentifier = new StringAggregateIdentifier(clientEntry.getIdentifier());

                OpenNewAccountForClientCommand command = new OpenNewAccountForClientCommand(aggregateIdentifier,
                        activeAccountName.getValue().toString());

                commandBus.dispatch(command);
            }
        });

        addComponent(activeAccountName);
        addComponent(addActiveAccount);

        


    }

}
