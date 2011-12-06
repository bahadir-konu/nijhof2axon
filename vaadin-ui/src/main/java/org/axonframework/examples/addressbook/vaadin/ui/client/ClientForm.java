package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import nijhof2axon.app.command.CreateClientCommand;
import nijhof2axon.app.domain.Address;
import nijhof2axon.app.query.ClientDetailsEntry;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;

import java.util.Arrays;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientForm extends VerticalLayout implements MediatorListener {

    public ClientForm(final CommandBus commandBus, final ClientContainer clientContainer) {
        final Form clientForm = new Form();
        clientForm.setCaption("Create Client");
        clientForm.setSizeFull();

        ClientDetailsEntry clientDetailsEntry = new ClientDetailsEntry();
        BeanItem item = new BeanItem(clientDetailsEntry);
        item.removeItemProperty("identifier");


        clientForm.setFormFieldFactory(new ClientFormFieldFactory());
        clientForm.setItemDataSource(item);
        clientForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "clientName", "city", "street", "streetNumber", "postalCode", "phoneNumber"}));

        addComponent(clientForm);

        Button saveButton = new Button("Create");
        saveButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                String clientName = clientForm.getItemDataSource().getItemProperty("clientName").toString();
                String city = clientForm.getItemDataSource().getItemProperty("city").toString();
                String street = clientForm.getItemDataSource().getItemProperty("street").toString();
                String streetNumber = clientForm.getItemDataSource().getItemProperty("streetNumber").toString();
                String postalCode = clientForm.getItemDataSource().getItemProperty("postalCode").toString();
                String phoneNumber = clientForm.getItemDataSource().getItemProperty("phoneNumber").toString();

                CreateClientCommand createClientCommand = new CreateClientCommand(clientName,
                        new Address(street, streetNumber, postalCode, city), phoneNumber
                );

                commandBus.dispatch(createClientCommand);

                clientContainer.refreshContent();

            }
        });

        addComponent(saveButton);

    }

    @Override
    public void handleEvent(MediatorEvent event) {
        //do nothing
    }

    private class ClientFormFieldFactory extends DefaultFieldFactory {
        @Override
        public Field createField(Item item, Object propertyId, Component uiContext) {
            Field field = super.createField(item, propertyId, uiContext);

            //BKONU if isAssignableFrom(TextField)  setNullBehaviour(..)
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                textField.setNullRepresentation("");
            }

            return field;
        }
    }
}
