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
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;
import org.axonframework.examples.addressbook.vaadin.data.ContactContainer;
import org.axonframework.sample.app.api.fohjin.command.CreateClientCommand;
import org.axonframework.sample.app.query.ClientEntry;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class Nijhof2AxonApplication extends Application {

    private Button moneyTransfer = new Button("Transfer Money!");
    private Button openActiveAccount = new Button("Open Active Account!");
    private Button createClient = new Button("Create a Client");

    @Autowired
    private ContactContainer contactContainer;

    @Autowired
    private ClientContainer clientContainer;

    @Autowired
    private CommandBus commandBus;

    @Override
    public void init() {

        Window mainWindow = new Window("Nijhof2Axon Application");
        setMainWindow(mainWindow);

        VerticalLayout mainVerticalLayout = new VerticalLayout();

        // Client list
        VerticalLayout clientList = new VerticalLayout();

        Table myTable = getClientsTable();

        clientList.addComponent(myTable);

        mainVerticalLayout.addComponent(clientList);

        //Client form

        VerticalLayout clientFormLayout = new VerticalLayout();

        final Form clientForm = new Form();
        ClientEntry clientEntry = new ClientEntry();
        BeanItem item = new BeanItem(clientEntry);
        item.removeItemProperty("identifier");

        clientForm.setItemDataSource(item);

        clientFormLayout.addComponent(clientForm);

        Button saveButton = new Button("Save");
        saveButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                String clientName = clientForm.getItemDataSource().getItemProperty("name").toString();
                CreateClientCommand createClientCommand = new CreateClientCommand(clientName
                );

                commandBus.dispatch(createClientCommand);

                clientContainer.refreshContent();

            }
        });

        clientFormLayout.addComponent(saveButton);

        mainVerticalLayout.addComponent(clientFormLayout);


        mainWindow.setContent(mainVerticalLayout);


    }

    private Table getClientsTable() {

        Table clientsTable = new Table("Clients");
        clientsTable.setContainerDataSource(clientContainer);

        clientContainer.refreshContent();

        test
        return clientsTable;
    }


}
