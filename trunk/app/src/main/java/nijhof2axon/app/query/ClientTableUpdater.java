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
        ClientEntry entry = new ClientEntry();
        entry.setIdentifier(event.getClientIdentifier());
        entry.setName(event.getName());
        clientRepository.persist(entry);
    }

    @EventHandler
    public void handleClientNameChangedEvent(ClientNameChangedEvent event) {          
        ClientEntry clientEntry = clientRepository.findByIdentifier(event.getClientIdentifier());
        clientEntry.setName(event.getNewName());

        clientRepository.persist(clientEntry);
    }

    @Required
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}

