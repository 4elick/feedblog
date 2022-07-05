package dao.jdbc.rowmapper;
import entity.Comment;
import entity.Post;
import entity.Role;
import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt(1);
        String comment = rs.getString(2);
        User user = new User(rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),Role.valueOf(rs.getString(7)));
        Post post = new Post(rs.getInt(8),rs.getString(9),user,rs.getBoolean(10),rs.getDate(11));
        return new Comment(id,user,comment,post);
    }
}
