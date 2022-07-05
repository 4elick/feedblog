package dao.jdbc.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryNameRowMapper implements RowMapper<String> {
@Override
public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        String name = rs.getString(1);
        return name;
        }
}
