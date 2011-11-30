package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import nijhof2axon.app.command.OpenNewAccountForClientCommand;
import nijhof2axon.app.query.ActiveAccountEntry;
import nijhof2axon.app.query.ClientEntry;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.MainWindow;
import org.axonframework.examples.addressbook.vaadin.data.ActiveAccountContainer;
import org.axonframework.examples.addressbook.vaadin.events.ActiveAccountCreatedEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-28
 * Time: 9:52:09 PM
 */
public class AddActiveAccountWindow extends Window {

    private ActiveAccountEntry activeAccountEntry;

    public AddActiveAccountWindow(final CommandBus commandBus, final ClientEntry clientEntry,
                                  final ActiveAccountContainer activeAccountContainer) {
        setCaption("Open Active Account");

        setModal(true);

        setWidth("50%");

        center();

        VerticalLayout mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setHeight("50%");
        mainVerticalLayout.setSpacing(true);

        final Form activeAccountForm = new Form();
        activeAccountForm.setWriteThrough(false);
        activeAccountForm.setInvalidCommitted(false);

        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setCaption("Open Active Account");
        formLayout.setSpacing(true);

        activeAccountForm.setLayout(formLayout);

        activeAccountEntry = new ActiveAccountEntry();
        BeanItem<ActiveAccountEntry> item = new BeanItem<ActiveAccountEntry>(activeAccountEntry);
        item.removeItemProperty("identifier");
        item.removeItemProperty("clientIdentifier");
        item.removeItemProperty("balance");

        activeAccountForm.setFormFieldFactory(new ActiveAccountEditModeFieldFactory());
        activeAccountForm.setItemDataSource(item);


        mainVerticalLayout.addComponent(activeAccountForm);

        VerticalLayout buttonsLayout = new VerticalLayout();

        Button openActiveAccountButton = new Button("Open");

        openActiveAccountButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                try {
                    activeAccountForm.commit();


                    OpenNewAccountForClientCommand command = new OpenNewAccountForClientCommand(clientEntry.getIdentifier(),
                            activeAccountEntry.getAccountName().toString());

                    commandBus.dispatch(command);

                    ((MainWindow) getApplication().getMainWindow()).fireEvent(
                            new ActiveAccountCreatedEvent(clientEntry.getIdentifier()));

                    close();
                } catch (Exception e) {
                    // Ignored, we'll let the Form handle the errors
                }


            }
        });

        buttonsLayout.addComponent(openActiveAccountButton);

        mainVerticalLayout.addComponent(buttonsLayout);

        addComponent(mainVerticalLayout);


    }


    
}
