package dao.jdbc;

import dao.UserDao;
import dao.jdbc.rowmapper.UserRowMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class JdbcUserDao implements UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users VALUES(default ,?,?,?,?)",user.getLogin(),user.getPassword(),user.getFirstName(),user.getRole());
    }

    @Override
    public void deleteById(int id) {
            jdbcTemplate.update("DELETE FROM users WHERE user_id = ?",id);
    }

    @Override
    public void deleteByName(String name) {
            jdbcTemplate.update("DELETE FROM users WHERE user_name = ?",name);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT* FROM users",new UserRowMapper());
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject("SELECT* FROM users WHERE user_id = ?",new UserRowMapper(),new Object[]{id});
    }

    @Override
    public User getByName(String name) {
        return jdbcTemplate.queryForObject("SELECT* FROM users WHERE user_name = ?",new UserRowMapper(),new Object[]{name});
    }

    @Override
    public boolean containsById(int id) {
        try {
            jdbcTemplate.queryForObject("SELECT* FROM users WHERE user_id = ?",new UserRowMapper(),new Object[]{id});
        } catch (EmptyResultDataAccessException e ){
            return false;
        }
        return true;
    }

    @Override
    public boolean containsByName(String name) {
        try {
            jdbcTemplate.queryForObject("SELECT* FROM users WHERE user_name = ?",new UserRowMapper(),new Object[]{name});
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public void updateName(int id, String name) {
        jdbcTemplate.update("UPDATE users SET user_name = ? WHERE user_id = ?",name,id);
    }

    @Override
    public void updatePassword(int id, String password) {
        jdbcTemplate.update("UPDATE users SET user_password = ? WHERE user_id = ?",password,id);
    }

    @Override
    public boolean authorization(String username, String password) {
        try {
            jdbcTemplate.queryForObject("SELECT* FROM users WHERE user_login = ? AND user_password = ?",new UserRowMapper(),new Object[]{username,password});
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }
}
