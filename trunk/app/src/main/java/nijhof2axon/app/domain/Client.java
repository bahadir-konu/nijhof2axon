package nijhof2axon.app.domain;

import nijhof2axon.app.event.ClientCreatedEvent;
import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import nijhof2axon.app.event.ClientNameChangedEvent;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-17
 * Time: 3:15:28 PM
 */
public class Client extends AbstractAnnotatedAggregateRoot {

    private String name;

    public Client(AggregateIdentifier identifier, String name) {
        super(identifier);
        apply(new ClientCreatedEvent(name));
    }

    public Client(AggregateIdentifier identifier) {
        super(identifier);
    }

    @EventHandler
    protected void handleClientCreatedEvent(ClientCreatedEvent event) {
    }

    public String getName() {
        return name;
    }

    public ActiveAccount createNewActiveAccount(AggregateIdentifier accountIdentifier, String accountName) {

        ActiveAccount activeAccount = new ActiveAccount(accountIdentifier, getIdentifier(), accountName);

        return activeAccount;
    }

    public void changeNameAs(String newName) {
        apply(new ClientNameChangedEvent(getIdentifier().asString(), newName));
    }

    @EventHandler
    private void handleClientNameChangedEvent(ClientNameChangedEvent event) {
        name = event.getNewName();
    } 
}


