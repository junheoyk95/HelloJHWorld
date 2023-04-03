package com.juneng.hellojhworld.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.juneng.hellojhworld.common.ResultVo;
import com.juneng.hellojhworld.domain.Member;
import lombok.extern.slf4j.Slf4j;
import com.juneng.hellojhworld.dto.MemberForm;
import com.juneng.hellojhworld.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // @Valid + BindingResult 를 쓰면 오류를 담은 채로 코드가 실행됨, 에러 시 다른 화면으로 보내줄수가 있음.
    // 게다가 에러 메세지를 해당 화면의 해당 항목에 출력 가능.
    // * 멤버 엔티티를 않쓰는 이유
    // 같이 쓰려고 하면 굉장히 지져분해지고 실무는 복잡하기 때문에
    // 차라리 화면에 맞는 MemberForm - 폼으로 데이터를 받는게 낫다.
    // 엔티티를 화면의 폼데이터로 쓰지 말자.
    @PostMapping("/members/create")
    @ResponseBody
    public Object create(@Validated @RequestBody MemberForm memberForm, BindingResult result){
        if(result.hasErrors()){
            return "signInForm";
        }
        Member member = new Member();
        member.setUserId(memberForm.getUserId());
        member.setPassword(memberForm.getPassword());
        member.setName(memberForm.getName());
        member.setNickname(memberForm.getNickname());
        member.setMemo(memberForm.getMemo());
        member.setRegistDt(LocalDateTime.now());

        String msg = memberService.join(member);

        Map<String, String> map = new HashMap<>();
        map.put("msg", msg);
        ResultVo resultVo = new ResultVo();
        resultVo.setData(map);

        return resultVo;
        // 리다이렉트로 하면 첫페이지로 넘어감
        // return "redirect:/";
    }

    // id 중복체크
    @PostMapping("/members/duplicateCheckId")
    @ResponseBody
    public Object duplicateCheckId(@RequestBody MemberForm memberForm){
        Member member = new Member();
        member.setUserId(memberForm.getUserId());
        String msg = memberService.validateDuplicateMember(member);

        Map<String, String> map = new HashMap<>();
        map.put("msg", msg);
        ResultVo resultVo = new ResultVo();
        resultVo.setData(map);

        return resultVo;
    }

    // 닉네임 중복체크
    @PostMapping("/members/duplicateCheckNickname")
    @ResponseBody
    public Object duplicateCheckNickname(@RequestBody MemberForm memberForm){
        Member member = new Member();
        member.setNickname(memberForm.getNickname());
        String msg = memberService.validateDuplicateMemberNickname(member);

        Map<String, String> map = new HashMap<>();
        map.put("msg", msg);
        ResultVo resultVo = new ResultVo();
        resultVo.setData(map);

        return resultVo;
    }
}
