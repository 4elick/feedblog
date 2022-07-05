package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    void deleteById(int id);
    void deleteByName(String name);
    List<User> getAll();
    User getById(int id);
    User getByName(String name);
    boolean containsById(int id);
    boolean containsByName(String name);
    void updateName(int id, String name);
    void updatePassword(int id, String password);
    boolean authorization(String username, String password);
}
