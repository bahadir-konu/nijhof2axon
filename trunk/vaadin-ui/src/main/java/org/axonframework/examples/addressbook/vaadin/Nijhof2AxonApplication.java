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
import com.vaadin.ui.VerticalLayout;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.data.ActiveAccountContainer;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;
import org.axonframework.examples.addressbook.vaadin.data.LedgerContainer;
import org.axonframework.examples.addressbook.vaadin.events.*;
import org.axonframework.examples.addressbook.vaadin.ui.activeAccount.*;
import org.axonframework.examples.addressbook.vaadin.ui.client.ChangeClientNameWindow;
import org.axonframework.examples.addressbook.vaadin.ui.client.ClientDetailsView;
import org.axonframework.examples.addressbook.vaadin.ui.client.ClientView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class Nijhof2AxonApplication extends Application implements MediatorListener {

    protected MainWindow mainWindow;

    @Autowired
    private ClientContainer clientContainer;

    @Autowired
    private ActiveAccountContainer activeAccountContainer;

    @Autowired
    private LedgerContainer ledgerContainer;

    @Autowired
    private CommandBus commandBus;

    private VerticalLayout mainVerticalLayout;
    private ClientView clientView;
    private ClientDetailsView clientDetailsView;
    private ActiveAccountView activeAccountView;
    private CashWithdrawalView cashWithdrawalView;

    @Override
    public void init() {

        setTheme("runo");

        mainWindow = new MainWindow();

        mainWindow.getContent().setSizeFull();

        mainVerticalLayout = new VerticalLayout();

        clientView = new ClientView(commandBus, clientContainer);
        mainVerticalLayout.addComponent(clientView);

        mainWindow.setContent(mainVerticalLayout);

        clientDetailsView = new ClientDetailsView(activeAccountContainer, null);
        activeAccountView = new ActiveAccountView(activeAccountContainer, ledgerContainer, commandBus);
        cashWithdrawalView = new CashWithdrawalView(commandBus, ledgerContainer);

        mainWindow.addCollaborator(clientView);
        mainWindow.addCollaborator(clientDetailsView);
        mainWindow.addCollaborator(activeAccountContainer);
        mainWindow.addCollaborator(this);
        mainWindow.addCollaborator(activeAccountView);

        setMainWindow(mainWindow);

    }

    @Override
    public void handleEvent(UIEvent event) {
        if (event instanceof ClientSelectedEvent) {
            mainVerticalLayout.replaceComponent(clientView, clientDetailsView);
        }

        if (event instanceof ActiveAccountDetailsRequestedEvent) {
            mainVerticalLayout.replaceComponent(clientDetailsView, activeAccountView);
        }

        if (event instanceof CashWithdrawalCompletedEvent) {
            mainVerticalLayout.replaceComponent(cashWithdrawalView, activeAccountView);
        }

        if (event instanceof ChangeClientNameRequestedEvent) {
            mainWindow.addWindow(new ChangeClientNameWindow(commandBus, ((ChangeClientNameRequestedEvent) event).getClientDetailsEntry()
            ));
        }

        if (event instanceof ClientListViewRequestedEvent) {
            mainVerticalLayout.replaceComponent(clientDetailsView, clientView);
        }

        if (event instanceof AddActiveAccountRequestedEvent) {
            mainWindow.addWindow(new AddActiveAccountWindow(commandBus, ((AddActiveAccountRequestedEvent) event).getClientDetailsEntry(),
                    activeAccountContainer));
        }

        if (event instanceof ClientDetailsViewRequestedEvent) {
            mainVerticalLayout.replaceComponent(activeAccountView, clientDetailsView);
        }

        if (event instanceof CashDepositeRequestedEvent) {
            mainWindow.addWindow(new CashDepositWindow(((CashDepositeRequestedEvent) event).getActiveAccountEntry(),
                    commandBus));
        }

        if (event instanceof CashWithdrawalRequestedEvent) {
            mainWindow.addWindow(new CashWithdrawalWindow(((CashWithdrawalRequestedEvent) event).getActiveAccountEntry(),
                    commandBus));
        }


    }

    @Override
    public MainWindow getMainWindow() {
        return mainWindow;
    }
}
