package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import org.axonframework.examples.addressbook.vaadin.Nijhof2AxonApplication;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;

import java.util.Arrays;

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

        clientsTable.addGeneratedColumn("Edit", new Table.ColumnGenerator() {
            public Component generateCell(Table source, Object itemId,
                                          Object columnId) {
                BeanItem item = (BeanItem) clientsTable.getItem(itemId);

                Button editButton = new Button("Edit");

                editButton.addListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {

                        //BKONU: How to get selected client entry???

                        //event.getComponent();

                        //((Nijhof2AxonApplication)getApplication()).switchToClientDetailsMode();
                       
                    }
                });

                return editButton;
            }
        });

        clientContainer.refreshContent();

        addComponent(clientsTable);
    }

}
