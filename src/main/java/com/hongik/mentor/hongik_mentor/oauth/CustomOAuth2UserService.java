package com.hongik.mentor.hongik_mentor.oauth;

import com.hongik.mentor.hongik_mentor.controller.dto.MemberResponseDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.oauth.dto.OAuthAttributes;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import com.hongik.mentor.hongik_mentor.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    //OAuth2User 반환
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //userRequest -> OAuthAttributes 생성
        String registrationId = userRequest.getClientRegistration().getRegistrationId();    //소셜공급자 구분
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();   //socialId

        OAuthAttributes oAuthAttributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());//attributes받아옴 from 위임된 OAuth2UserService의 OAuth2User로부터


        //OAuthAttributes -> Member 변환 & DB 저장 또는 수정
        saveOrUpdate(oAuthAttributes, userNameAttributeName);
        //httpSession에 저장
    }

    //Member가 이미 있다면 update, 없다면 save
    private Member saveOrUpdate(OAuthAttributes oAuthAttributes, String userNameAttributeName) {
        Optional<Member> findOptionalMember = memberRepository.findBySocialId(userNameAttributeName);

        //DB에 이미 Member있음
        if (findOptionalMember.isPresent()) {

        }

        //DB에 Member 없음
        else{

        }
    }
}
