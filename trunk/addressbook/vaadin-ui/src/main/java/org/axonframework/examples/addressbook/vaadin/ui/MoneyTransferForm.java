package org.axonframework.examples.addressbook.vaadin.ui;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.data.ContactFormBean;
import org.axonframework.sample.app.api.fohjin.ReceiveMoneyTransferCommand;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 11:55:05 AM
 */
public class MoneyTransferForm extends Form implements Button.ClickListener {
    private Button transferMoney = new Button("Transfer Money", (Button.ClickListener) this);

    private CommandBus commandBus;

    public MoneyTransferForm(CommandBus commandBus) {
        this.commandBus = commandBus;
        transferMoney.setIcon(new ThemeResource(Theme.save));

        createAndSetFooter();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Button source = event.getButton();
        if (source == transferMoney) {
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
        transferMoney.setVisible(!readOnly);

    }


    public void initialize() {
        setItemDataSource(new BeanItem<ContactFormBean>(new ContactFormBean()));

        setReadOnly(false);
    }

    private void handleSave() {

        ReceiveMoneyTransferCommand command =
                new ReceiveMoneyTransferCommand(BigDecimal.valueOf(10.), "12345");

        commandBus.dispatch(command);

        fireEvent(new FormIsSuccessfullyCommittedEvent(this));

        getApplication().getMainWindow().showNotification("Receive money transfer command dispatched!", Window.Notification.TYPE_TRAY_NOTIFICATION);
    }

    private ContactFormBean obtainContactFormBeanFromDatasource() {
        //noinspection unchecked
        return ((BeanItem<ContactFormBean>) getItemDataSource()).getBean();
    }


    private void createAndSetFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(transferMoney);
        footer.setVisible(false);
        setFooter(footer);
    }


    /*
        EVENTS
     */

    public class FormIsSuccessfullyCommittedEvent extends Component.Event {
        private String name;
        private String identifier;

        /**
         * Constructs a new event with the specified source component.
         *
         * @param source the source component of the event
         */
        public FormIsSuccessfullyCommittedEvent(Component source) {
            super(source);
            ContactFormBean contactFormBean = obtainContactFormBeanFromDatasource();
            name = contactFormBean.getName();
            identifier = contactFormBean.getIdentifier();
        }

        public String getIdentifier() {
            return identifier;
        }

        public String getName() {
            return name;
        }
    }

    public interface CommitListener extends Serializable {
        public void formIsCommitted(FormIsSuccessfullyCommittedEvent event);
    }

    public void addListener(CommitListener listener) {
        addListener(FormIsSuccessfullyCommittedEvent.class, listener, "formIsCommitted");
    }

    public void removeListener(CommitListener listener) {
        removeListener(FormIsSuccessfullyCommittedEvent.class, listener, "formIsCommitted");
    }
}

