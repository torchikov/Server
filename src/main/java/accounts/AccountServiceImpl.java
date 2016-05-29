package accounts;


import baseLogic.AccountService;
import baseLogic.DBService;
import dao.UserDao;
import dataSets.UserDataSet;
import db.DBServiceImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountServiceImpl implements AccountService {

    private DBService dbService;


    public AccountServiceImpl() {
        dbService = new DBServiceImpl();
    }

    @Override
    public UserDataSet getUser(String login) {
        try (Session session = dbService.getSession()) {
            UserDao userDao = new UserDao(session);
            UserDataSet userDataSet = userDao.get(login);
            return userDataSet;
        }
    }

    @Override
    public long addUser(UserDataSet userDataSet) {
        try (Session session = dbService.getSession()) {
            Transaction transaction = session.beginTransaction();
            UserDao userDao = new UserDao(session);
            long id = userDao.insertUser(userDataSet);
            transaction.commit();
            return id;
        }
    }

}
