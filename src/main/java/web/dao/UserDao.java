package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    User findByUsername(String username);

    void saveUser(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    User getUserById(long id);

    User showUser(long id);

    void updateUser(User user);
}
