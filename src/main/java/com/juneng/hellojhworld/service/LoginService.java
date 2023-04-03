package com.juneng.hellojhworld.service;

import com.juneng.hellojhworld.domain.Member;
import com.juneng.hellojhworld.repository.LoginRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
    // + @RequiredArgsConstructor 이렇게 생성하는 걸 추천 (테스트하기도 용이)
    private final LoginRepository loginRepository;


    // 조회 시 (readOnly = true) 로 설정하면 읽기 전용 트렌젝션으로 취급하여 부하를 덜 일으킴.
    @Transactional(readOnly = true)
    public boolean authenticate(String userId, String password, HttpServletRequest request) {
        Optional<Member> result = loginRepository.findByUseIdPw(userId, password);
        HttpSession session = request.getSession();

        if(!result.isPresent()){
            // 로그인 실패
            session.setAttribute("isLoginYN", false);
            return false;
        }

        // 로그인 성공
        Member member = result.get();
        session.setAttribute("userId", member.getUserId());
        session.setAttribute("nickname", member.getNickname());
        session.setAttribute("isLoginYN", true);

        return true;

    }


}
