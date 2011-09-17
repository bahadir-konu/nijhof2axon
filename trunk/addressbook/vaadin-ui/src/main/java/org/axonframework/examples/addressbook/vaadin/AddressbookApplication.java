/*
 * Copyright (c) 2010. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.examples.addressbook.vaadin;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Runo;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.domain.StringAggregateIdentifier;
import org.axonframework.examples.addressbook.vaadin.data.ContactContainer;
import org.axonframework.examples.addressbook.vaadin.data.ContactFormBean;
import org.axonframework.examples.addressbook.vaadin.ui.*;
import org.axonframework.sample.app.api.fohjin.command.OpenNewAccountForClientCommand;
import org.axonframework.sample.app.query.ContactEntry;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jettro Coenradie
 */
public class AddressbookApplication extends Application
        implements Button.ClickListener, Property.ValueChangeListener, ItemClickEvent.ItemClickListener, ContactForm.CommitListener {

    private Button newContact = new Button("Add contact");
    private Button search = new Button("Search");
    private Button help = new Button("Help");

    private Button moneyTransfer = new Button("Transfer Money!");
    private Button openActiveAccount = new Button("Open Active Account!");
    private Button createClient = new Button("Create a Client!");


    private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
    private NavigationTree tree = new NavigationTree();

    private ListView listView = null;
    private ContactList contactList = null;
    private ContactForm contactForm = null;

    private MoneyTransferForm moneyTransferForm = null;

    private SearchView searchView = null;

    private HelpWindow helpWindow = null;

    @Autowired
    private ContactContainer contactContainer;

    @Autowired
    private CommandBus commandBus;

    @Override
    public void init() {
        setTheme(Runo.themeName());
        buildMainLayout();
        newContact.setIcon(new ThemeResource(Theme.documentAdd));
        search.setIcon(new ThemeResource(Theme.search));
        help.setIcon(new ThemeResource(Theme.help));
    }

    private void buildMainLayout() {
        setMainWindow(new Window("Address Book Demo application"));

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.addComponent(createToolbar());
        verticalLayout.addComponent(horizontalSplit);
        verticalLayout.setExpandRatio(horizontalSplit, 1);
        horizontalSplit.setSplitPosition(150, HorizontalSplitPanel.UNITS_PIXELS);
        horizontalSplit.setFirstComponent(tree);
        tree.addListener((ItemClickEvent.ItemClickListener) this);
        getMainWindow().setContent(verticalLayout);
        setMainComponent(getListView());

    }

    private Window getHelpWindow() {
        if (helpWindow == null) {
            helpWindow = new HelpWindow();
        }
        return helpWindow;
    }

    private Component createToolbar() {
        search.addListener((Button.ClickListener) this);
        newContact.addListener((Button.ClickListener) this);
        moneyTransfer.addListener((Button.ClickListener) this);
        openActiveAccount.addListener((Button.ClickListener) this);
        createClient.addListener((Button.ClickListener) this);
        help.addListener((Button.ClickListener) this);

        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.addComponent(newContact);
        toolbar.addComponent(search);
        toolbar.addComponent(help);
        toolbar.addComponent(moneyTransfer);
        toolbar.addComponent(openActiveAccount);
        toolbar.addComponent(createClient);
        return toolbar;
    }

    private void setMainComponent(Component c) {
        horizontalSplit.setSecondComponent(c);
    }

    private ListView getListView() {
        if (listView == null) {
            contactList = new ContactList(contactContainer);
            contactList.setContainerDataSource(contactContainer);
            contactList.addListener((Property.ValueChangeListener) this);
            contactForm = new ContactForm(commandBus);
            contactForm.addListener((ContactForm.CommitListener) this);
            moneyTransferForm = new MoneyTransferForm(commandBus);
            listView = new ListView(contactList, contactForm);
        }
        contactForm.setItemDataSource(null);
        contactContainer.refreshContent();
        return listView;
    }

    private SearchView getSearchView() {
        if (searchView == null) {
            searchView = new SearchView();
        }
        return searchView;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        final Button source = event.getButton();
        if (source == search) {
            showSearchView();
        } else if (source == help) {
            getMainWindow().addWindow(getHelpWindow());
        } else if (source == newContact) {
            addNewContact();
        } else if (source == moneyTransfer) {
            transferMoney();
        } else if (source == openActiveAccount) {
            createAnActiveAccount();
        } else if (source == createClient) {
            createClient();
        }

    }

    private void createClient() {

        //BKONU: not implemented

    }

    private void createAnActiveAccount() {

        AggregateIdentifier clientId =
                new StringAggregateIdentifier("tqwuweqtohjo?j");

        OpenNewAccountForClientCommand openNewAccountForClientCommand
                = new OpenNewAccountForClientCommand(clientId, "ACCOUNT ABC");
        commandBus.dispatch(openNewAccountForClientCommand);
    }

    private void transferMoney() {
        showMoneyTransferView();
        moneyTransferForm.initialize();
    }

    private void showMoneyTransferView() {
        setMainComponent(moneyTransferForm);
    }

    private void showSearchView() {
        setMainComponent(getSearchView());
    }

    private void showListView() {
        setMainComponent(getListView());
    }

    private void addNewContact() {
        showListView();
        contactForm.addContact();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        Property property = event.getProperty();
        if (property == contactList) {
            @SuppressWarnings({"unchecked"})
            ContactEntry item = ((BeanItem<ContactEntry>) contactList.getItem(contactList.getValue())).getBean();
            ContactFormBean contactFormBean = new ContactFormBean(item.getIdentifier(), item.getName());
            contactForm.setItemDataSource(new BeanItem<ContactFormBean>(contactFormBean));
        }
    }

    @Override
    public void itemClick(ItemClickEvent event) {
        if (event.getSource() == tree) {
            Object itemId = event.getItemId();
            if (itemId != null) {
                if (NavigationTree.SHOW_ALL.equals(itemId)) {
                    showListView();
                } else if (NavigationTree.SEARCH.equals(itemId)) {
                    showSearchView();
                }
            }
        }
    }

    @Override
    public void formIsCommitted(ContactForm.FormIsSuccessfullyCommittedEvent event) {
        showListView();
    }
}
