package com.juneng.hellojhworld.controller;

import com.juneng.hellojhworld.common.ResultVo;
import com.juneng.hellojhworld.domain.Member;
import com.juneng.hellojhworld.dto.BlogPostForm;
import com.juneng.hellojhworld.dto.LoginForm;
import com.juneng.hellojhworld.service.LoginService;
import com.juneng.hellojhworld.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 로그인 처리
    // Object 타입 반환 + @ResponseBody
    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(@RequestBody LoginForm loginForm, Model model, HttpServletRequest request) {

        // 로그인 성공 여부
        boolean isAuthenticated = loginService.authenticate(loginForm.getUserId(), loginForm.getPassword(), request);

        Map<String, String> map = new HashMap<>();
        ResultVo resultVo = new ResultVo();
        // 실패
        if (!isAuthenticated) {
            map.put("msg", "fail");
            resultVo.setData(map);

            return resultVo;
        }

        // 성공
        map.put("msg", "success");
        resultVo.setData(map);

        return resultVo;
    }

}
