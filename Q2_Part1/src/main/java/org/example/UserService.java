package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public interface UserService {
    boolean usernameExists(String username);
    boolean saveUser(String username, String password);
}
