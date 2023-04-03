package com.juneng.hellojhworld.service;

import com.juneng.hellojhworld.domain.Member;
import com.juneng.hellojhworld.dto.MemberForm;
import com.juneng.hellojhworld.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // 데이터 변경이 일어나야하므로 @트렌젝션 필수!!
@RequiredArgsConstructor
public class MemberService {
    // + @RequiredArgsConstructor 이렇게 생성하는 걸 추천 (테스트하기도 용이)
    private final MemberRepository memberRepository;

    //회원 가입
    /*public String join(MemberForm memberForm) {
        validateDuplicateMember(memberForm); // 중복 회원 검증

        // memberRepository.save(memberForm); 이 방법으로 안하고 엔티티에서 회원 생성 메서드 호출 하게끔 변경

        Member member = Member.createMember(memberForm); // 회원 생성

        return member.getUserId();
    }*/
    //회원 가입
    public String join(Member member) {
        if(!validateDuplicateMemberBoolean(member)) {  // 중복 아이디 검증
            return "이미 존재하는 아이디입니다.";
        }
        if(!validateDuplicateMemberNicknameBoolean(member)) {  // 중복 닉네임 검증
            return "이미 존재하는 닉네임입니다.";
        }
        memberRepository.save(member);

        return "success";
    }

    public String validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getUserId());
        if (!findMembers.isEmpty()){
            return "이미 존재하는 아이디입니다.";
        }
        return "사용 가능한 아이디입니다.";
    }

    public String validateDuplicateMemberNickname(Member member) {
        List<Member> findMembers = memberRepository.findByNickname(member.getNickname());
        if (!findMembers.isEmpty()){
            return "이미 존재하는 닉네임입니다.";
        }
        return "사용 가능한 닉네임입니다.";
    }

    public Boolean validateDuplicateMemberBoolean(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getUserId());
        if (!findMembers.isEmpty()){
            return false;
        }
        return true;
    }

    public Boolean validateDuplicateMemberNicknameBoolean(Member member) {
        List<Member> findMembers = memberRepository.findByNickname(member.getNickname());
        if (!findMembers.isEmpty()){
            return false;
        }
        return true;
    }

    //회원 전체 조회
    // 조회 시 (readOnly = true) 로 설정하면 일긱 전용 트렌젝션으로 취급하여 부하를 덜 일으킴.
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name); // 더티 체킹 일어남

    }
}
