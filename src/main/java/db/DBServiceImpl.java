package db;


import baseLogic.DBService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBServiceImpl implements DBService {
    static final Logger logger = LogManager.getLogger(DBService.class.getName());
    private final SessionFactory sessionFactory;
    private String dbName;
    private String login;
    private String password;

    public DBServiceImpl() {
        Properties propertiesDB = new Properties();
        try (InputStream is = new FileInputStream("configDB.properties")){
            propertiesDB.load(is);

            dbName = propertiesDB.getProperty("database");
            login = propertiesDB.getProperty("login");
            password = propertiesDB.getProperty("password");

        } catch (IOException e){
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        Configuration configuration = getDBConfiguration(dbName, login, password);
        this.sessionFactory = createSessionFactory(configuration);
    }

    @Override
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
