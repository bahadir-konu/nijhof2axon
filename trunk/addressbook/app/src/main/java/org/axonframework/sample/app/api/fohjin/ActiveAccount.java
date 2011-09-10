package org.axonframework.sample.app.api.fohjin;

import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.sample.app.api.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 10:44:13 AM
 */
public class ActiveAccount extends AbstractAnnotatedAggregateRoot {

    String accountName;
    String accountNumber;
    BigDecimal balance;

    private List<Ledger> ledgers;

    private Map<AddressType, Address> addresses = new HashMap<AddressType, Address>();

    public ActiveAccount(AggregateIdentifier identifier, String name) {
        super(identifier);
        apply(new ContactCreatedEvent(name));
    }

    public ActiveAccount(AggregateIdentifier identifier) {
        super(identifier);
    }

    /**
     * Register the provided address with the provided type. If a contact already has an address of the provided type,
     * that address is changed.
     *
     * @param type    AddressType of the address to add or change
     * @param address Address to add or change
     */
    public void registerAddress(AddressType type, Address address) {
        if (addresses.containsKey(type)) {
            apply(new AddressChangedEvent(type, address));
        } else {
            apply(new AddressAddedEvent(type, address));
        }
    }

    /**
     * Removes the address with the provided type if it exists.
     *
     * @param type AddressType of the address that needs to be removed
     */
    public void removeAddress(AddressType type) {
        if (addresses.containsKey(type)) {
            apply(new AddressRemovedEvent(type));
        }
    }

    /**
     * Change the name of the contact
     *
     * @param name String containing the new name
     */
    public void changeName(String name) {
        apply(new ContactNameChangedEvent(name));
    }

    public void delete() {
        apply(new ContactDeletedEvent());
    }

    @EventHandler
    protected void handleContactCreatedEvent(ContactCreatedEvent event) {
    }

    @EventHandler
    protected void handleContactNameChangedEvent(ContactNameChangedEvent event) {
    }

    @EventHandler
    protected void handleAddressRegisteredEvent(AddressRegisteredEvent event) {
        addresses.put(event.getType(), event.getAddress());
    }

    @EventHandler
    protected void handleAddressRemovedEvent(AddressRemovedEvent event) {
        addresses.remove(event.getType());
    }

    @EventHandler
    protected void handleMoneyTransferReceivedEvent(MoneyTransferReceivedEvent event) {

        ledgers.add(new DebitTransfer(event.getAmount(), event.getTargetAccountNumber()));
        balance = event.getNewBalance();
    }


    public void receiveTransferFrom(String sourceAccountNumber, BigDecimal amount) {

        BigDecimal newBalance = balance.subtract(amount);

        apply(new MoneyTransferReceivedEvent(newBalance, amount, sourceAccountNumber, accountNumber));
    }

}

