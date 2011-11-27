package nijhof2axon.app.command;

public class OpenNewAccountForClientCommand {

    private String accountId;
    private String clientId;
    private String accountName;

    public OpenNewAccountForClientCommand(String clientId, String accountName) {
        this(null, clientId, accountName);
    }

    public OpenNewAccountForClientCommand(String accountId, String clientId, String accountName) {
        this.accountId = accountId;
        this.clientId = clientId;
        this.accountName = accountName;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getAccountName() {
        return accountName;
    }
}