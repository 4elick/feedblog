package dao.jdbc;

import dao.PostDao;
import dao.jdbc.rowmapper.CategoryNameRowMapper;
import dao.jdbc.rowmapper.PostRowMapper;
import entity.Category;
import entity.Post;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcPostDao implements PostDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(Post post) {
        jdbcTemplate.update("INSERT INTO posts VALUES(default,?,?,?,?,?,?,?)",post.getTitle(),post.getDescription(),post.getDate(),
                post.getUser().getId(),post.getCategory().getId(),post.isChecked(),post.getViews());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM posts WHERE post_id = ?",id);
    }

    @Override
    public void deleteByTitle(String title) {
        jdbcTemplate.update("DELETE FROM posts WHERE post_title = ?",title);
    }

    @Override
    public List<Post> getAll() {
        return jdbcTemplate.query("SELECT post_id,post_title,post_description,user_id," +
                "user_login,user_password,user_name,user_role,category_id,category_title," +
                "post_date,post_ischecked FROM posts p JOIN users u ON p.post_user_id = u.user_id " +
                "JOIN category c ON p.post_category_id = c.category_id", new PostRowMapper());
    }

    @Override
    public Post getById(int id) {
        return jdbcTemplate.queryForObject("SELECT post_id,post_title,post_description,user_id," +
                "user_login,user_password,user_name,user_role,category_id,category_title," +
                "post_date,post_ischecked FROM posts p JOIN users u ON p.post_user_id = u.user_id " +
                "JOIN category c ON p.post_category_id = c.category_id " +
                "WHERE p.post_id = ?",new PostRowMapper(),new Object[]{id});
    }

    @Override
    public Post getByTitle(String title) {
        return jdbcTemplate.queryForObject("SELECT post_id,post_title,post_description,user_id," +
                "user_login,user_password,user_name,user_role,category_id,category_title," +
                "post_date,post_ischecked FROM posts p JOIN users u ON p.post_user_id = u.user_id " +
                "JOIN category c ON p.post_category_id = c.category_id " +
                "WHERE p.post_id = ?",new PostRowMapper(),new Object[]{title});
    }

    @Override
    public List<Post> getAllByUser(User user) {
        return jdbcTemplate.query("SELECT post_id,post_title,post_description,user_id," +
                "user_login,user_password,user_name,user_role,category_id,category_title," +
                "post_date,post_ischecked FROM posts p JOIN users u ON p.post_user_id = u.user_id " +
                "JOIN category c ON p.post_category_id = c.category_id " +
            "WHERE user_id = ?", new PostRowMapper(),new Object[]{user.getId()});
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        return jdbcTemplate.query("SELECT post_id,post_title,post_description,user_id," +
                "user_login,user_password,user_name,user_role,category_id,category_title," +
                "post_date,post_ischecked FROM posts p JOIN users u ON p.post_user_id = u.user_id " +
                "JOIN category c ON p.post_category_id = c.category_id " +
                "WHERE category_id = ?", new PostRowMapper(),new Object[]{category.getId()});
    }

    @Override
    public List<Post> getAllChecked() {
        return jdbcTemplate.query("SELECT post_id,post_title,post_description,user_id," +
                "user_login,user_password,user_name,user_role,category_id,category_title," +
                "post_date,post_ischeked FROM posts p JOIN users u ON p.post_user_id = u.user_id " +
                "JOIN category c ON p.post_category_id = c.category_id " +
                "WHERE post_ischecked = true", new PostRowMapper());
    }

    @Override
    public List<Post> getAllUnchecked() {
        return jdbcTemplate.query("SELECT post_id,post_title,post_description,user_id," +
                "user_login,user_password,user_name,user_role,category_id,category_title," +
                "post_date,post_ischeked FROM posts p JOIN users u ON p.post_user_id = u.user_id " +
                "JOIN category c ON p.post_category_id = c.category_id " +
                "WHERE post_ischecked = false", new PostRowMapper());
    }

    @Override
    public boolean containsById(int id) {
        try{
            jdbcTemplate.queryForObject("SELECT post_id,post_title,post_description,user_id," +
                    "user_login,user_password,user_name,user_role,category_id,category_title," +
                    "post_date,post_ischecked FROM posts p JOIN users u ON p.post_user_id = u.user_id " +
                    "JOIN category c ON p.post_category_id = c.category_id " +
                    "WHERE p.post_id = ?",new PostRowMapper(),new Object[]{id});
        }catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean containsByTitle(String title) {
        try{
            jdbcTemplate.queryForObject("SELECT post_id,post_title,post_description,user_id," +
                    "user_login,user_password,user_name,user_role,category_id,category_title," +
                    "post_date,post_ischecked FROM posts p JOIN users u ON p.post_user_id = u.user_id " +
                    "JOIN category c ON p.post_category_id = c.category_id " +
                    "WHERE p.post_title = ?",new PostRowMapper(),new Object[]{title});
        }catch (EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }



    @Override
    public void updateDescription(int id, String description) {
        jdbcTemplate.update("UPDATE posts set post_description = ? WHERE post_id = ?",description,id);
    }

    @Override
    public void updateCategory(int id, Category category) {
        jdbcTemplate.update("UPDATE posts set post_category_id = ? WHERE post_id = ?",category.getId(),id);
    }

    @Override
    public void addChecked(Post post) {
        jdbcTemplate.update("UPDATE posts set post_ischecked = true where post_id = ?",post.getId());
    }

    @Override
    public String getCategoryName(Post post) {
        return jdbcTemplate.queryForObject("SELECT category_name from posts p JOIN category c ON p.post_category_id = c.category_id WHERE post_id = ?", new CategoryNameRowMapper(),new Object[]{post.getId()
        });
    }
}
