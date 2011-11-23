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

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.examples.addressbook.vaadin.data.ActiveAccountContainer;
import org.axonframework.examples.addressbook.vaadin.data.ClientContainer;
import org.axonframework.examples.addressbook.vaadin.data.ContactContainer;
import org.axonframework.examples.addressbook.vaadin.data.LedgerContainer;
import org.axonframework.examples.addressbook.vaadin.events.ClientSelectedEvent;
import org.axonframework.examples.addressbook.vaadin.ui.activeAccount.ActiveAccountDetails;
import org.axonframework.examples.addressbook.vaadin.ui.activeAccount.CashDepositView;
import org.axonframework.examples.addressbook.vaadin.ui.activeAccount.CashWithdrawView;
import org.axonframework.examples.addressbook.vaadin.ui.client.ChangeNameView;
import org.axonframework.examples.addressbook.vaadin.ui.client.ClientDetails;
import org.axonframework.examples.addressbook.vaadin.ui.client.ClientView;
import org.axonframework.sample.app.query.ActiveAccountEntry;
import org.axonframework.sample.app.query.ClientEntry;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class Nijhof2AxonApplication extends MediatorApplication implements MediatorListener {

    @Autowired
    private ContactContainer contactContainer;

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
    private ClientDetails clientDetails;
    private ActiveAccountDetails activeAccountDetails;
    private CashDepositView cashDepositView;
    private CashWithdrawView cashWithdrawalView;

    @Override
    public void init() {

        Window mainWindow = new Window("Nijhof2Axon Application");
        setMainWindow(mainWindow);

        mainWindow.getContent().setSizeFull();

        mainVerticalLayout = new VerticalLayout();

        clientView = new ClientView(commandBus, clientContainer);
        mainVerticalLayout.addComponent(clientView);

        mainWindow.setContent(mainVerticalLayout);

        addCollaborator(this);

    }

//    //BKONU: replace with mediator pattern
//
//    public void switchToClientDetailsMode(ClientEntry clientEntry) {
//
//        clientDetails = new ClientDetails(clientEntry, commandBus, activeAccountContainer);
//        mainVerticalLayout.replaceComponent(clientView, clientDetails);
//    }


    public void switchToActiveAccountDetailsMode(ActiveAccountEntry activeAccountEntry) {
        activeAccountDetails = new ActiveAccountDetails(activeAccountEntry, commandBus, ledgerContainer);
        mainVerticalLayout.replaceComponent(clientDetails, activeAccountDetails);
    }

    public void switchToCashDepositeMode(ActiveAccountEntry activeAccountEntry) {
        cashDepositView = new CashDepositView(activeAccountEntry, commandBus, ledgerContainer);
        mainVerticalLayout.replaceComponent(activeAccountDetails, cashDepositView);
    }

    public void switchBackToAccountDetailsMode(ActiveAccountEntry activeAccountEntry) {
        mainVerticalLayout.replaceComponent(cashDepositView, activeAccountDetails);
    }

    public void switchToCashWithdrawalMode(ActiveAccountEntry activeAccountEntry) {
        cashWithdrawalView = new CashWithdrawView(activeAccountEntry, commandBus, ledgerContainer);
        mainVerticalLayout.replaceComponent(activeAccountDetails, cashWithdrawalView);
    }

    public void switchToChangeNameMode(ClientEntry clientEntry) {
        ChangeNameView changeNameView = new ChangeNameView(clientEntry, commandBus);
        mainVerticalLayout.replaceComponent(clientDetails, changeNameView);

    }

    @Override
    public void handleEvent(MediatorEvent event) {
        if (event instanceof ClientSelectedEvent) {
            clientDetails = new ClientDetails(((ClientSelectedEvent) event).getSelectedClient(), commandBus, activeAccountContainer);
            mainVerticalLayout.replaceComponent(clientView, clientDetails);
        }
    }
}
