package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import nijhof2axon.app.query.ClientDetailsEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;
import org.axonframework.examples.addressbook.vaadin.events.ClientSelectedEvent;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com) 
 */
public class ClientList extends MediatorVerticalLayout implements MediatorListener {
    public ClientList(ClientContainer clientContainer) {

        final Table clientsTable = new Table("Clients");
        clientsTable.setContainerDataSource(clientContainer);

        clientsTable.setVisibleColumns(new String[]{"clientName", "city", "street", "streetNumber", "postalCode", "phoneNumber"});

        clientsTable.setColumnHeader("clientName", "Name");
        clientsTable.setColumnHeader("city", "City");
        clientsTable.setColumnHeader("street", "Street");
        clientsTable.setColumnHeader("streetNumber", "Street Number");
        clientsTable.setColumnHeader("postalCode", "Postal Code");
        clientsTable.setColumnHeader("phoneNumber", "Phone Number");

        clientsTable.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {

                BeanItem beanItem = (BeanItem) event.getItem();

                ClientDetailsEntry clientEntry = (ClientDetailsEntry) beanItem.getBean();

                fire(new ClientSelectedEvent(clientEntry));

            }
        });

        clientContainer.refreshContent();

        addComponent(clientsTable);
    }


    @Override
    public void handleEvent(MediatorEvent event) {

    }
}
