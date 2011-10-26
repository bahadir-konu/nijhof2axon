package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import org.axonframework.examples.addressbook.vaadin.Nijhof2AxonApplication;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;
import org.axonframework.sample.app.query.ClientEntry;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-24
 * Time: 7:53:01 PM
 */
public class ClientList extends VerticalLayout {
    public ClientList(ClientContainer clientContainer) {

        final Table clientsTable = new Table("Clients");
        clientsTable.setContainerDataSource(clientContainer);

        clientsTable.setVisibleColumns(new String[]{"name"});

        clientsTable.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {

                BeanItem beanItem = (BeanItem) event.getItem();

                ClientEntry clientEntry = (ClientEntry) beanItem.getBean();

                ((Nijhof2AxonApplication) getApplication()).switchToClientDetailsMode(clientEntry);

            }
        });

        clientContainer.refreshContent();

        addComponent(clientsTable);
    }

}
