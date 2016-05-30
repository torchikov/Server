package baseLogic;


import javax.management.DynamicMBean;

public interface AccountServerControllerMBean {


    int getUserLimit();

    void setUserLimit(int userLimit);
}
