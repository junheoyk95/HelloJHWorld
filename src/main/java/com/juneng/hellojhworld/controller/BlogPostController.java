package com.juneng.hellojhworld.controller;

import com.juneng.hellojhworld.common.ResultVo;
import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.domain.Comment;
import com.juneng.hellojhworld.domain.Likes;
import com.juneng.hellojhworld.dto.CommentForm;
import com.juneng.hellojhworld.dto.LikesForm;
import com.juneng.hellojhworld.service.BlogPostService;
import com.juneng.hellojhworld.service.BlogService;
import com.juneng.hellojhworld.service.CommentService;
import com.juneng.hellojhworld.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;

    private final BlogService blogService;

    private final CommentService commentService;

    private final LikeService likeService;


    @RequestMapping(value = "/postPage")
    public String postPage(@RequestParam("postId") Long postId, HttpServletRequest request, Model model) {
        BlogPost result = blogPostService.findBlogPost(postId);
        model.addAttribute("postId", postId);
        model.addAttribute("title", result.getTitle());
        model.addAttribute("content", result.getContent());
        model.addAttribute("category", result.getCategory());
        model.addAttribute("registNickname", result.getRegistNickname());
        model.addAttribute("registDt", result.getRegistDt());
        model.addAttribute("likeCnt", result.getLikeCnt());

//        List<Map<String, Object>> categories = blogService.findPostsCategory();
//        model.addAttribute("categories", categories);

        HttpSession session = request.getSession();
        Object nickname = session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);

        Object userId = session.getAttribute("userId");
        model.addAttribute("userId", userId);

        Object isLoginYN = session.getAttribute("isLoginYN");
        model.addAttribute("isLoginYN", isLoginYN);

        return "post";
    }

    @RequestMapping(value = "/postPage/postCommentList")
    @ResponseBody
    public List<Comment> postCommentList(@RequestBody CommentForm commentForm) {

        List<Comment> comments = commentService.findCommentByPostId(commentForm.getPostId());

        return comments;
    }

    @RequestMapping(value = "/postPage/addComment")
    @ResponseBody
    public String addComment(@RequestBody CommentForm commentForm) {

        commentService.addComment(commentForm);

        return "";
    }

    @RequestMapping(value = "/postPage/addLike")
    @ResponseBody
    public Integer addLike(@RequestBody LikesForm like) {

        likeService.addLike(like);

        return likeService.findLikeCntByPostId(like.getPostId());
    }

    @RequestMapping(value = "/postPage/cancelLike")
    @ResponseBody
    public Integer cancelLike(@RequestBody LikesForm like) {

        likeService.cancelLike(like);

        return likeService.findLikeCntByPostId(like.getPostId());
    }

    @RequestMapping(value = "/postPage/findLikeByUserId")
    @ResponseBody
    public Integer findLikeByUserId(@RequestBody LikesForm like) {

        return likeService.findLikeByUserId(like);
    }

    @RequestMapping(value = "/postPage/deletePost")
    @ResponseBody
    public String deletePost(@RequestBody BlogPost blogPost, HttpServletRequest request, Model model) {

        blogPostService.deletePost(blogPost.getId());

        List<BlogPost> blogPosts = blogService.findBlogPosts();
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");

        model.addAttribute("userId", userId);
        model.addAttribute("blogPosts", blogPosts);

        return "blog";
    }

}
