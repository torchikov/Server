package db;


import baseLogic.DBService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBServiceImpl implements DBService {

    private final SessionFactory sessionFactory;

    public DBServiceImpl() {
        Configuration configuration = getDBConfiguration();
        this.sessionFactory = createSessionFactory(configuration);
    }

    @Override
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
