package org.axonframework.sample.app.api.fohjin.event;

import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.domain.DomainEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-17
 * Time: 3:34:48 PM
 */
public class ActiveAccountOpenedEvent extends DomainEvent {
    public ActiveAccountOpenedEvent(AggregateIdentifier clientId, String accountName) {

    }
}
