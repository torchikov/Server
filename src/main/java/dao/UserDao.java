package dao;


import dataSets.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDao {
    private Session session;

    public UserDao(Session session) {
        this.session = session;
    }

    public UserDataSet get(String login) {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        criteria.add(Restrictions.eq("login", login));
        UserDataSet userDataSet = (UserDataSet) criteria.uniqueResult();
        return userDataSet;
    }

    public long insertUser(UserDataSet userDataSet) {
        return (long) session.save(userDataSet);
    }
}
