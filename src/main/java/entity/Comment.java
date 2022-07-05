package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private User user;
    private String comment;
    private Post post;

    public Comment(User user, String comment, Post post) {
        this.user = user;
        this.comment = comment;
        this.post = post;
    }
}
