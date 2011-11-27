package org.axonframework.sample.app.api.fohjin.commandHandler;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.axonframework.sample.app.api.fohjin.ActiveAccount;
import org.axonframework.sample.app.api.fohjin.command.ReceiveMoneyTransferCommand;
import org.axonframework.unitofwork.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-09-10
 * Time: 3:52:38 PM
 */
public class ReceiveMoneyTransferCommandHandler {

    private final static Logger logger = LoggerFactory.getLogger(ReceiveMoneyTransferCommandHandler.class);
    private Repository<ActiveAccount> repository;

    /**
     * Sets the contact domain event repository.
     *
     * @param repository the contact repository
     */
    public void setRepository(Repository<ActiveAccount> repository) {
        this.repository = repository;
    }

    /**
     * Creates a new contact based on the provided data. The provided user name is tested for uniqueness before
     * continuing.
     * <p/>
     * BEWARE
     * The mechanism to guarantee uniqueness is not a best practice for axon. This is a pretty expensive operation
     * especially when the number of contacts increases. It is better to make the client responsible for guaranteeing
     * unique contact names and make an explicit process to overcome the very rare situation where a duplicate contact
     * name is entered.
     *
     * @param command    CreateContactCommand object that contains the needed data to create a new contact
     * @param unitOfWork Unit of work for the current running thread
     */
    @CommandHandler
    public void handle(final ReceiveMoneyTransferCommand command, UnitOfWork unitOfWork) {
        logger.debug("Received a command for a new transfer for account : {}", command.getAccountNumber());
        Assert.notNull(command.getAccountNumber(), "Account Number may not be null");
        Assert.notNull(command.getAmount(), "Amount may not be null");

        ActiveAccount activeAccount = repository.load(command.getActiveAccountId());

        activeAccount.receiveTransferFrom(command.getAccountNumber(), command.getAmount());
    }



}
