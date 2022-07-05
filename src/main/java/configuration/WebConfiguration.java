package configuration;

import dao.CategoryDao;
import dao.LikeDao;
import dao.PostDao;
import dao.UserDao;
import dao.jdbc.JdbcCategoryDao;
import dao.jdbc.JdbcLikeDao;
import dao.jdbc.JdbcPostDao;
import dao.jdbc.JdbcUserDao;
import entity.Category;
import entity.Like;
import entity.Post;
import entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.CategoryService;
import service.LikeService;
import service.PostService;
import service.UserService;

import javax.sql.DataSource;
import java.util.Date;

@EnableWebMvc
@ComponentScan(basePackages = "java")
public class WebConfiguration {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/feedblog");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("knigamenya");
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) {
        LikeDao likeDao = new JdbcLikeDao();
        PostDao postDao = new JdbcPostDao();
        UserDao userDao = new JdbcUserDao();
        CategoryDao categoryDao = new JdbcCategoryDao();
        UserService userService = new UserService(userDao);
        PostService postService = new PostService(postDao);
        CategoryService categoryService = new CategoryService(categoryDao);
        LikeService likeService = new LikeService(likeDao);
        User user = userService.getById(2);
        Category category = new Category(1,"testovay");
        Post post = new Post(1,"Test","Description",user,category,true,new Date());
        likeService.save(new Like(1,post,userService.getById(2)));
        int likes  = likeService.getCount(post);
        categoryService.save(category);
        postService.save(post);
        userService.save(user);
        System.out.println("CAtegory-"+ categoryService.getById(0).getTitle());
        System.out.println("likes = " + likes);
        System.out.println("Post = " +postService.getById(0).getTitle());
        System.out.println("User" + userService.getById(1).getFirstName());

    }
}
