package com.juneng.hellojhworld.controller;

import com.juneng.hellojhworld.domain.BlogPost;
import com.juneng.hellojhworld.service.BlogPostService;
import com.juneng.hellojhworld.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final BlogPostService blogPostService;

    private final BlogService blogService;

    @RequestMapping("/")
    public String main(HttpServletRequest request, Model model) {
        log.info("HomeController main()");

        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        model.addAttribute("userId", userId);
        Object nickname = session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
        Object isLoginYN = session.getAttribute("isLoginYN");
        model.addAttribute("isLoginYN", isLoginYN);

        log.info("HomeController main nickname() "+ userId);

        return "main";
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        log.info("HomeController home()");

        List<BlogPost> blogPosts = blogPostService.findTop3BlogPostsOrderByHeartDesc();

        model.addAttribute("blogPosts", blogPosts);

        return "home";
    }
}
