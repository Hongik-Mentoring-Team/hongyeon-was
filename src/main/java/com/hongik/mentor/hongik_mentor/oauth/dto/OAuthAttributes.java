package com.hongik.mentor.hongik_mentor.oauth.dto;

import java.util.Map;

public class OAuthAttributes {
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
