package accounts;


import baseLogic.AccountService;
import baseLogic.DBService;
import dao.UserDao;
import dataSets.UserDataSet;
import db.DBServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountServiceImpl implements AccountService {
    static final Logger logger = LogManager.getLogger(AccountService.class.getName());

    private DBService dbService;
    private Map<String, UserDataSet> sessionToUserProfile;


    public AccountServiceImpl() {
        dbService = new DBServiceImpl();
        sessionToUserProfile = new ConcurrentHashMap<>();
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

    @Override
    public void addUserSession(String sessionId, UserDataSet user){
        sessionToUserProfile.put(sessionId, user);
    }

    @Override
    public void removeUserSession(String sessionId){
        sessionToUserProfile.remove(sessionId);
    }

    @Override
    public boolean hasUserSession(String sessonId) {
        return sessionToUserProfile.containsKey(sessonId);
    }

}
