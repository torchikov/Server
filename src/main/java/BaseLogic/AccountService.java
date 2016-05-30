package baseLogic;

import dataSets.UserDataSet;


public interface AccountService {

    UserDataSet getUser(String login);

    long addUser(UserDataSet userDataSet);

    void addUserSession(String sessionId, UserDataSet user);

    void removeUserSession(String sessionId);

    boolean hasUserSession(String sessonId);

}
