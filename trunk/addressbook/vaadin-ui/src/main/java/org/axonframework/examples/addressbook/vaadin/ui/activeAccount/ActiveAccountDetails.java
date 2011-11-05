package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Form;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.Nijhof2AxonApplication;
import org.axonframework.examples.addressbook.vaadin.data.LedgerContainer;
import org.axonframework.sample.app.query.ActiveAccountEntry;

import java.util.Arrays;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-04
 * Time: 11:13:27 AM
 */
public class ActiveAccountDetails extends VerticalLayout {

    public ActiveAccountDetails(final ActiveAccountEntry activeAccountEntry, final CommandBus commandBus, final LedgerContainer ledgerContainer) {

        com.vaadin.ui.MenuBar menuBar = new com.vaadin.ui.MenuBar();

        MenuBar.MenuItem menuItemFileTransformations = menuBar.addItem("Transfer", null);

        menuItemFileTransformations.addItem("Make cash deposite", new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                ((Nijhof2AxonApplication) getApplication()).switchToCashDepositeMode(activeAccountEntry);
            }
        });

        addComponent(menuBar);

        final Form activeAccountForm = new Form();
        activeAccountForm.setCaption("Active Account Details");
        activeAccountForm.setSizeFull();

        BeanItem item = new BeanItem(activeAccountEntry);
        item.removeItemProperty("identifier");

        activeAccountForm.setVisibleItemProperties(Arrays.asList(new String[]{
                "name"}));

        activeAccountForm.setReadOnly(true);

        activeAccountForm.setItemDataSource(item);

        addComponent(activeAccountForm);

        final Table ledgersTable = new Table("Ledgers");
        ledgersTable.setContainerDataSource(ledgerContainer);

        addComponent(ledgersTable);
    }

}
