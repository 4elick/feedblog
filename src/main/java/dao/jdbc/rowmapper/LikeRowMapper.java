package dao.jdbc.rowmapper;

import entity.Like;
import entity.Post;
import entity.Role;
import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeRowMapper implements RowMapper<Like> {
    @Override
    public Like mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt(1);
        User user = new User(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5), Role.valueOf(rs.getString(6)));
        Post post = new Post(rs.getInt(7),rs.getString(8),user,rs.getBoolean(9),rs.getDate(10));
        return new Like(id,post,user);
    }
}
