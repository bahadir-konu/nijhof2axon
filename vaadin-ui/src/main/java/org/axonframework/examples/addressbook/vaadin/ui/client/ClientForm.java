package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;
import nijhof2axon.app.command.CreateClientCommand;
import nijhof2axon.app.query.ClientEntry;

import java.util.Arrays;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-24
 * Time: 7:47:36 PM
 */
public class ClientForm extends VerticalLayout {

    public ClientForm(final CommandBus commandBus, final ClientContainer clientContainer) {
        final Form clientForm = new Form();
        clientForm.setCaption("Create Client");
        clientForm.setSizeFull();

        ClientEntry clientEntry = new ClientEntry();
        BeanItem item = new BeanItem(clientEntry);
        item.removeItemProperty("identifier");

        clientForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "name"}));

        clientForm.setFormFieldFactory(new ClientFormFieldFactory());
        clientForm.setItemDataSource(item);

        addComponent(clientForm);

        Button saveButton = new Button("Create");
        saveButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                String clientName = clientForm.getItemDataSource().getItemProperty("name").toString();
                CreateClientCommand createClientCommand = new CreateClientCommand(clientName
                );

                commandBus.dispatch(createClientCommand);

                clientContainer.refreshContent();

            }
        });

        addComponent(saveButton);

    }

    private class ClientFormFieldFactory extends DefaultFieldFactory {
        @Override
        public Field createField(Item item, Object propertyId, Component uiContext) {
            Field field = super.createField(item, propertyId, uiContext);

            if (propertyId.equals("name")) {
                TextField textField = (TextField) field;
                textField.setNullRepresentation("");
            }

            return field;
        }
    }
}
