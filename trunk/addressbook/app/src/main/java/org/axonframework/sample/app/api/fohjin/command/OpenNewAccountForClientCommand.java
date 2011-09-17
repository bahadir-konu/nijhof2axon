package org.axonframework.sample.app.api.fohjin.command;

import org.axonframework.domain.AggregateIdentifier;

public class OpenNewAccountForClientCommand {

    private AggregateIdentifier clientId;
    private String accountName;

    public OpenNewAccountForClientCommand(AggregateIdentifier clientId, String accountName) {
        this.clientId = clientId;
        this.accountName = accountName;
    }

    public AggregateIdentifier getClientId() {
        return clientId;
    }

    public String getAccountName() {
        return accountName;
    }

}