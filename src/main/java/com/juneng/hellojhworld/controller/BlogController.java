package com.juneng.hellojhworld.controller;

import com.juneng.hellojhworld.common.ResultVo;
import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.domain.Member;
import com.juneng.hellojhworld.dto.BlogPostForm;
import com.juneng.hellojhworld.dto.MemberForm;
import com.juneng.hellojhworld.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @RequestMapping("/postCreateForm")
    public String postCreateForm() {

        return "postCreateForm";
    }

    @PostMapping("/blogPost/postCreate")
    public String postCreate(@Validated @RequestBody BlogPostForm blogPostForm, BindingResult result){
        if(result.hasErrors()){
            return "postCreateForm";
        }
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(blogPostForm.getTitle());
        blogPost.setContent(blogPostForm.getContent());
        blogPost.setCategory(blogPostForm.getCategory());
        blogPost.setRegistUserId(blogPostForm.getRegistUserId());
        blogPost.setRegistNickname(blogPostForm.getRegistNickname());
        blogPost.setRegistDt(LocalDateTime.now());

        blogService.postCreate(blogPost);

        return "postCreateForm";
    }

    @RequestMapping("/blogPostsList")
    public String blogPostsList(Model model) {
        List<BlogPost> blogPosts = blogService.findBlogPosts();

        model.addAttribute("blogPosts", blogPosts);

        return "blog";
    }

    @RequestMapping(value = "/postsListByCategory", method = { RequestMethod.POST })
    public String postsListByCategory(@RequestBody BlogPostForm blogPostForm, Model model) {
        List<BlogPost> blogPosts = blogService.findByCategory(blogPostForm.getCategory());

        model.addAttribute("blogPosts", blogPosts);

        return "/blog :: #postsTable";
    }

    @RequestMapping("/blog/postsListByCategory")
    public String blogByCategory(HttpServletRequest request, Model model, @RequestParam(required = false, defaultValue = "0",
            value = "page") int page, @RequestBody BlogPostForm blogPostForm){
        Page<BlogPost> blogPosts = blogService.blogByCategory(page, blogPostForm.getCategory());
        List<Map<String, Object>> categories = blogService.findPostsCategory();
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");

        model.addAttribute("blogPosts", blogPosts);
        model.addAttribute("userId", userId);
        model.addAttribute("categories", categories);

        return "blog";
    }


}
