package dao.jdbc.rowmapper;

import entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {


    @Override
    public Category mapRow(ResultSet rs, int i) throws SQLException {
        int id = rs.getInt(1);
        String title = rs.getString(2);
        return new Category(id,title);
    }
}
