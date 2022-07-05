package dao;

import entity.Category;
import entity.Like;
import entity.Post;
import entity.User;

import java.util.List;

public interface PostDao {
    void save(Post post);
    void deleteById(int id);
    void deleteByTitle(String title);
    List<Post> getAll();
    Post getById(int id);
    Post getByTitle(String title);
    List<Post> getAllByUser(User user);
    List<Post> getAllByCategory(Category category);
    List<Post> getAllChecked();
    List<Post> getAllUnchecked();
    boolean containsById(int id);
    boolean containsByTitle(String title);

    void updateDescription(int id, String description);
    void updateCategory(int id, Category category);
    void addChecked(Post post);
    String getCategoryName(Post post);

}
