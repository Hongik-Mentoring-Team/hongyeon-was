package com.hongik.mentor.hongik_mentor.oauth;

import com.hongik.mentor.hongik_mentor.controller.dto.MemberResponseDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.oauth.dto.OAuthAttributes;
import com.hongik.mentor.hongik_mentor.oauth.dto.SessionMember;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import com.hongik.mentor.hongik_mentor.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Transactional  //DB에 member 조회 쿼리를 실행시키기 위해 필요함
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    //OAuth2User 반환
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
    //1.
        //userRequest -> OAuthAttributes 생성
        String registrationId = userRequest.getClientRegistration().getRegistrationId();    //소셜공급자 구분
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();   //socialId

        OAuthAttributes oAuthAttributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());//attributes받아옴 from 위임된 OAuth2UserService의 OAuth2User로부터


        //OAuthAttributes -> Member엔티티 변환 & DB 저장 또는 수정
        Member member = saveOrUpdate(oAuthAttributes, userNameAttributeName);
    //2.
        //httpSession에 저장
        httpSession.setAttribute("sessionMember", new SessionMember(member));

    //3.
        //Oauth2User반환
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRole().getKey())),   //Member 객체의 authority만 빼와서 Oauth2User에 저장
                oAuthAttributes.getAttributes(),
                oAuthAttributes.getNameAttributeKey()
        );
    }

    //Member가 이미 있다면 update, 없다면 save
    private Member saveOrUpdate(OAuthAttributes oAuthAttributes, String userNameAttributeName) {
        Optional<Member> findOptionalMember = memberRepository.findBySocialId(userNameAttributeName);

        //DB에 이미 Member있음
        if (findOptionalMember.isPresent()) {
            Member member = findOptionalMember.get();
            return member;
        }
        //DB에 Member 없음 == 회원가입
        else{
//            return memberRepository.save(oAuthAttributes.toEntity()); //Member의 다른 멤버변수가 채워지지 않았기에 save불가
            return oAuthAttributes.toEntity();
        }
    }
}
