package accounts;


import BaseLogic.AccountService;
import dao.UserDao;
import dataSets.UserDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AccountServiceImpl implements AccountService {


    private final SessionFactory sessionFactory;

    public AccountServiceImpl() {
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    @Override
    public UserDataSet getUser(String login) {
        try (Session session = sessionFactory.openSession()) {
            UserDao userDao = new UserDao(session);
            UserDataSet userDataSet = userDao.get(login);
            return userDataSet;
        }
    }

    @Override
    public long addUser(UserDataSet userDataSet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            UserDao userDao = new UserDao(session);
            long id = userDao.insertUser(userDataSet);
            transaction.commit();
            return id;
        }
    }

}
