package jk.uz.pdp.model;

import jk.uz.pdp.service.UserService;

public class User extends BaseModel{
    private String name;
    private String username;
    private String password;

    public User() {
        super();
    }

    public User(String name, String username, String password) {
        super();
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
