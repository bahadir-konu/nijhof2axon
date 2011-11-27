package org.axonframework.sample.app.api.fohjin.commandHandler;

import nijhof2axon.app.domain.Client;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.domain.StringAggregateIdentifier;
import org.axonframework.repository.Repository;
import org.axonframework.sample.app.api.fohjin.command.ChangeClientNameCommand;
import org.axonframework.unitofwork.UnitOfWork;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-22
 * Time: 10:12:59 AM
 */
public class ChangeClientNameCommandHandler {
    private Repository<Client> clientRepository;

    @CommandHandler
    public void handle(final ChangeClientNameCommand command, UnitOfWork unitOfWork) {

        Client client = clientRepository.load(new StringAggregateIdentifier(command.getClientIdentifier()));

        client.changeNameAs(command.getNewName());
    }

    public void setClientRepository(Repository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }
}
