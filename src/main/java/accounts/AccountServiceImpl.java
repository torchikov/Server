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
        logger.info("getUser() <====== " + login);
        try (Session session = dbService.getSession()) {
            UserDao userDao = new UserDao(session);
            UserDataSet userDataSet = userDao.get(login);
            logger.info("getUser() ======> " + userDataSet);
            return userDataSet;

        }

    }

    @Override
    public long addUser(UserDataSet userDataSet) {
        logger.info("addUser() <====== " + userDataSet);
        try (Session session = dbService.getSession()) {
            Transaction transaction = session.beginTransaction();
            UserDao userDao = new UserDao(session);
            long id = userDao.insertUser(userDataSet);
            transaction.commit();
            logger.info("addUser() ======> id:" + id);
            return id;
        }
    }

    @Override
    public void addUserSession(String sessionId, UserDataSet user){
        logger.info("addUserSession() <======");
        sessionToUserProfile.put(sessionId, user);
        logger.info("addUserSession() ======>");
    }

    @Override
    public void removeUserSession(String sessionId){
        logger.info("removeUserSession() <======");
        sessionToUserProfile.remove(sessionId);
        logger.info("removeUserSession() ======>");
    }

    @Override
    public boolean hasUserSession(String sessonId) {
        return sessionToUserProfile.containsKey(sessonId);
    }

}
