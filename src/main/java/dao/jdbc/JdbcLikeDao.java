package dao.jdbc;

import dao.LikeDao;
import dao.jdbc.rowmapper.UserNameRowMapper;
import dao.jdbc.rowmapper.UserRowMapper;
import entity.Like;
import entity.Post;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcLikeDao implements LikeDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(Like like) {
        jdbcTemplate.update("INSERT INTO likes VALUES (default ,?,?)",like.getPost().getId(),like.getUser().getId());
    }

    @Override
    public List<String> getUsers() {
        return jdbcTemplate.query("SELECT user_name FROM likes " +
                "JOIN users on like.user_id = users.user_id",new UserNameRowMapper());
    }

    @Override
    public int getCount(Post post) {
        return jdbcTemplate.queryForObject("select post_likecount from likes l join posts p on l.post_id = p.id " +
                "where p.id = ?", Integer.class, new Object[]{post.getId()});
    }
}
