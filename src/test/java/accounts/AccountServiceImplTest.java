package accounts;

import baseLogic.AccountService;
import dataSets.UserDataSet;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class AccountServiceImplTest {

    UserDataSet user;
    AccountService accountService;

    @Before
    public void setUp(){
        user = new UserDataSet("admin", "admin");
        accountService = mock(AccountServiceImpl.class);
        when(accountService.getUser("admin")).thenReturn(user);
        when(accountService.addUser(user)).thenReturn(13L);
    }

    @After
    public void tearDown(){
        user = null;
        accountService = null;
    }

    @Test
    public void getUser() throws Exception {
        UserDataSet user = accountService.getUser("admin");
        assertEquals(user, this.user);
    }

    @Test
    public void addUser() throws Exception {
        assertEquals(accountService.addUser(user), 13L);
    }

}