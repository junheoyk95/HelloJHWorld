package com.juneng.hellojhworld.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LogoutController {

    // 로그아웃
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {

        // HttpSession 객체 가져오기
        // HttpSession 객체를 가져올 때, request.getSession(false)와 같이 false를 인자로 전달하여
        // 세션이 존재하지 않을 때(null) 새로운 세션을 생성하지 않도록 합니다.
        // 이렇게 하면 세션이 존재하지 않을 때 로그인 페이지를 보여줄 수 있습니다.
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션 무효화
            session.invalidate();
        }

        return "main";
    }

}
