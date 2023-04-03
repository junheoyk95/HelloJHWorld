package com.juneng.hellojhworld.controller;

import com.juneng.hellojhworld.common.ResultVo;
import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.domain.Member;
import com.juneng.hellojhworld.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainTopController {

    private final BlogService blogService;

    @RequestMapping("/blog")
    public String blog(HttpServletRequest request, Model model, @RequestParam(required = false, defaultValue = "0",
    value = "page") int page){
        Page<BlogPost> blogPosts = blogService.pageList(page);
        List<Map<String, Object>> categories = blogService.findPostsCategory();
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");

        model.addAttribute("blogPosts", blogPosts);
        model.addAttribute("userId", userId);
        model.addAttribute("categories", categories);

        return "blog";
    }

    @RequestMapping("/about")
    public String about(HttpServletRequest request, Model model) {


       return "about";
    }

    @RequestMapping("/contact")
    public String contact(HttpServletRequest request, Model model) {


        return "contact";
    }

    @RequestMapping("/loginForm")
    public String loginForm() {

        return "loginForm";
    }
    @RequestMapping("/signInForm")
    public String signInForm() {

        return "signInForm";
    }

}
