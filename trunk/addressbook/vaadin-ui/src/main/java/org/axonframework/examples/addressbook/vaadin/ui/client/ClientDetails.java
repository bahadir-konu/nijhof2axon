package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.Nijhof2AxonApplication;
import org.axonframework.examples.addressbook.vaadin.data.ActiveAccountContainer;
import org.axonframework.sample.app.api.fohjin.command.OpenNewAccountForClientCommand;
import org.axonframework.sample.app.query.ActiveAccountEntry;
import org.axonframework.sample.app.query.ClientEntry;

import java.util.Arrays;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientDetails extends VerticalLayout {
    private Form clientForm = new Form();

    public ClientDetails(final ClientEntry clientEntry, final CommandBus commandBus, final ActiveAccountContainer activeAccountContainer) {

        com.vaadin.ui.MenuBar menuBar = new com.vaadin.ui.MenuBar();

        MenuBar.MenuItem menuItemTransfer = menuBar.addItem("Client", null);

        menuItemTransfer.addItem("Change name", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                ((Nijhof2AxonApplication) getApplication()).switchToChangeNameMode(clientEntry);
            }
        });

        addComponent(menuBar);

        clientForm.setCaption("Client Details");
        clientForm.setSizeFull();

        addComponent(clientForm);

        final Table activeAccountsTable = new Table("Active Accounts");
        activeAccountContainer.refreshContent(clientEntry.getIdentifier());
        activeAccountsTable.setContainerDataSource(activeAccountContainer);

        activeAccountsTable.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {

                BeanItem beanItem = (BeanItem) event.getItem();

                ActiveAccountEntry activeAccountEntry = (ActiveAccountEntry) beanItem.getBean();

                ((Nijhof2AxonApplication) getApplication()).switchToActiveAccountDetailsMode(activeAccountEntry);

            }
        });


//        addComponent(new Label("Client: " + clientEntry.getName()));
//

        final TextField activeAccountName = new TextField("Account Name");

        Button addActiveAccount = new Button("Add active account");

        addActiveAccount.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                OpenNewAccountForClientCommand command = new OpenNewAccountForClientCommand(clientEntry.getIdentifier(),
                        activeAccountName.getValue().toString());

                commandBus.dispatch(command);

                activeAccountContainer.refreshContent(clientEntry.getIdentifier());
            }
        });
//
        addComponent(activeAccountName);
        addComponent(addActiveAccount);
        addComponent(activeAccountsTable);


    }


    public void refreshFor(ClientEntry clientEntry) {

        BeanItem item = new BeanItem(clientEntry);
        item.removeItemProperty("identifier");

        clientForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "name"}));

        clientForm.setReadOnly(true);

        clientForm.setItemDataSource(item);

    }

}
