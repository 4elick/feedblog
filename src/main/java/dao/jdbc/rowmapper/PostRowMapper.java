package dao.jdbc.rowmapper;

import entity.Category;
import entity.Post;
import entity.Role;
import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt(1);
        String title = rs.getString(2);
        String description = rs.getString(3);
        User user = new User(rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7), Role.valueOf(rs.getString(8)));
        int id1 = rs.getInt(9);
        String title1 = rs.getString(10);
        Category category = new Category(id1,title1);
        Date date = rs.getDate(11);
        int views = rs.getInt(12);
        boolean isChecked = rs.getBoolean(13);
        return new Post(id,title,description,user,category,isChecked,date,views);
    }
}
