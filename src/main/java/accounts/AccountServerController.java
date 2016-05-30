package accounts;

import baseLogic.AccountServer;
import baseLogic.AccountServerControllerMBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class AccountServerController implements AccountServerControllerMBean {
    static final Logger logger = LogManager.getLogger(AccountServerController.class.getName());
    private AccountServer accountServer;

    public AccountServerController(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public int getUserLimit() {
        return accountServer.getUserLimit();
    }

    @Override
    public void setUserLimit(int userLimit) {
        accountServer.setUserLimit(userLimit);
    }
}
