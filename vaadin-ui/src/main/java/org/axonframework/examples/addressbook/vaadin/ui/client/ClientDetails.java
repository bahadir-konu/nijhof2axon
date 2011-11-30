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

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientDetails extends MediatorVerticalLayout implements MediatorListener {
    private ClientEntry clientEntry;
    private ActiveAccountContainer activeAccountContainer;
    private Label clientLabel;

    public ClientDetails(final CommandBus commandBus, final ActiveAccountContainer activeAccountContainer) {

        this.activeAccountContainer = activeAccountContainer;

        addMenuItems();

        VerticalLayout mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setSpacing(true);

        HorizontalLayout space = new HorizontalLayout();
        space.setHeight("40%");
        mainVerticalLayout.addComponent(space);

        HorizontalLayout clientNameLayout = new HorizontalLayout();
        clientNameLayout.setSpacing(true);

        Label labelCaption = new Label("Name: ");
        clientLabel = new Label();
        clientNameLayout.addComponent(labelCaption);
        clientNameLayout.addComponent(clientLabel);

        mainVerticalLayout.addComponent(clientNameLayout);

        mainVerticalLayout.addComponent(getActiveAccountsTable(activeAccountContainer));

        Button backButton = new Button("Back");
        backButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                fire(new ClientListViewRequestedEvent());
            }
        });

        mainVerticalLayout.addComponent(backButton);

        addComponent(mainVerticalLayout);

    }

    private Table getActiveAccountsTable(ActiveAccountContainer activeAccountContainer) {
        final Table activeAccountsTable = new Table("Active Accounts");
        activeAccountsTable.setContainerDataSource(activeAccountContainer);

        activeAccountsTable.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {

                BeanItem beanItem = (BeanItem) event.getItem();

                ActiveAccountEntry activeAccountEntry = (ActiveAccountEntry) beanItem.getBean();

                fire(new ActiveAccountDetailsRequestedEvent(activeAccountEntry));

            }
        });

        return activeAccountsTable;

    }


    private void addMenuItems() {
        MenuBar menuBar = new MenuBar();

        MenuBar.MenuItem menuItemClient = menuBar.addItem("Client", null);

        menuItemClient.addItem("Change name", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                fire(new ChangeClientNameRequestedEvent(clientEntry));
            }
        });

        menuItemClient.addItem("Open Active Account", new MenuBar.Command() {
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

        clientLabel.setValue(clientEntry.getName());

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
