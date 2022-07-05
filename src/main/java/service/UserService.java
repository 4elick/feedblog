
package service;

import entity.User;

import dao.UserDao;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean save(User user) {
        if (userDao.containsByName(user.getFirstName())) {
            return false;
        } else {
            userDao.save(user);
        }
        return true;
    }

    public boolean deleteById(int id) {
        if (userDao.containsById(id)) {
            userDao.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteByName(String name) {
        if (userDao.containsByName(name)) {
            userDao.deleteByName(name);
            return true;
        }
        return false;
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getById(int id) {
        if (userDao.containsById(id)) {
            return userDao.getById(id);
        }
        return null;
    }

    public User getByName(String name) {
        if (userDao.containsByName(name)) {
            return userDao.getByName(name);
        }
        return null;
    }

    public void updateName(int id, String name) {
        userDao.updateName(id, name);
    }

    public void updatePassword(int id, String password) {
        userDao.updatePassword(id, password);
    }


    public boolean registration(User user) {
        if (userDao.containsByName(user.getFirstName())) {
            return false;
        } else {
            userDao.save(user);
        }
        return true;
    }

    public boolean authorization(String username, String password) {
        return userDao.authorization(username, password);
    }
}