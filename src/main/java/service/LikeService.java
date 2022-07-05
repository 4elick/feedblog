package service;

import dao.LikeDao;
import dao.jdbc.JdbcLikeDao;
import entity.Like;
import entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService implements LikeDao {
    private LikeDao likeDao;

    public LikeService(LikeDao likeDao){
        this.likeDao = likeDao;
    }


    @Override
    public void save(Like like) {
        likeDao.save(like);
    }

    @Override
    public List<String> getUsers() {
        return likeDao.getUsers();
    }

    @Override
    public int getCount(Post post) {
        return likeDao.getCount(post);
    }
}
