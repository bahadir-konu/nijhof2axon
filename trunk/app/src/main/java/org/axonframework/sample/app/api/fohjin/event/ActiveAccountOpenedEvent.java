package org.axonframework.sample.app.api.fohjin.event;

import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.domain.DomainEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-17
 * Time: 3:34:48 PM
 */
public class ActiveAccountOpenedEvent extends DomainEvent {
    private AggregateIdentifier clientId;
    private String accountName;

    public ActiveAccountOpenedEvent(AggregateIdentifier clientId, String accountName) {
        this.clientId = clientId;
        this.accountName = accountName;
    }

    public AggregateIdentifier getClientId() {
        return clientId;
    }

    public void setClientId(AggregateIdentifier clientId) {
        this.clientId = clientId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
