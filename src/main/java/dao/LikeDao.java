package dao;

import entity.Like;
import entity.Post;
import entity.User;

import java.util.List;

public interface LikeDao {
    void save(Like like);
    List<String> getUsers();
    int getCount(Post post);
}
