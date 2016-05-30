package resources;


public class DBParametersResource {
    private String name;
    private String password;
    private String user;

    public DBParametersResource() {
        this.name = "";
        this.password = "";
        this.user = "";
    }

    public DBParametersResource(String name, String password, String user) {
        this.name = name;
        this.password = password;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }
}
