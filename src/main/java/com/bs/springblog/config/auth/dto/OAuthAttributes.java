package com.bs.springblog.config.auth.dto;

import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.Member.AuthenticationProvider;
import com.bs.springblog.domain.Member.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    /**
     * 구글 로그인 이후 가져온 사용자의 이메일, 이름, 프로필 사진 주소를 저장하는 DTO
     */
    private Map<String, Object> attributes;
    private String nameAttributeKey, name, email, picture;
    private AuthenticationProvider authenticationProvider;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey,
                           String name, String email, String picture, AuthenticationProvider authenticationProvider) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.authenticationProvider = authenticationProvider;
    }
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }
    public static OAuthAttributes ofGoogle(String userNameAttributeName,
                                           Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .authenticationProvider(AuthenticationProvider.GOOGLE)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.ROLE_USER)
                .authenticationProvider(authenticationProvider)
                .build();
    }
}
