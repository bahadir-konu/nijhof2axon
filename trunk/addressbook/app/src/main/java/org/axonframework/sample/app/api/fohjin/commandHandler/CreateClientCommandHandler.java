package org.axonframework.sample.app.api.fohjin.commandHandler;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.domain.StringAggregateIdentifier;
import org.axonframework.repository.Repository;
import org.axonframework.sample.app.api.fohjin.Client;
import org.axonframework.sample.app.api.fohjin.command.CreateClientCommand;
import org.axonframework.unitofwork.UnitOfWork;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * Author: Bahadir Konu (bah.konu@gmail.com)
 */
public class CreateClientCommandHandler {

    private Repository<Client> clientRepository;

    @CommandHandler
    public void handle(final CreateClientCommand command, UnitOfWork unitOfWork) {
        Assert.notNull(command.getName(), "Client name may not be null");

        AggregateIdentifier id = new StringAggregateIdentifier(UUID.randomUUID().toString());

        Client client = new Client(id, command.getName());

        clientRepository.add(client);
    }

    public void setClientRepository(Repository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }
}
