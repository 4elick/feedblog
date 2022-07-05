package service;

import dao.PostDao;
import entity.Category;
import entity.Post;
import entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements PostDao {
    private PostDao postDao;
    public PostService(PostDao postDao){
        this.postDao =postDao;
    }
    @Override
    public void save(Post post) {
        if(!postDao.containsByTitle(post.getTitle())){
            postDao.save(post);
        }
    }

    @Override
    public void deleteById(int id) {
        if(postDao.containsById(id)){
            postDao.deleteById(id);
            return;
        }

    }

    @Override
    public void deleteByTitle(String title) {
        if(postDao.containsByTitle(title)){
            postDao.deleteByTitle(title);
            return;
        }
    }

    @Override
    public List<Post> getAll() {
        return postDao.getAll();
    }

    @Override
    public Post getById(int id) {
        if(postDao.containsById(id)){
            return postDao.getById(id);
        }
        return null;
    }

    @Override
    public Post getByTitle(String title) {
        if(postDao.containsByTitle(title)){
            return postDao.getByTitle(title);
        }
        return null;
    }

    @Override
    public List<Post> getAllByUser(User user) {
        return postDao.getAllByUser(user);
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        return postDao.getAllByCategory(category);
    }

    @Override
    public List<Post> getAllChecked() {
        return postDao.getAllChecked();
    }

    @Override
    public List<Post> getAllUnchecked() {
        return postDao.getAllUnchecked();
    }

    @Override
    public boolean containsById(int id) {
        return postDao.containsById(id);
    }

    @Override
    public boolean containsByTitle(String title) {
        return postDao.containsByTitle(title);
    }

    @Override
    public void updateDescription(int id, String description) {
        postDao.updateDescription(id, description);
    }

    @Override
    public void updateCategory(int id, Category category) {
        postDao.updateCategory(id, category);
    }

    @Override
    public void addChecked(Post post) {
        postDao.addChecked(post);
    }

    @Override
    public String getCategoryName(Post post) {
        return postDao.getCategoryName(post);
    }
}
