package org.axonframework.sample.app.api.fohjin.commandHandler;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.axonframework.sample.app.api.fohjin.ActiveAccount;
import org.axonframework.sample.app.api.fohjin.Client;
import org.axonframework.sample.app.api.fohjin.command.OpenNewAccountForClientCommand;
import org.axonframework.unitofwork.UnitOfWork;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-17
 * Time: 3:14:16 PM
 */
public class OpenNewAccountForClientCommandHandler {

    private Repository<Client> clientRepository;
    private Repository<ActiveAccount> activeAccountRepository;

    @Required
    public void setClientRepository(Repository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Required
    public void setActiveAccountRepository(Repository<ActiveAccount> activeAccountRepository) {
        this.activeAccountRepository = activeAccountRepository;
    }

    @CommandHandler
    public void handle(final OpenNewAccountForClientCommand command, UnitOfWork unitOfWork) {
        Assert.notNull(command.getAccountName(), "Account Name may not be null");
        Assert.notNull(command.getClientId(), "Client may not be null");

        Client client = clientRepository.load(command.getClientId());

        ActiveAccount activeAccount = client.createNewActiveAccount(command.getAccountName());

        activeAccountRepository.add(activeAccount);
    }


}

