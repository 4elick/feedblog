package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    private int id;
    private Post post;
    private User user;

    public Like(User user) {
        this.user = user;
    }

    public Like(int id, Post post) {
        this.id = id;
        this.post = post;
    }
}
