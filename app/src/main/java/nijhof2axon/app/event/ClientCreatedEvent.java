package nijhof2axon.app.event;

import org.axonframework.domain.DomainEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-17
 * Time: 3:19:23 PM
 */
public class ClientCreatedEvent extends DomainEvent {

    private final String name;

    public ClientCreatedEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getClientIdentifier() {
        return getAggregateIdentifier().asString();
    }


}
