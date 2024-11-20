package com.hongik.mentor.hongik_mentor.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

//회원 엔티티
/*고려사항
*
* 1. 로그인 전략
* - only 소셜로그인 VS 소셜로그인+자체로그인
* 비교:
*       상황: 유저 '홍길동'이 카카오로 회원가입 후 네이버로도 같은 계정취급을 받으며 로그인하고 싶음.
*       이때, 소셜로그인만을 사용한다면 적절히 처리가 불가능함. 반면 자체로그인을 도입한다면 개인의 주민번호를 사용하여 유저 식별이 가능하기 때문에 하나의 통합된 계정으로 생성 가능함.
* 결정:
*       자체로그인 시 필요한 보안수준을 아직 모르기에 소셜로그인만으로 진행.
*
* 2. socialId x provider VS email
* 필요성: 사용자가 처음 소셜로그인 하여 회원가입하는 경우와 그 외의 경우를 구분.
*          소셜로그인을 통해 회원가입한 회원들 사이의 구분에 사용되는 기준임.
* 이때, 유저를 식별하기위해서 socialId와 email중 하나를 택할 수 있음.
* 유연성: socialId가 더 높음.
*       왜냐하면, 소셜공급자마다 제공하는 api가 다르고 email을 제공하지 않을 가능성도 존재함.
* 결정:
*       socialId x provider 사용
*
* */

@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //참고(sequence, table전략은 JPA에서 ID를 미리 할당받기에 쿼리를 지연 가능, 반면 identity는 즉시 쿼리 발생)
    @Column(name = "member_id")
    private Long id;            //DB용 PK
    @Column(nullable = false)
    private String socialId;    //socialId+provider를 조합하여 유저를 구분함
    @Column(nullable = false)
    private SocialProvider socialProvider;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String major;
    @Column(nullable = false)
    private Integer graduationYear;
    @Column(nullable = false)
    private MemberType type;    //재학생/졸업생

    private AccountStatus accountStatus; //null 주의

    public Member() {
    }

    public Member(String socialId, SocialProvider socialProvider, String name, String major, Integer graduationYear, MemberType type, AccountStatus accountStatus) {
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.name = name;
        this.major = major;
        this.graduationYear = graduationYear;
        this.type = type;
        this.accountStatus = accountStatus;
    }
}
