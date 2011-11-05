package org.axonframework.examples.addressbook.vaadin.data;

import com.vaadin.data.util.BeanItemContainer;
import org.axonframework.sample.app.query.LedgerEntry;
import org.axonframework.sample.app.query.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-04
 * Time: 11:26:59 AM
 */
@Component
public class LedgerContainer extends BeanItemContainer<LedgerEntry> implements Serializable {

    @Autowired
    private LedgerRepository ledgerRepository;

    public LedgerContainer() throws IllegalArgumentException {
        super(LedgerEntry.class);
    }

    public void refreshContent() {
        List<LedgerEntry> allLedgers = ledgerRepository.findAllLedgers();
        removeAllItems();
        addAll(allLedgers);
    }


}


