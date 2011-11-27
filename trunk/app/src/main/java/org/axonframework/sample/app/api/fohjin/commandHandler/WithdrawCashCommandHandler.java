package org.axonframework.sample.app.api.fohjin.commandHandler;

import nijhof2axon.app.domain.ActiveAccount;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.domain.AggregateIdentifier;
import org.axonframework.domain.StringAggregateIdentifier;
import org.axonframework.repository.Repository;
import org.axonframework.sample.app.api.fohjin.command.WithdrawCashCommand;
import org.axonframework.unitofwork.UnitOfWork;
import org.springframework.beans.factory.annotation.Required;

/**
 * User: Bahadir Konu (bah.konu@gmail.com)
 * Date: 2011-11-19
 * Time: 11:56:08 AM
 */
public class WithdrawCashCommandHandler {

    private Repository<ActiveAccount> activeAccountRepository;

    @CommandHandler
    public void handle(final WithdrawCashCommand command, UnitOfWork unitOfWork) {

        AggregateIdentifier identifier = new StringAggregateIdentifier(command.getActiveAccountId());

        ActiveAccount activeAccount = activeAccountRepository.load(identifier);

        activeAccount.withdrawCash(command.getAmount());

    }

    @Required
    public void setActiveAccountRepository(Repository<ActiveAccount> activeAccountRepository) {
        this.activeAccountRepository = activeAccountRepository;
    }
}
