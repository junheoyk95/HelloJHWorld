package com.juneng.hellojhworld.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juneng.hellojhworld.dto.MemberForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String memo;
    private LocalDateTime registDt;

    // 생성 메서드
    // 생성하는 시점을 변경할 일이 있으면 이부분만 고치면 되기때문에 이렇게 작성
    public static Member createMember(MemberForm memberForm) {
        Member member = new Member();

        member.setUserId(memberForm.getUserId());
        member.setPassword(memberForm.getPassword());
        member.setName(memberForm.getName());
        member.setNickname(memberForm.getNickname());
        member.setMemo(memberForm.getMemo());
        member.setRegistDt(LocalDateTime.now());

        return member;
    }



    //Embedded type 은 사용자가 직접 정의한 값 타입이다. 여기서 Embedded type을 사용하지 않으면,집 주소에 관한 정보
    //city,street,zipcode 를 전부 정의해 줘야 되는데 그러면 객체지향적이지 않고 응집력을 떨어뜨리는 원인이 된다.
    //때문에 Embedded type을 사용하여 코드를 좀 더 명확히 한 것이다.
//    @Embedded
//    private Address address;

    // 'mappedBy' 옵션은 연관관계의 주인이 아닌 엔티티에 설정을 해주어야 하니,
    // Member 엔티티에 mappedBy 옵션을 설정해주게 됩니다.
//    @OneToMany(mappedBy = "member")
    // 객체를 json으로 만들때 무한 로프 방지를 위해서
    // 양방향 연관 관계 일때는 한쪽에 @JsonIgnore 을 사용해서 막아준다.
//    @JsonIgnore
//    private List<Order> orders = new ArrayList<>();
}
