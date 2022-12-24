package jk.uz.pdp.service;

import jk.uz.pdp.model.User;

import java.util.UUID;

public class UserService {
    private static User[] userList = new User[100];
    private int index = 0;

    public boolean addUser(String name, String username, String password) {
        User user = getCheckUser(username);
        if (user != null) {
            return false;
        }
        userList[index++] = new User(name, username, password);
        return true;
    }

    public User login(
            String username,
            String password
    ){
        User user = getCheckUser(username);
        if (user == null){
            return null;
        }
        if (user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    static User getUser(UUID userId){
        for (User user: userList) {
            if (user != null){
                if (user.getId().equals(userId)){
                    return user;
                }
            }
        }
        return null;
    }

    public User getCheckUser(String username) {
        for (User user : userList) {
            if (user != null) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        }
        return null;
    }
}
