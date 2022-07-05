package dao.jdbc.rowmapper;

import entity.Role;
import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt(1);
        String login = rs.getString(2);
        String password = rs.getString(3);
        String firstName = rs.getString(4);
        String role = rs.getString(5);
        return new User(id,login,password,firstName, Role.valueOf(role));
    }
}
