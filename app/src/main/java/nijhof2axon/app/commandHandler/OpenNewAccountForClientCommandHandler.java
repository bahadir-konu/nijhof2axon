package nijhof2axon.app.commandHandler;

import nijhof2axon.app.domain.ActiveAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.domain.StringAggregateIdentifier;
import org.axonframework.repository.Repository;
import nijhof2axon.app.domain.Client;
import nijhof2axon.app.command.OpenNewAccountForClientCommand;
import org.axonframework.unitofwork.UnitOfWork;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.util.UUID;

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

        Client client = clientRepository.load(new StringAggregateIdentifier(command.getClientId()));

        String accountId = command.getAccountId();
        if (accountId == null) {
            accountId = UUID.randomUUID().toString();
        }

        ActiveAccount activeAccount = client.createNewActiveAccount(new StringAggregateIdentifier(accountId), command.getAccountName(), command.getAccountNumber());

        activeAccountRepository.add(activeAccount);
    }


}

