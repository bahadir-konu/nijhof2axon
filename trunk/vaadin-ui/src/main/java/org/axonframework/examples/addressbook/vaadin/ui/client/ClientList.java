package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import nijhof2axon.app.query.ClientEntry;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;
import org.axonframework.examples.addressbook.vaadin.events.ClientSelectedEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-24
 * Time: 7:53:01 PM
 */
public class ClientList extends MediatorVerticalLayout implements MediatorListener {
    public ClientList(ClientContainer clientContainer) {

        final Table clientsTable = new Table("Clients");
        clientsTable.setContainerDataSource(clientContainer);

        clientsTable.setVisibleColumns(new String[]{"name"});

        clientsTable.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {

                BeanItem beanItem = (BeanItem) event.getItem();

                ClientEntry clientEntry = (ClientEntry) beanItem.getBean();

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
