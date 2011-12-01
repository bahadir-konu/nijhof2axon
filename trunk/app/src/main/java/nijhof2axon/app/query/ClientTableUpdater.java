package nijhof2axon.app.query;

import nijhof2axon.app.event.ClientCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import nijhof2axon.app.event.ClientNameChangedEvent;
import org.springframework.beans.factory.annotation.Required;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-10-02
 * Time: 2:59:29 PM
 */
public class ClientTableUpdater {
    private ClientRepository clientRepository;

    @EventHandler
    public void handleClientCreatedEvent(ClientCreatedEvent event) {
        ClientDetailsEntry entry = new ClientDetailsEntry();
        entry.setIdentifier(event.getClientIdentifier());
        entry.setClientName(event.getName());
        clientRepository.persist(entry);
    }

    @EventHandler
    public void handleClientNameChangedEvent(ClientNameChangedEvent event) {          
        ClientDetailsEntry clientEntry = clientRepository.findByIdentifier(event.getClientIdentifier());
        clientEntry.setClientName(event.getNewName());

        clientRepository.persist(clientEntry);
    }

    @Required
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}

