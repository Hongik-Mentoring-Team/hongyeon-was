package com.hongik.mentor.hongik_mentor.oauth.dto;

import lombok.Builder;

import java.util.Map;

public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;    //nameAttributeKey == userNameAttributeName == socialId
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //UserRequest -> OAuthAttributes 변환
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if ("naver".equals(registrationId)) {
            return ofNaver(userNameAttributeName, attributes);
        } else if ("google".equals(registrationId)) {
            return ofGoogle(userNameAttributeName, attributes);
        } else
            return ofKakao(userNameAttributeName, attributes);


    }

    //kakao로그인용
    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        /*카카오 JSON 구조
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
                .build();
    }

    //google로그인용
    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes) {
        return OAuthAttributes.builder()
                .attributes(attributes)
                .email(String.valueOf(attributes.get("email")))
                .name(String.valueOf(attributes.get("name")))
                .picture(String.valueOf(attributes.get("picture")))
                .nameAttributeKey(userNameAttributeName)
                .build();

    }


    //naver로그인용
    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .attributes(response)
                .email(String.valueOf(response.get("email")))
                .name(String.valueOf(response.get("name")))
                .picture(String.valueOf(response.get("profile_image")))
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

        /*
        public static OAuthAttributes ofGoogle(Map<String, Object> attributes) {
            return new OAuthAttributes(
                    (String) attributes.get("name"),
                    (String) attributes.get("email"),
                    (String) attributes.get("picture"),
                    (String) attributes.get("sub"), // Google의 고유 ID
                    "google"
            );
        }

        public static OAuthAttributes ofNaver(Map<String, Object> attributes) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            return new OAuthAttributes(
                    (String) response.get("name"),
                    (String) response.get("email"),
                    (String) response.get("profile_image"),
                    (String) response.get("id"), // Naver의 고유 ID
                    "naver"
            );
        }*/

}
