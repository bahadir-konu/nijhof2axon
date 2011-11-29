package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import nijhof2axon.app.query.ActiveAccountEntry;
import nijhof2axon.app.query.ClientEntry;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.MediatorEvent;
import org.axonframework.examples.addressbook.vaadin.MediatorListener;
import org.axonframework.examples.addressbook.vaadin.MediatorVerticalLayout;
import org.axonframework.examples.addressbook.vaadin.data.ActiveAccountContainer;
import org.axonframework.examples.addressbook.vaadin.events.*;

import java.util.Arrays;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientDetails extends MediatorVerticalLayout implements MediatorListener {
    private ClientEntry clientEntry;
    private ActiveAccountContainer activeAccountContainer;

    private Form clientForm = new Form();

    public ClientDetails(final CommandBus commandBus, final ActiveAccountContainer activeAccountContainer) {

        this.activeAccountContainer = activeAccountContainer;

        addMenuItems();

        addClientForm();

        addActiveAccountsTable(activeAccountContainer);


        Button backButton = new Button("Back");
        backButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                fire(new ClientListViewRequestedEvent());
            }
        });

        addComponent(backButton);

    }

    private void addActiveAccountsTable(ActiveAccountContainer activeAccountContainer) {
        final Table activeAccountsTable = new Table("Active Accounts");
        //activeAccountContainer.refreshContent(clientEntry.getIdentifier());
        activeAccountsTable.setContainerDataSource(activeAccountContainer);

        activeAccountsTable.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {

                BeanItem beanItem = (BeanItem) event.getItem();

                ActiveAccountEntry activeAccountEntry = (ActiveAccountEntry) beanItem.getBean();

                fire(new ActiveAccountDetailsRequestedEvent(activeAccountEntry));

                //((Nijhof2AxonApplication) getApplication()).switchToActiveAccountDetailsMode(activeAccountEntry);

            }
        });

        addComponent(activeAccountsTable);

    }

    private void addClientForm() {
        clientForm.setCaption("Client Details");
        clientForm.setSizeFull();

        addComponent(clientForm);
    }

    private void addMenuItems() {
        MenuBar menuBar = new MenuBar();

        MenuBar.MenuItem menuItemClient = menuBar.addItem("Client", null);

        menuItemClient.addItem("Change name", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                fire(new ChangeClientNameRequestedEvent(clientEntry));
            }
        });

        menuItemClient.addItem("Add Active Account", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                fire(new AddActiveAccountRequestedEvent(clientEntry));
            }
        });

        addComponent(menuBar);
    }


    public void refreshFor(ClientEntry clientEntry) {
        this.clientEntry = clientEntry;

        BeanItem item = new BeanItem(clientEntry);
        item.removeItemProperty("identifier");

        clientForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "name"}));

        clientForm.setReadOnly(true);

        clientForm.setItemDataSource(item);

        activeAccountContainer.refreshContent(clientEntry.getIdentifier());


    }

    @Override
    public void handleEvent(MediatorEvent event) {
        if (event instanceof ClientSelectedEvent) {
            refreshFor(((ClientSelectedEvent) event).getSelectedClient());
        }

        if (event instanceof ChangeClientNameCompletedEvent) {
            refreshFor(((ChangeClientNameCompletedEvent) event).getClientEntry());
        }

        if (event instanceof ActiveAccountCreatedEvent) {
            getApplication().getMainWindow().showNotification("Account Opened Successfully", Window.Notification.TYPE_HUMANIZED_MESSAGE);
        }

    }

    @Override
    public void attach() {
        super.attach();
        getApplication().getMainWindow().addCollaborator(this);
    }

}
