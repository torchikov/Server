package db;


import baseLogic.DBService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import resources.DBParametersResource;
import sax.ReadXMLFileSax;

public class DBServiceImpl implements DBService {
    static final Logger logger = LogManager.getLogger(DBService.class.getName());
    private final SessionFactory sessionFactory;

    public DBServiceImpl() {
        Configuration configuration = getDBConfiguration(getDBParameters());
        this.sessionFactory = createSessionFactory(configuration);
    }

    @Override
    public Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public DBParametersResource getDBParameters() {
        return (DBParametersResource) ReadXMLFileSax.readXML("./data/MySqlResource.xdb");
    }
}
