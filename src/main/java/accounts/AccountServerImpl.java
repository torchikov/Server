package accounts;


import baseLogic.AccountServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountServerImpl implements AccountServer {
    static final Logger logger = LogManager.getLogger(AccountServer.class.getName());
    private int usersLimit;

    public AccountServerImpl(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    @Override
    public int getUserLimit() {
        return usersLimit;
    }

    @Override
    public void setUserLimit(int limit) {
        this.usersLimit = limit;
    }
}
