package org.axonframework.examples.addressbook.vaadin.ui.activeAccount;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.axonframework.sample.app.query.ActiveAccountEntry;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-05
 * Time: 9:34:51 AM
 */
public class CashDepositView extends VerticalLayout {

    public CashDepositView(ActiveAccountEntry activeAccountEntry) {

        TextField depositAmount = new TextField("Specify the amount to be deposit");

        addComponent(depositAmount);

        Button deposit = new Button("Deposit");
        
        addComponent(deposit);
        

    }
}
