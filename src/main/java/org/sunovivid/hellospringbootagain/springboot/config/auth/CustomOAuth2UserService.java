package org.sunovivid.hellospringbootagain.springboot.config.auth;

import javax.servlet.http.HttpSession;
import java.util.Collections;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import org.sunovivid.hellospringbootagain.springboot.domain.user.User;
import org.sunovivid.hellospringbootagain.springboot.domain.user.UserRepository; //UserRepository 빨간줄, Cannot resolve symbol "UserRepository"
import org.sunovivid.hellospringbootagain.springboot.config.auth.dto.OAuthAttributes; //같은에러
import org.sunovivid.hellospringbootagain.springboot.config.auth.dto.SessionUser; //같은에러

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); //로그인 진행 중인 서비스 구분 (네이버인가, 구글인가?)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        //userNameAttributeName: OAuth2 로그인 진행시 키가 되는 필드값 (PK)

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());//oAuth2User를 oAuth2UserService타입으로 위에서 선언함.. 자동완성의 폐해. 잘 보고 자동완성하자

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(
                        new SimpleGrantedAuthority(user.getRoleKey())),
                        attributes.getAttributes(),
                        attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture())) //update 찾을 수 없는 에러 -> 진짜 update를 주석처리해둔거였음..
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
