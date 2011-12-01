package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import nijhof2axon.app.query.ActiveAccountEntry;
import nijhof2axon.app.query.ClientDetailsEntry;
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
    private ClientDetailsEntry clientDetailsEntry;
    private ActiveAccountContainer activeAccountContainer;
    private Label clientLabel;
    private Label streetLabel;

    public ClientDetails(final CommandBus commandBus, final ActiveAccountContainer activeAccountContainer) {

        this.activeAccountContainer = activeAccountContainer;

        addMenuItems();

        VerticalLayout mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setSpacing(true);

        HorizontalLayout space = new HorizontalLayout();
        space.setHeight("40%");
        mainVerticalLayout.addComponent(space);

        clientLabel = addLabel(mainVerticalLayout, "Name: ");
        streetLabel = addLabel(mainVerticalLayout, "Street: ");

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

    private Label addLabel(VerticalLayout verticalLayout, String caption) {

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        Label captionLabel = new Label(caption);
        Label valueLabel = new Label();
        layout.addComponent(captionLabel);
        layout.addComponent(valueLabel);

        verticalLayout.addComponent(layout);

        return valueLabel;
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
                fire(new ChangeClientNameRequestedEvent(clientDetailsEntry));
            }
        });

        menuItemClient.addItem("Open Active Account", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                fire(new AddActiveAccountRequestedEvent(clientDetailsEntry));
            }
        });

        addComponent(menuBar);
    }


    public void refreshFor(ClientDetailsEntry clientEntry) {
        this.clientDetailsEntry = clientEntry;

        BeanItem item = new BeanItem(clientEntry);
        item.removeItemProperty("identifier");

        clientLabel.setValue(clientEntry.getClientName());
        streetLabel.setValue(clientEntry.getStreet());

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
