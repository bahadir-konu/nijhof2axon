package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import nijhof2axon.app.command.ChangeClientNameCommand;
import nijhof2axon.app.query.ClientDetailsEntry;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.events.ChangeClientNameCompletedEvent;


/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-22
 * Time: 10:02:43 AM
 */
public class ChangeNameView extends MediatorVerticalLayout {
    private ClientDetailsEntry clientEntry;

    public ChangeNameView(final CommandBus commandBus) {

        final TextField textField = new TextField("New name:");
        addComponent(textField);

        Button button = new Button("Change");
        button.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String newName = textField.getValue().toString();

                ChangeClientNameCommand command = new ChangeClientNameCommand(clientEntry.getIdentifier(),
                        newName);

                commandBus.dispatch(command);

                clientEntry.setClientName(newName);
                fire(new ChangeClientNameCompletedEvent(clientEntry));
            }
        });

        addComponent(button);
    }

    public void refreshFor(ClientDetailsEntry clientEntry) {
        this.clientEntry = clientEntry;
    }

}
