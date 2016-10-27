package blog.forms;

import blog.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostForm {
//    @Size(min=2, max=30, message = "Username size should be in the range [2...30]")
//    @NotNull
//    @Size(min=1, max=50)

    private String title;
    private String body;
    private String author;

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}