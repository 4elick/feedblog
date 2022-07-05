package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int id;
    private String title;
    private String description;
    private User user;
    private Category category;
    private boolean isChecked;
    private Date date;
    private Comment moderComment;
    private int views;

    public Post(int id, String title, String description, User user, Category category, boolean isChecked, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.category = category;
        this.isChecked = isChecked;
        this.date = date;

    }
    public Post(int id, String title, String description, User user, Category category, boolean isChecked, Date date,int views) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.category = category;
        this.isChecked = isChecked;
        this.date = date;
        this.views = views;

    }
    public Post(int id, String title, User user, Category category, boolean isChecked, Date date, Comment moderComment, int likesCount) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.category = category;
        this.isChecked = isChecked;
        this.date = date;
        this.moderComment = moderComment;
        this.views = likesCount;
    }

    public Post(int id, String title, User user, Category category, boolean isChecked, Date date, Comment moderComment) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.category = category;
        this.isChecked = isChecked;
        this.date = date;
        this.moderComment = moderComment;
    }

    public Post(int id, String title, String description, User user, boolean isChecked, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.isChecked = isChecked;
        this.date = date;
    }

    public Post(int id, String title, User user, boolean isChecked, Date date) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.isChecked = isChecked;
        this.date = date;
    }
}
