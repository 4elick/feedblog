package dao.jdbc;

import dao.CategoryDao;
import dao.jdbc.rowmapper.CategoryRowMapper;
import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcCategoryDao implements CategoryDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(Category category) {
        jdbcTemplate.update("INSERT INTO category VALUES(?,)",category.getTitle());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM category WHERE category_id = ?",id);
    }

    @Override
    public void deleteByTitle(String title) {
        jdbcTemplate.update("DELETE FROM category WHERE category_title = ?",title);
    }

    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query("SELECT* FROM category",new CategoryRowMapper());
    }

    @Override
    public Category getById(int id) {
        return jdbcTemplate.queryForObject("SELECT* FROM category WHERE category_id = ?",new CategoryRowMapper(),new Object[]{id});
    }

    @Override
    public Category getByTitle(String title) {
        return jdbcTemplate.queryForObject("SELECT* FROM category WHERE category_title = ?",new CategoryRowMapper(),new Object[]{title});
    }

    @Override
    public boolean containsById(int id) {
        try {
            jdbcTemplate.queryForObject("select * from categories where category_id = ?",  new CategoryRowMapper(),new Object[]{id});
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean containsByTitle(String title) {
        try {
            jdbcTemplate.queryForObject("select * from categories where category_title = ?",  new CategoryRowMapper(),new Object[]{title});
        } catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }
}
