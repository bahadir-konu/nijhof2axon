package org.axonframework.examples.addressbook.vaadin.ui.client;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.data.ClientFormBean;
import org.axonframework.examples.addressbook.vaadin.ui.Theme;
import org.axonframework.sample.app.api.fohjin.command.CreateClientCommand;

import java.io.Serializable;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class ClientForm extends Form implements Button.ClickListener {
    private Button save = new Button("Save", (Button.ClickListener) this);

    private CommandBus commandBus;

    public ClientForm(CommandBus commandBus) {
        this.commandBus = commandBus;
        save.setIcon(new ThemeResource(Theme.save));

        createAndSetFooter();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if (source == save) {
            handleSave();
        }
    }

    @Override
    public void setItemDataSource(Item newDataSource) {
        if (newDataSource != null) {
            super.setItemDataSource(newDataSource);
            setReadOnly(true);
            getFooter().setVisible(true);
        } else {
            super.setItemDataSource(null);
            getFooter().setVisible(false);
        }
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        save.setVisible(!readOnly);

    }

    /**
     * Setup the form to create a new Contact
     */
    public void addClient() {
        setItemDataSource(new BeanItem<ClientFormBean>(new ClientFormBean()));
        setReadOnly(false);
    }

    private void handleSave() {
        String message;
        if (!isValid()) {
            return;
        }


        ClientFormBean client = obtainContactFormBeanFromDatasource();

        CreateClientCommand createClientCommand = new CreateClientCommand(client.getName());

        message = "Created new client with name " + client.getName();

        commandBus.dispatch(createClientCommand);
        //fireEvent(new FormIsSuccessfullyCommittedEvent(this));
        setReadOnly(true);
        getApplication().getMainWindow().showNotification(message, Window.Notification.TYPE_TRAY_NOTIFICATION);
    }

    private ClientFormBean obtainContactFormBeanFromDatasource() {
        //noinspection unchecked
        return ((BeanItem<ClientFormBean>) getItemDataSource()).getBean();
    }


    private void createAndSetFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.setVisible(false);
        setFooter(footer);
    }


    /*
        EVENTS
     */

    public class FormIsSuccessfullyCommittedEvent extends Component.Event {
        private String name;

        /**
         * Constructs a new event with the specified source component.
         *
         * @param source the source component of the event
         */
        public FormIsSuccessfullyCommittedEvent(Component source) {
            super(source);
            ClientFormBean contactFormBean = obtainContactFormBeanFromDatasource();
            name = contactFormBean.getName();

        }

        public String getName() {
            return name;
        }
    }

    public interface CommitListener extends Serializable {
        public void clientFormIsCommitted(FormIsSuccessfullyCommittedEvent event);
    }

    public void addListener(CommitListener listener) {
        addListener(FormIsSuccessfullyCommittedEvent.class, listener, "clientFormIsCommitted");
    }

    public void removeListener(CommitListener listener) {
        removeListener(FormIsSuccessfullyCommittedEvent.class, listener, "formIsCommitted");
    }
}

