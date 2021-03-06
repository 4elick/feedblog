package dao;

import entity.Comment;
import entity.Post;
import entity.User;

import java.util.List;

public interface CommentDao {
    void save(Comment comment);
    void deleteById(int id);
    Comment getById(int id);
    List<Comment> getAllByPost(Post post);
    List<Comment> getAllByUser(User user);
    List<Comment> getAll();
    boolean containsById(int id);
}
