package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.sample.app.api.fohjin.command.ChangeClientNameCommand;
import org.axonframework.sample.app.query.ClientEntry;


/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-22
 * Time: 10:02:43 AM
 */
public class ChangeNameView extends VerticalLayout {

    public ChangeNameView(final ClientEntry clientEntry, final CommandBus commandBus) {

        final TextField textField = new TextField("New name:");
        addComponent(textField);

        Button button = new Button("Change");
        button.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                ChangeClientNameCommand command = new ChangeClientNameCommand(clientEntry.getIdentifier(),
                        textField.getValue().toString());

                commandBus.dispatch(command);
            }
        });

        addComponent(button);
    }

}
