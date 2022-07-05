package dao.jdbc;

import dao.CommentDao;
import dao.jdbc.rowmapper.CommentRowMapper;
import entity.Comment;
import entity.Post;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcCommentDao implements CommentDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(Comment comment) {
        jdbcTemplate.update("INSERT INTO comments VALUES(?,,,)",comment.getComment(),comment.getPost().getId(),comment.getUser().getId());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM comments WHERE comment_id = ?",id);
    }

    @Override
    public Comment getById(int id) {
        return jdbcTemplate.queryForObject("SELECT comment_id,comment_text,user_login,user_password,user_name,user_role,post_id,post_title,post_description FROM " +
                "comments c JOIN users u ON c.comment_user_id  = u.user_id " +
                "JOIN posts p ON c.comment_post_id = p.post_id WHERE comment_id = ?",new CommentRowMapper(),id);
    }

    @Override
    public List<Comment> getAllByPost(Post post) {
        return jdbcTemplate.query("SELECT comment_id,comment_text,user_login,user_password,user_name,user_role,post_id,post_title,post_description FROM " +
                "comments c JOIN users u ON c.comment_user_id  = u.user_id " +
                "JOIN posts p ON c.comment_post_id = p.post_id WHERE comment_post_id = ?",new CommentRowMapper(),new Object[]{post.getId()});
    }

    @Override
    public List<Comment> getAllByUser(User user) {
        return jdbcTemplate.query("SELECT comment_id,comment_text,user_login,user_password,user_name,user_role,post_id,post_title,post_description FROM " +
                "comments c JOIN users u ON c.comment_user_id  = u.user_id " +
                "JOIN posts p ON c.comment_post_id = p.post_id WHERE comment_user_id = ?",new CommentRowMapper(),new Object[]{user.getId()});
    }

    @Override
    public List<Comment> getAll() {
        return jdbcTemplate.query("SELECT comment_id,comment_text,user_login,user_password,user_name,user_role,post_id,post_title,post_description FROM " +
                "comments c JOIN users u ON c.comment_user_id  = u.user_id " +
                "JOIN posts p ON c.comment_post_id = p.post_id",new CommentRowMapper());
    }

    @Override
    public boolean containsById(int id) {
        try {
            jdbcTemplate.queryForObject("SELECT comment_id,comment_text,user_login,user_password,user_name,user_role,post_id,post_title,post_description FROM " +
                    "comments c JOIN users u ON c.comment_user_id  = u.user_id " +
                    "JOIN posts p ON c.comment_post_id = p.post_id WHERE comment_id = ?",new CommentRowMapper(),id);
        }catch (EmptyResultDataAccessException e){
            return false;
        }

        return true;
    }
}
