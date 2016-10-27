package blog.controllers;

import blog.forms.EditForm;
import blog.forms.PostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import blog.models.*;
import blog.services.*;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostsController {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notifyService;

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Post post = postService.findById(id);
        if (post == null) {
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/";
        }
        model.addAttribute("post", post);
        return "posts/view";
    }


    @RequestMapping("/posts")
    public String index(Model model) {
        List<Post> allPosts = postService.findAll();
        model.addAttribute("allPosts", allPosts);
        return "posts/posts";
    }

    @RequestMapping("/posts/create")
    public String create(PostForm postform) {
        return "posts/create";
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public String createPage(PostForm postForm) {
        Post post = new Post(7L, postForm.getTitle(), postForm.getBody(), new User(3L, "user1", "test"));
        postService.create(post);
        return "redirect:/";
    }

    @RequestMapping("/posts/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, EditForm editFrom) {
        Post post = postService.findById(id);
        if (post == null) {
            notifyService.addErrorMessage("Cannot find post #" + id);
            return "redirect:/";
        }
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @RequestMapping(value = "/posts/edit/{id}", method = RequestMethod.POST)
    public String editPost(@PathVariable("id") Long id, EditForm editForm) {
        Post post = new Post(id, editForm.getTitle(), editForm.getBody(), new User(3L, "user1", "test"));
        postService.edit(post);
        return "redirect:/";
    }

    @RequestMapping(value = "/posts/delete/{id}", method = RequestMethod.POST)
    public String deletePost(@PathVariable("id") Long id) {
        postService.deleteById(id);
        return "redirect:/";
    }
}