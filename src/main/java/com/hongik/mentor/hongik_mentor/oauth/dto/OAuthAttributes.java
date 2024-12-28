package com.hongik.mentor.hongik_mentor.oauth.dto;

import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.SocialProvider;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;    //nameAttributeKey == userNameAttributeName == socialId
    private String name;
    private String email;
    private String picture;
    private String registrationId;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, String registrationId) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.registrationId = registrationId;
    }

    //UserRequest -> OAuthAttributes 변환
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes, registrationId);   //기존의 userNameAttributeName: "response"이므로 -> "id"로 변경 필요
        } else if ("google".equals(registrationId)) {
            return ofGoogle(userNameAttributeName, attributes, registrationId);
        } else
            return ofKakao(userNameAttributeName, attributes, registrationId);


    }

    //kakao로그인용
    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes, String registrationId) {
        /*카카오 attributes (JSON) 구조
        예시임
        {
          "id": 1234567890,
          "connected_at": "2024-01-01T00:00:00Z",
          "properties": {
            "nickname": "홍길동",
            "profile_image": "http://example.com/profile.jpg",
            "thumbnail_image": "http://example.com/thumbnail.jpg"
          },
          "kakao_account": {
            "profile_needs_agreement": false,
            "profile": {
              "nickname": "홍길동",
              "profile_image_url": "http://example.com/profile.jpg",
              "thumbnail_image_url": "http://example.com/thumbnail.jpg"
            },
            "email_needs_agreement": false,
            "is_email_valid": true,
            "is_email_verified": true,
            "email": "user@example.com"
          }
        }
       * */

        //properties 접근
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        String nickname = String.valueOf(properties.get("nickname"));

        //account 접근
        Map<String, Object> kakaoAcount = (Map<String, Object>) attributes.get("kakao_acount");
        String email = String.valueOf(kakaoAcount.get("email"));
        Map<String, Object> profile = (Map<String, Object>) kakaoAcount.get("profile");
        String profileImageUrl = String.valueOf(profile.get("profile_image_url"));

        return OAuthAttributes.builder()
                .attributes(attributes)
                .email(email)
                .name(nickname)
                .picture(profileImageUrl)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }

    //google로그인용
    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes, String registrationId) {
        return OAuthAttributes.builder()
                .attributes(attributes)
                .email(String.valueOf(attributes.get("email")))
                .name(String.valueOf(attributes.get("name")))
                .picture(String.valueOf(attributes.get("picture")))
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();

    }


    //naver로그인용
    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes,String registrationId) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .attributes(response)
                .email(String.valueOf(response.get("email")))
                .name(String.valueOf(response.get("name")))
                .picture(String.valueOf(response.get("profile_image")))
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }

    public Member toEntity() {
        return new Member(nameAttributeKey, SocialProvider.from(registrationId));
    }


}
